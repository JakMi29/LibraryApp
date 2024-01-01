CREATE TABLE rental (
    id SERIAL PRIMARY KEY,
    book_id int,
    received_date TIMESTAMP WITH TIME ZONE,
    return_date TIMESTAMP WITH TIME ZONE,
    rental_period int,
    fee DECIMAL(10, 2),
    email VARCHAR(255)
);