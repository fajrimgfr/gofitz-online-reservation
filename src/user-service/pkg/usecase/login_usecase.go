package usecase

import (
	"context"
	"time"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/util"
)

type loginUsecase struct {
	userRepository domain.UserRepository
	contextTimeout time.Duration
}

func NewLoginUsecase(ur domain.UserRepository, timeout time.Duration) domain.LoginUsecase {
	return &loginUsecase{
		userRepository: ur,
		contextTimeout: timeout,
	}
}

func (lu *loginUsecase) GetUserByEmail(c context.Context, email string) (domain.User, error) {
	ctx, cancel := context.WithTimeout(c, lu.contextTimeout)
	defer cancel()

	return lu.userRepository.GetByEmail(ctx, email)
}

func (lu *loginUsecase) CreateAccessToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateAccessToken(user, expiry, secret)
}

func (lu *loginUsecase) CreateRefreshToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateRefreshToken(user, expiry, secret)
}
