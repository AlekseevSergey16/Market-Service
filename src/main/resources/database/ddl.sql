CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price DECIMAL NOT NULL
);

CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    name_category VARCHAR(100) NOT NULL
);

CREATE TABLE product_category (
    product_category_id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product(product_id),
    FOREIGN KEY(category_id) REFERENCES category(category_id)
);

CREATE TABLE client (
    client_id SERIAL PRIMARY KEY,
    name_client VARCHAR(100) NOT NULL
);

CREATE TABLE reservation (
    reservation_id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    reservation_date DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (client_id)
);

CREATE TABLE reservation_product (
    reservation_product_id SERIAL PRIMARY KEY,
    reservation_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);



