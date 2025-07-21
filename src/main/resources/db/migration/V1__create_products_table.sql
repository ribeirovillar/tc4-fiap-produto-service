CREATE TABLE products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sku VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,

    CONSTRAINT uk_product_sku UNIQUE (sku)
);