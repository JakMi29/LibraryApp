CREATE TABLE shop_book (
        id SERIAL NOT NULL,
        name VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        quantity INT  NOT NULL,
        category VARCHAR(255) NOT NULL,
        publication_date INT NOT NULL,
        price DECIMAL(10, 2),
        PRIMARY KEY (id)
    );