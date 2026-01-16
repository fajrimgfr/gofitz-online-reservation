package repository

import (
	"context"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/jmoiron/sqlx"
)

type userRepository struct {
	database   *sqlx.DB
	collection string
}

func NewUserRepository(db *sqlx.DB, collection string) domain.UserRepository {
	return &userRepository{
		database:   db,
		collection: collection,
	}
}

func (ur *userRepository) Create(c context.Context, user *domain.User) error {
	_, err := ur.database.NamedExecContext(c, `INSERT INTO users (id, email, password_hash, name, provider, provider_id, created_at, updated_at) VALUES (:id, :email, :password_hash, :name, :provider, :provider_id, :created_at, :updated_at)`, user)
	return err
}

func (ur *userRepository) GetByEmail(c context.Context, email string) (domain.User, error) {
	var user domain.User
	err := ur.database.GetContext(c, &user, `SELECT * FROM users WHERE email = $1`, email)
	return user, err
}

func (ur *userRepository) GetByID(c context.Context, id string) (domain.User, error) {
	var user domain.User
	err := ur.database.GetContext(c, &user, `SELECT * FROM users WHERE id = $1`, id)
	return user, err
}

func (ur *userRepository) UpdateProvider(c context.Context, id string, provider string, providerID string) error {
	_, err := ur.database.ExecContext(c, `UPDATE users SET provider = $1, provider_id = $2 WHERE id = $3`, provider, providerID, id)
	return err
}

func (ur *userRepository) UpdatePassword(c context.Context, id string, password string) error {
	_, err := ur.database.ExecContext(c, `UPDATE users SET password = $1 WHERE id = $2`, password, id)
	return err
}
