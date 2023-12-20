CREATE TABLE orders (
    orderId SERIAL PRIMARY KEY,
    book_id int,
    received_date TIMESTAMP WITH TIME ZONE,
    return_date TIMESTAMP WITH TIME ZONE
);