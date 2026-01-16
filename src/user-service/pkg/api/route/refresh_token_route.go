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

func NewRefreshTokenRouter(env *bootstrap.Env, db *sqlx.DB, timeout time.Duration, group *gin.RouterGroup) {
	ur := repository.NewUserRepository(db, domain.UserCollection)
	rtu := usecase.NewRefreshTokenUsecase(ur, timeout)
	rtc := controller.RefreshTokenController{
		RefreshTokenUsecase: rtu,
		Env:                 env,
	}
	group.POST("/refresh", rtc.RefreshToken)
}
