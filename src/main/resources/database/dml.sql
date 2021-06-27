INSERT INTO category (name_category) VALUES ('Мужское');
INSERT INTO category (name_category) VALUES ('Женское');
INSERT INTO category (name_category) VALUES ('Детское');

INSERT INTO product (title, price) VALUES ('Мужская футболка Nike', 1199.99);

INSERT INTO product_category (product_id, category_id) VALUES (1, 1);

SELECT title, name_category, price
FROM product_category
         INNER JOIN product ON product_category.product_id = product.product_id
         INNER JOIN category ON product_category.category_id = category.category_id;

INSERT INTO product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO product_category (product_id, category_id) VALUES (2, 4);

SELECT title, name_category, price
FROM product_category
         INNER JOIN product ON product_category.product_id = product.product_id
         INNER JOIN category ON product_category.category_id = category.category_id
WHERE name_category = 'Мужское';

INSERT INTO reservation(client_id, reservation_date) VALUES (1, '2020-07-16');