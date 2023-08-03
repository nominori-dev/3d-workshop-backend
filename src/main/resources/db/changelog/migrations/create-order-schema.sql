CREATE TABLE oms_order_item
(
    id  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES oms_products (id)
);

CREATE TABLE oms_order
(
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  email VARCHAR NOT NULL,
  status VARCHAR NOT NULL,
  total_price DECIMAL(18, 8) NOT NULL,
  created_on TIMESTAMP NOT NULL
);

CREATE TABLE oms_order_items
(
    order_id BIGINT NOT NULL,
    order_item_id BIGINT NOT NULL,
    CONSTRAINT fk_order_id
        FOREIGN KEY (order_id)
            REFERENCES oms_order,
    CONSTRAINT fk_order_item_id
        FOREIGN KEY (order_item_id)
            REFERENCES oms_order_item
);