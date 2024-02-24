create table products (
    id VARCHAR(256) PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    unit_price DECIMAL NOT NULL
);

create table discount_deals (
    id VARCHAR(256) PRIMARY KEY,
    product_id VARCHAR(256) NOT NULL,
    deal_type VARCHAR(256) NOT NULL
);

create table basket_products (
    id VARCHAR(256) PRIMARY KEY,
    product_id VARCHAR(256) NOT NULL,
    customer_id VARCHAR(256) NOT NULL,
    quantity INTEGER NOT NULL
)
