INSERT INTO reservation(client_id, reservation_date) VALUES (1, '2020-07-16');

INSERT INTO reservation_product(reservation_id, product_id) VALUES (1, 1);

SELECT reservation.reservation_id, name_client, title, name_category, reservation_date
FROM client
         INNER JOIN reservation ON client.client_id = reservation.client_id
         INNER JOIN reservation_product ON reservation.reservation_id = reservation_product.reservation_id
         INNER JOIN product ON reservation_product.product_id = product.product_id
         INNER JOIN product_category on product.product_id = product_category.product_id
         INNER JOIN category on category.category_id = product_category.category_id
WHERE client.client_id = (SELECT client_id FROM client WHERE name_client = 'admin')
  AND reservation_date BETWEEN '2020-07-11' AND '2020-07-16';

SELECT name_client
FROM client
         INNER JOIN reservation ON client.client_id = reservation.client_id
         INNER JOIN reservation_product ON reservation.reservation_id = reservation_product.reservation_id
         INNER JOIN product ON reservation_product.product_id = product.product_id
WHERE title = 'Мужская футболка Nike' AND reservation_date BETWEEN '2020-07-10' AND '2020-07-20';

INSERT INTO reservation(client_id, reservation_date) SELECT client_id, '2020-07-11' FROM client WHERE name_client='admin';

SELECT reservation.reservation_id, reservation_date, name_client, title, name_category
FROM client
         INNER JOIN reservation ON client.client_id = reservation.client_id
         INNER JOIN reservation_product ON reservation.reservation_id = reservation_product.reservation_id
         INNER JOIN product ON reservation_product.product_id = product.product_id
         INNER JOIN product_category on product.product_id = product_category.product_id
         INNER JOIN category on category.category_id = product_category.category_id
WHERE client.client_id = (SELECT client_id FROM client WHERE name_client = 'admin')
  AND reservation_date BETWEEN '2020-07-11' AND '2020-07-16';