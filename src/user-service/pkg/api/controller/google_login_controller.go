package controller

import (
	"database/sql"
	"encoding/json"
	"net/http"
	"time"

	"github.com/fajrimgfr/user-service/pkg/bootstrap"
	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
)

type GoogleLoginController struct {
	GoogleLoginUsecase domain.GoogleLoginUsecase
	Env                *bootstrap.Env
}

// todo: save RANDOM state in redis
func (glc *GoogleLoginController) GoogleLogin(c *gin.Context) {
	url := bootstrap.GoogleOAuthConfig.AuthCodeURL("randomstate")
	c.JSON(http.StatusOK, domain.GoogleLoginResponse{URL: url})
}

func (glc *GoogleLoginController) GoogleLoginCallback(c *gin.Context) {
	var request domain.GoogleLoginCallbackRequest

	if err := c.ShouldBindQuery(&request); err != nil {
		c.JSON(http.StatusBadRequest, domain.ErrorResponse{Message: err.Error()})
		return
	}

	if request.State != "randomstate" {
		c.JSON(http.StatusUnauthorized, domain.ErrorResponse{Message: "States don't match"})
		return
	}

	googleConfig := bootstrap.GoogleOAuthConfig

	token, err := glc.GoogleLoginUsecase.Exchange(c, googleConfig, request.Code)
	if err != nil {
		c.JSON(http.StatusUnauthorized, domain.ErrorResponse{Message: err.Error()})
		return
	}

	client := glc.GoogleLoginUsecase.Client(c, googleConfig, token)
	resp, err := client.Get("https://openidconnect.googleapis.com/v1/userinfo")
	if err != nil {
		c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
		return
	}
	defer resp.Body.Close()

	var userInfo domain.GoogleUser
	if err := json.NewDecoder(resp.Body).Decode(&userInfo); err != nil {
		c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
		return
	}

	user, err := glc.GoogleLoginUsecase.GetUserByEmail(c, userInfo.Email)
	if err != nil {
		user = domain.User{
			ID:         uuid.New().String(),
			Email:      userInfo.Email,
			Password:   sql.NullString{String: "", Valid: false},
			Name:       sql.NullString{String: userInfo.Name, Valid: true},
			Provider:   sql.NullString{String: "google", Valid: true},
			ProviderID: sql.NullString{String: userInfo.ID, Valid: true},
			CreatedAt:  time.Now().UTC(),
			UpdatedAt:  time.Now().UTC(),
		}
		if err := glc.GoogleLoginUsecase.Create(c, &user); err != nil {
			c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
			return
		}
	} else {
		if !user.ProviderID.Valid {
			if err := glc.GoogleLoginUsecase.UpdateProvider(c, user.ID, "google", userInfo.ID); err != nil {
				c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
				return
			}
		}
	}

	accessToken, err := glc.GoogleLoginUsecase.CreateAccessToken(&user, glc.Env.AccessTokenExpiryHour, glc.Env.AccessTokenSecret)
	if err != nil {
		c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
		return
	}

	refreshToken, err := glc.GoogleLoginUsecase.CreateRefreshToken(&user, glc.Env.RefreshTokenExpiryHour, glc.Env.RefreshTokenSecret)
	if err != nil {
		c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
		return
	}

	googleLoginCallbackResponse := domain.GoogleLoginCallbackResponse{
		AccessToken:  accessToken,
		RefreshToken: refreshToken,
	}

	c.JSON(http.StatusOK, googleLoginCallbackResponse)
}
