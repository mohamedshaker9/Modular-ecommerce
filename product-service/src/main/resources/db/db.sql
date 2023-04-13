CREATE TABLE ecommerce.product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO ecommerce.product (name, description, price, quantity)
VALUES ('iPhone 13 Pro', 'Apple iPhone 13 Pro 128GB, Graphite', 1299.00, 10),
       ('Samsung Galaxy S21', 'Samsung Galaxy S21 5G 128GB, Phantom Gray', 799.99, 5),
       ('Sony PlayStation 5', 'Sony PlayStation 5 Console', 499.99, 20),
       ('Bose QuietComfort 35 II', 'Bose QuietComfort 35 (Series II) Wireless Headphones', 299.00, 15),
       ('Nintendo Switch', 'Nintendo Switch Console with Neon Blue and Neon Red Joy‑Con', 299.99, 8);
