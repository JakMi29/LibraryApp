CREATE TABLE opinion (
    id SERIAL NOT NULL,
    book_id INT NOT NULL,
    rating INT CHECK(rating BETWEEN 1 AND 5),
    comment TEXT,
    PRIMARY KEY (id)
);