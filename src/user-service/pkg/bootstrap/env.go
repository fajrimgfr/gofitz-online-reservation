package bootstrap

import (
	"log"

	"github.com/spf13/viper"
)

type Env struct {
	ContextTimeout         int
	Host                   string
	Port                   string
	DBUrl                  string
	AccessTokenExpiryHour  int
	RefreshTokenExpiryHour int
	AccessTokenSecret      string
	RefreshTokenSecret     string
	GoogleClientID         string
	GoogleClientSecret     string
}

func NewEnv() *Env {
	viper.SetDefault("CONTEXT_TIMEOUT", "2")
	viper.SetDefault("ACCESS_TOKEN_EXPIRY_HOUR", "2")
	viper.SetDefault("REFRESH_TOKEN_EXPIRY_HOUR", "168")
	viper.AutomaticEnv()

	env := &Env{
		Port:                   viper.GetString("PORT"),
		ContextTimeout:         viper.GetInt("CONTEXT_TIMEOUT"),
		Host:                   viper.GetString("HOST"),
		AccessTokenExpiryHour:  viper.GetInt("ACCESS_TOKEN_EXPIRY_HOUR"),
		RefreshTokenExpiryHour: viper.GetInt("REFRESH_TOKEN_EXPIRY_HOUR"),
		DBUrl:                  viper.GetString("DB_URL"),
		AccessTokenSecret:      viper.GetString("ACCESS_TOKEN_SECRET"),
		RefreshTokenSecret:     viper.GetString("REFRESH_TOKEN_SECRET"),
		GoogleClientID:         viper.GetString("GOOGLE_CLIENT_ID"),
		GoogleClientSecret:     viper.GetString("GOOGLE_CLIENT_SECRET"),
	}

	log.Printf("Config loaded: %+v\n", env)

	return env
}
