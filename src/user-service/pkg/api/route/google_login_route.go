package route

import (
	"time"

	"github.com/fajrimgfr/user-service/pkg/api/controller"
	"github.com/fajrimgfr/user-service/pkg/bootstrap"
	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/repository"
	"github.com/fajrimgfr/user-service/pkg/usecase"
	"github.com/gin-gonic/gin"
	"github.com/jmoiron/sqlx"
)

func NewGoogleLoginRouter(env *bootstrap.Env, db *sqlx.DB, timeout time.Duration, group *gin.RouterGroup) {
	glc := controller.GoogleLoginController{
		Env: env,
	}
	group.GET("auth/google", glc.GoogleLogin)
}

func NewGoogleLoginCallbackRouter(env *bootstrap.Env, db *sqlx.DB, timeout time.Duration, group *gin.RouterGroup) {
	ur := repository.NewUserRepository(db, domain.UserCollection)
	glu := usecase.NewGoogleLoginUsecase(ur, timeout)
	glc := controller.GoogleLoginController{
		GoogleLoginUsecase: glu,
		Env:                env,
	}
	group.GET("auth/google/callback", glc.GoogleLoginCallback)
}
