package middleware

import (
	"net/http"
	"strings"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/util"
	"github.com/gin-gonic/gin"
)

func JwtAuthMiddleware(secret string) gin.HandlerFunc {
	return func(c *gin.Context) {
		authHeader := c.Request.Header.Get("Authorization")
		auth := strings.Split(authHeader, " ")
		if len(auth) == 2 {
			token := auth[1]
			isAuthorized, err := util.IsAuthorized(token, secret)
			if isAuthorized {
				userID, err := util.ExtractIDFromToken(token, secret)
				if err != nil {
					c.JSON(http.StatusUnauthorized, domain.ErrorResponse{Message: err.Error()})
					c.Abort()
					return
				}
				c.Set("x-user-id", userID)
				c.Next()
				return
			}
			c.JSON(http.StatusUnauthorized, domain.ErrorResponse{Message: err.Error()})
			c.Abort()
			return
		}
		c.JSON(http.StatusUnauthorized, domain.ErrorResponse{Message: "Not authorized"})
		c.Abort()
	}
}
