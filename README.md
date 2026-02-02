# Gofitz Online Reservation

Gofitz Online Reservation is a project designed to provide an online reservation system. This project aims to facilitate users in making reservations efficiently and effectively. Well originally this project was my college project built with react as a frontend and supabase
as a database. So i decided to refactor it into a production-like microservices architecture for learning purposes.

## Features

- **Authentication and Authorization**: Secure sign-up and login implemented using JWT & OAuth (Google OAuth).
- **Online Reservation System**: Users can reserve futsal fields with real-time availability checks.
- **High-Scale Backend Architecture**:
    - Idempotency key handling
    - Retry mechanism
    - Pessimistic locking for double-booking prevention
    - Distributed locking (Redis)
    - Event-driven communication (Kafka)
- **Microservices Architecture**: Each domain split into independent services (User, Field, Reservation, Payment (on-development), Available (on-development)).
- **Monitoring & Observability**: Integrated with Prometheus + Grafana (on-development).
- **Dockerized Development**: Fully runnable using docker compose.

## Technologies

This project is built using the following technologies:

- **Java**: The main programming language for backend development.
- **Go**: Used for certain services within the system.

## Installation

To install and run this project locally, follow these steps:
Running locally (docker):

1. Clone this repository:
   ```bash
   git clone https://github.com/fajrimgfr/gofitz-online-reservation.git
   ```
2. Navigate to project and docker directory
    ```
    cd gofitz-online-reservation/docker
    ```
3. Run docker compose
    ```
    docker compose up -d
    ```

## Project Structure
## 📂 Project Structure

```text
.
├── docker/                         # Dockerfile and docker-compose configurations
│   ├── docker-compose.yml
├── k8s/                            # Kubernetes manifests (Yamls)
│   ├── user-service.yaml
├── src/                            # Source code for all microservices
│   ├── availability-service/       # Java (Spring Boot) Microservice
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── Dockerfile
│   ├── field-service/              # Java (Spring Boot) Microservice
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── Dockerfile
│   ├── reservation-service/       # Java (Spring Boot) Microservice
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── Dockerfile
│   ├── payment-service/       # Java (Spring Boot) Microservice
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── Dockerfile
│   ├── user-service/              # Go Gin Microservice
│   │   ├── cmd/
│   │   ├── internal/
│   │   └── go.mod
│   │   └── Dockerfile
│   └── frontend-nextjs/      # Next.js Web Application
│       ├── public/
│       ├── src/
│       └── package.json
│       └── Dockerfile
└──  README.md                # Project documentation