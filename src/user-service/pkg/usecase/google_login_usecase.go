package usecase

import (
	"context"
	"net/http"
	"time"

	"github.com/fajrimgfr/user-service/pkg/domain"
	"github.com/fajrimgfr/user-service/pkg/util"
	"golang.org/x/oauth2"
)

type googleLoginUsecase struct {
	userRepository domain.UserRepository
	contextTimeout time.Duration
}

func NewGoogleLoginUsecase(ur domain.UserRepository, timeout time.Duration) domain.GoogleLoginUsecase {
	return &googleLoginUsecase{
		userRepository: ur,
		contextTimeout: timeout,
	}
}

func (glu *googleLoginUsecase) Exchange(c context.Context, config *oauth2.Config, code string) (*oauth2.Token, error) {
	ctx, cancel := context.WithTimeout(c, glu.contextTimeout)
	defer cancel()

	return config.Exchange(ctx, code)
}

func (glu *googleLoginUsecase) Client(c context.Context, config *oauth2.Config, token *oauth2.Token) *http.Client {
	ctx, cancel := context.WithTimeout(c, glu.contextTimeout)
	defer cancel()

	return config.Client(ctx, token)
}

func (glu *googleLoginUsecase) GetUserByEmail(c context.Context, email string) (domain.User, error) {
	ctx, cancel := context.WithTimeout(c, glu.contextTimeout)
	defer cancel()

	return glu.userRepository.GetByEmail(ctx, email)
}

func (glu *googleLoginUsecase) Create(c context.Context, user *domain.User) error {
	ctx, cancel := context.WithTimeout(c, glu.contextTimeout)
	defer cancel()

	return glu.userRepository.Create(ctx, user)
}

func (glu *googleLoginUsecase) UpdateProvider(c context.Context, id string, provider string, providerID string) error {
	ctx, cancel := context.WithTimeout(c, glu.contextTimeout)
	defer cancel()

	return glu.userRepository.UpdateProvider(ctx, id, provider, providerID)
}

func (glu *googleLoginUsecase) CreateAccessToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateAccessToken(user, expiry, secret)
}

func (glu *googleLoginUsecase) CreateRefreshToken(user *domain.User, expiry int, secret string) (string, error) {
	return util.CreateRefreshToken(user, expiry, secret)
}
