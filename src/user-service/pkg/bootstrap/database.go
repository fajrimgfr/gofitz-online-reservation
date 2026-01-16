package bootstrap

import (
	"context"
	"log"
	"time"

	"github.com/jmoiron/sqlx"

	_ "github.com/jackc/pgx/stdlib"
)

const (
	driverName = "pgx"
)

func NewPostgresDatabase(env *Env) *sqlx.DB {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	db, err := sqlx.ConnectContext(ctx, driverName, env.DBUrl)
	if err != nil {
		log.Fatal(err)
	}

	if err := db.Ping(); err != nil {
		log.Fatal(err)
	}

	return db
}
