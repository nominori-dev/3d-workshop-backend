CREATE TABLE oms_customers (
    id uuid PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL
)