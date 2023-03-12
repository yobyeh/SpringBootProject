CREATE TABLE Books(
    id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    book_name VARCHAR(255) NOT NULL,
    book_description TEXT,
    price DECIMAL(10,2),
    author_id INT,
    genre VARCHAR(255),
    publisher VARCHAR(255),
    year_published YEAR,
    copies_sold INT,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

