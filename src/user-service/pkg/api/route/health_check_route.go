package route

import (
	"time"

	"github.com/fajrimgfr/user-service/pkg/api/controller"
	"github.com/fajrimgfr/user-service/pkg/bootstrap"
	"github.com/gin-gonic/gin"
	"github.com/jmoiron/sqlx"
)

func NewHealthCheckRoute(env *bootstrap.Env, db *sqlx.DB, timeout time.Duration, group *gin.RouterGroup) {
	hcc := controller.HealthCheckController{
		Database: db,
	}
	group.GET("/health", hcc.HealthCheck)
}
