CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    book_id int,
    received_date TIMESTAMP WITH TIME ZONE,
    return_date TIMESTAMP WITH TIME ZONE,
    email VARCHAR(255)
);