SELECT DISTINCT title, name_category, price
FROM product
         INNER JOIN product_category pc on product.product_id = pc.product_id
         INNER JOIN category c on c.category_id = pc.category_id;

SELECT DISTINCT title, price
FROM product
WHERE product_id=1;

SELECT name_category
FROM category
         INNER JOIN product_category pc on category.category_id = pc.category_id
         INNER JOIN product p on p.product_id = pc.product_id
WHERE title='Бутсы мужские Nike Vapor 14';

SELECT name_category FROM category
                              INNER JOIN product_category ON category.category_id =product_category.category_id
WHERE name_category = 'Бутсы мужские Nike Vapor 14';

SELECT title
FROM product
         INNER JOIN product_category pc on product.product_id = pc.product_id
         INNER JOIN category c on c.category_id = pc.category_id
WHERE pc.product_id = 2;

INSERT INTO product (title, price) VALUES ('Мужская футболка Nike', 1199.99);

SELECT category_id FROM category WHERE name_category='Мужское';

DELETE FROM product WHERE product_id=20;

DELETE FROM product_category WHERE product_id=20;


SELECT p.product_id,  title
FROM category
         INNER JOIN product_category pc on category.category_id = pc.category_id
         INNER JOIN product p on pc.product_id = p.product_id
WHERE name_category='Мужское';

