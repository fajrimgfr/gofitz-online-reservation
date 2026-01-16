package domain

import "context"

type RefreshTokenRequest struct {
	RefreshToken string `form:"refreshToken" binding:"required"`
}

type RefreshTokenResponse struct {
	AccessToken  string `json:"accessToken"`
	RefreshToken string `json:"refreshToken"`
}

type RefreshTokenUsecase interface {
	GetUserByID(c context.Context, id string) (User, error)
	ExtractIDFromToken(requestToken string, secret string) (string, error)
	CreateAccessToken(user *User, expiry int, secret string) (string, error)
	CreateRefreshToken(user *User, expiry int, secret string) (string, error)
}
