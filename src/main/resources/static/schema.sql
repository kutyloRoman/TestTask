CREATE TABLE user
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL
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
    price DECIMAL(10,4) NOT NULL,
    category_id INT NOT NULL,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES category (id)
);

INSERT INTO category(name, description)
VALUES ('t-shirt', 'football t-shirt'), ('sneakers', 'basketball sneakers');

INSERT INTO product(name, description, price, category_id)
VALUES
('Core Red', 'Example of text', 100.2,  1),
('TUC', 'Example of text', 69.3, 2);

INSERT iNTO user(username, password, is_admin)
VALUES ('roman', '$2a$10$2F2sfCuZuPek/g1AYnj0Huraw/d/F.zWOSagifbUpgpJZewEoQgny', true);