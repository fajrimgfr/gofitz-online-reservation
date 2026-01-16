package main

import (
	"fmt"
	"time"

	"github.com/fajrimgfr/user-service/pkg/api/route"
	"github.com/fajrimgfr/user-service/pkg/bootstrap"
	"github.com/gin-gonic/gin"
)

func main() {
	env := bootstrap.NewEnv()

	db := bootstrap.NewPostgresDatabase(env)
	defer db.Close()

	bootstrap.NewGoogleOAuthConfig(env)

	timeout := time.Duration(env.ContextTimeout) * time.Second

	router := gin.Default()

	route.Setup(env, db, timeout, router)

	router.Run(fmt.Sprintf(":%s", env.Port))
}
