package usecase

import (
	"context"
	"time"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/util"
)

type signupUsecase struct {
	userRepository domain.UserRepository
	contextTimeout time.Duration
}

func NewSignupUsecase(ur domain.UserRepository, timeout time.Duration) domain.SignupUsecase {
	return &signupUsecase{userRepository: ur, contextTimeout: timeout}
}

func (su *signupUsecase) Create(c context.Context, user *domain.User) error {
	ctx, cancel := context.WithTimeout(c, su.contextTimeout)
	defer cancel()

	return su.userRepository.Create(ctx, user)
}

func (su *signupUsecase) GetUserByEmail(c context.Context, email string) (domain.User, error) {
	ctx, cancel := context.WithTimeout(c, su.contextTimeout)
	defer cancel()

	return su.userRepository.GetByEmail(ctx, email)
}

func (su *signupUsecase) CreateAccessToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateAccessToken(user, expiry, secret)
}

func (su *signupUsecase) CreateRefreshToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateRefreshToken(user, expiry, secret)
}
