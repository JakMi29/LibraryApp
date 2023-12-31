CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    reference_id INTEGER,
    payment_mode VARCHAR(255),
    transaction_type VARCHAR(255),
    payment_date TIMESTAMP WITH TIME ZONE,
    amount DECIMAL(10, 2)
);