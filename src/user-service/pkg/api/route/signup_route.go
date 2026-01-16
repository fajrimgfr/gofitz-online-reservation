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

func NewSignupRouter(env *bootstrap.Env, db *sqlx.DB, timeout time.Duration, group *gin.RouterGroup) {
	ur := repository.NewUserRepository(db, domain.UserCollection)
	su := usecase.NewSignupUsecase(ur, timeout)
	sc := controller.SignupController{
		SignupUsecase: su,
		Env:           env,
	}
	group.POST("/signup", sc.Signup)
}
