package domain

import "github.com/golang-jwt/jwt/v4"

type JwtCustomClaim struct {
	Name string `json:"name,omitempty"`
	ID   string `json:"id"`
	jwt.RegisteredClaims
}
