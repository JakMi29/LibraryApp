CREATE TABLE orders (
    orderId SERIAL PRIMARY KEY,
    book_id int,
    received_date TIMESTAMP WITH TIME ZONE,
    status VARCHAR(255),
    order_number VARCHAR(255)
);