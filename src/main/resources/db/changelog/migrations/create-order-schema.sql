CREATE TABLE oms_order_item
(
    id  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    total_price DECIMAL(18, 8) NOT NULL,
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES oms_products (id)
);

CREATE TABLE oms_order
(
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  user_id uuid NOT NULL,
  status VARCHAR NOT NULL,
  total_price DECIMAL(18, 8) NOT NULL,
  created_on TIMESTAMP NOT NULL,
  CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
        REFERENCES oms_users
);

