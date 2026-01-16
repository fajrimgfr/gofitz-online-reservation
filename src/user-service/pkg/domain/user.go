package domain

import (
	"context"
	"database/sql"
	"time"
)

const (
	UserCollection = "users"
)

type User struct {
	ID         string         `db:"id"`
	Email      string         `db:"email"`
	Password   sql.NullString `db:"password_hash"`
	Name       sql.NullString `db:"name"`
	Provider   sql.NullString `db:"provider"`
	ProviderID sql.NullString `db:"provider_id"`
	CreatedAt  time.Time      `db:"created_at"`
	UpdatedAt  time.Time      `db:"updated_at"`
}

type UserRepository interface {
	Create(c context.Context, user *User) error
	GetByEmail(c context.Context, email string) (User, error)
	GetByID(c context.Context, id string) (User, error)
	UpdateProvider(c context.Context, id string, provider string, providerID string) error
	UpdatePassword(c context.Context, id string, password string) error
	// Fetch(c context.Context) ([]User, error)
}
