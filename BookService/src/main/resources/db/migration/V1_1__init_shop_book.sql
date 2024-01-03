CREATE TABLE library_book (
        id SERIAL NOT NULL,
        name VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        quantity INT  NOT NULL,
        category VARCHAR(255) NOT NULL,
        publication_date INT NOT NULL,
        PRIMARY KEY (id)
    );