CREATE TABLE oms_users
(
    id         uuid PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL
)