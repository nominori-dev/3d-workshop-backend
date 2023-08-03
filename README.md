
# 3D Assets Workshop API
[![Powered by bdv.pw](https://img.shields.io/badge/powered%20by-bdv.pw-blueviolet)](https://bdv.pw/)

The 3D Assets Workshop REST API powers an online shop for managing and selling 3D assets effortlessly.

___

## Documentation

[Project reference](docs/project_reference.pdf)

SQL Scripts with demo data for development purposes can be found in [docs](docs) directory.
```
oms_product_types.sql oms_products.sql  oms_types.sql
```

# Tech stack

- Java 17
- Spring Boot 3
- SpringDoc
- OAuth2 Resource Server
- Spring Security
- Liquibase
- PostgreSQL
- Keycloak 21
- RabbitMQ
- Docker

## Local environment

To run this project locally you need Docker Engine and Docker Compose installed.

1. Clone this repository 
```bash
git clone https://github.com/nominori-dev/3d-workshop-backend
# cd into repository
cd 3d-workshop-backend
```
2. Start docker containers with compose
```bash
# Compose files stored in /docs folder. 
# This will be replaced soon with single development compose file
docker-compose --project-directory ./ -f docs/compose/keycloak/docker-compose.yml up -d
docker-compose --project-directory ./ -f docs/compose/postgres/docker-compose.yml up -d
docker-compose --project-directory ./ -f docs/compose/rabbitmq/docker-compose.yml up -d
```

3. Download dependencies and run project
```bash
./mvnw install 

# By default app will run with development ("dev") profile. So you don't need to provide env. variables
./mvnw spring-boot:run
```

## Environment variables

List of all environment variables you can find in **[.env.example](docs/.env.example)** file.


## Authors

- [@nominori-dev](https://www.github.com/nominori-dev)

