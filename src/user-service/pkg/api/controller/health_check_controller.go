package controller

import (
	"net/http"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/gin-gonic/gin"
	"github.com/jmoiron/sqlx"
)

type HealthCheckController struct {
	Database *sqlx.DB
}

func (lc *HealthCheckController) HealthCheck(c *gin.Context) {
	if err := lc.Database.Ping(); err != nil {
		c.JSON(http.StatusInternalServerError, domain.ErrorResponse{Message: err.Error()})
		return
	}
	c.JSON(http.StatusOK, "ok")
}
