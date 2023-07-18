CREATE TABLE oms_file_sources
(
    id               INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    file_name        VARCHAR NOT NULL,
    file_description VARCHAR,
    file_url         VARCHAR UNIQUE NOT NULL
);

CREATE TABLE oms_types
(
    id               INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type_name        VARCHAR UNIQUE NOT NULL,
    type_description VARCHAR UNIQUE NOT NULL
);

CREATE TABLE oms_products
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR UNIQUE NOT NULL,
    description VARCHAR        NOT NULL
);

CREATE TABLE oms_product_type
(
    product_id INTEGER,
    type_id    INTEGER,
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES oms_products (id),
    CONSTRAINT fk_type_id
        FOREIGN KEY (type_id)
            REFERENCES oms_types (id)
);

CREATE TABLE oms_product_file_sources
(
    product_id INTEGER,
    file_id INTEGER,
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES oms_products (id),
    CONSTRAINT fk_file_id
        FOREIGN KEY (file_id)
            REFERENCES oms_file_sources (id)
);