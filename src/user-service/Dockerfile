FROM golang:1.24-bullseye AS build-base

WORKDIR /app 
COPY go.mod go.sum ./
RUN --mount=type=cache,target=/go/pkg/mod \
  --mount=type=cache,target=/root/.cache/go-build \
  go mod download

FROM build-base AS dev

COPY . .
CMD ["go", "run", "./cmd/main.go"] 
# RUN go install github.com/cosmtrek/air@latest && \
#   go install github.com/go-delve/delve/cmd/dlv@latest
# COPY . .
# CMD ["air", "-c", ".air.toml"]

FROM build-base AS build-production

RUN useradd -u 1001 nonroot
COPY . .
RUN go build \
  -ldflags="-linkmode external -extldflags -static" \
  -tags netgo \
  -o healthcheck \
  ./cmd/healthcheck.go
RUN go build \
  -ldflags="-linkmode external -extldflags -static" \
  -tags netgo \
  -o user-service \
  ./cmd/main.go

FROM scratch AS production

ENV GIN_MODE=release
WORKDIR /
COPY --from=build-production /etc/passwd /etc/passwd
COPY --from=build-production /app/healthcheck healthcheck
COPY --from=build-production /app/user-service user-service
USER nonroot
EXPOSE 8080
CMD ["/user-service"]