package domain

import (
	"context"
	"net/http"

	"golang.org/x/oauth2"
)

type GoogleLoginResponse struct {
	URL string `json:"url"`
}

type GoogleLoginCallbackRequest struct {
	State string `form:"state" binding:"required"`
	Code  string `form:"code" binding:"required"`
}

type GoogleLoginCallbackResponse struct {
	AccessToken  string `json:"accessToken"`
	RefreshToken string `json:"refreshToken"`
}

type GoogleUser struct {
	ID    string `json:"sub"`
	Email string `json:"email"`
	Name  string `json:"name"`
}

type GoogleLoginUsecase interface {
	Exchange(c context.Context, config *oauth2.Config, code string) (*oauth2.Token, error)
	Client(c context.Context, config *oauth2.Config, token *oauth2.Token) *http.Client
	GetUserByEmail(c context.Context, email string) (User, error)
	Create(c context.Context, user *User) error
	UpdateProvider(c context.Context, id string, provider string, providerID string) error
	CreateAccessToken(user *User, expiry int, secret string) (string, error)
	CreateRefreshToken(user *User, expiry int, secret string) (string, error)
}
