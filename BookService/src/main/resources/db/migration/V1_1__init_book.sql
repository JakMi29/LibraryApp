CREATE TABLE book (
        id SERIAL NOT NULL,
        name VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        quantity INT  NOT NULL,
        publication_data INT NOT NULL,
        PRIMARY KEY (id)
    );