CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    rental_id INTEGER,
    order_id INTEGER,
    mode VARCHAR(255),
    payment_date TIMESTAMP WITH TIME ZONE,
    amount DECIMAL(10, 2)
);