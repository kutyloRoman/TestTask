CREATE TABLE user
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL
);

CREATE TABLE category
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE product
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(32) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    category_id INT NOT NULL,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES category (id)
);

INSERT INTO category(name, description)
VALUES ('t-shirt', 'football t-shirt'), ('sneakers', 'basketball sneakers');

INSERT INTO Product(name, description, price, category_id)
VALUES
('Core Red', 'Example of text', 100.2,  1),
('TUC', 'Example of text', 69.3, 2);