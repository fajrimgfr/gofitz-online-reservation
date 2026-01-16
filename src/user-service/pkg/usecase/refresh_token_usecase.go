package usecase

import (
	"context"
	"time"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/util"
)

type refreshTokenUsecase struct {
	userRepository domain.UserRepository
	contextTimeout time.Duration
}

func NewRefreshTokenUsecase(ur domain.UserRepository, timeout time.Duration) domain.RefreshTokenUsecase {
	return &refreshTokenUsecase{
		userRepository: ur,
		contextTimeout: timeout,
	}
}

func (rtu *refreshTokenUsecase) GetUserByID(c context.Context, id string) (domain.User, error) {
	ctx, cancel := context.WithTimeout(c, rtu.contextTimeout)
	defer cancel()

	return rtu.userRepository.GetByID(ctx, id)
}

func (rtu *refreshTokenUsecase) ExtractIDFromToken(requestToken string, secret string) (string, error) {
	return util.ExtractIDFromToken(requestToken, secret)
}

func (rtu *refreshTokenUsecase) CreateAccessToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateAccessToken(user, expiry, secret)
}

func (rtu *refreshTokenUsecase) CreateRefreshToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateRefreshToken(user, expiry, secret)
}
