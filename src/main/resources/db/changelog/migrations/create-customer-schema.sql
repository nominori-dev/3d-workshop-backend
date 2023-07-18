CREATE TABLE oms_customers (
    id uuid,
    name VARCHAR (50) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (id)
              REFERENCES oms_users(id)
)