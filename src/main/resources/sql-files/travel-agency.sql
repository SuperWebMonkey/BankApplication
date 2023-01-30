USE mydb;
SHOW tables;

SELECT * FROM customers;
SELECT * FROM customers WHERE customer_id = 27;
SELECT * FROM staff;

/* Insert Statements */
INSERT INTO customers (first_name, last_name, phone)
VALUES ("Sam", "Peterson", "757-180-8001");

INSERT INTO customers (first_name, last_name, phone)
VALUES ("Bob", "Masterson", "801-222-4004");

INSERT INTO customers (first_name, last_name, phone)
VALUES ("Alice", "Liddel", "799-841-800");

INSERT INTO customers (first_name, last_name, phone)
VALUES ("Norman", "Bates", "910-891-0011");

INSERT INTO customers (first_name, last_name, phone)
VALUES ("Bob", "Hope", "620-441-9011");

INSERT INTO staff (first_name, last_name)
VALUES ("Tim", "Hawke");

INSERT INTO staff (first_name, last_name)
VALUES ("Sabrina", "Williams");

INSERT INTO staff (first_name, last_name)
VALUES ("Michael", "Lucas");

INSERT INTO countries(country_name)
VALUES("United States");

INSERT INTO countries(country_name)
VALUES("Canada");

INSERT INTO cities (city_name, country_id)
VALUES ("Los Angeles", 1);

INSERT INTO cities (city_name, country_id)
VALUES ("San Francisco", 1);

INSERT INTO hotels (hotel_name, price, city_id)
VALUES ("Morongo", 15.00, 1);

INSERT INTO airline_companies (company_name)
VALUES ("Delta");

INSERT INTO flights (price, airline_id, origin_city_id, destination_city_id)
VALUES (50.00, 1, 1, 2);

INSERT INTO tours (tour_name, hotel_id, flight_to_id, flight_from_id)
VALUES ("Disney Land", 1, 1, 1);

INSERT INTO orders(payment, customer_id, staff_id, tours_id, status_id, payment_Id, driving_companies_driving_id)
VALUES (20.0, 1,1,1,1,1,1);

/* Update Statements */
UPDATE customers
SET first_name = "David"
WHERE customer_id = 1;

UPDATE customers
SET last_name = "Martinez"
WHERE customer_id = 3;

UPDATE customers
SET last_name="Wonderland"
WHERE customer_id = 3 && first_name = "Alice";

UPDATE customers
SET last_name="Builder"
WHERE customer_id = 8 && first_name = "Bob";

UPDATE customers
SET first_name="Mad"
WHERE customer_id = 4;

UPDATE customers
SET last_name = "Max"
WHERE customer_id = 4 && first_Name = "Mad";

UPDATE staff
SET first_name="Tony"
WHERE staff_id = 1 && last_name = "Hawke";

UPDATE staff
SET first_name="Tony"
WHERE staff_id = 3 && first_name = "Michael";

UPDATE countries
SET country_name = "Mexico"
WHERE country_id = 2;

UPDATE countries
SET country_name = "Brazil"
WHERE country_id = 2;

UPDATE countries
SET country_name = "Argentina"
WHERE country_id = 2;

/* Delete Statements*/
Delete FROM customers WHERE first_name="Sam" && customer_id=4;
DELETE FROM customers WHERE customer_id=5;
DELETE FROM customers WHERE customer_id=6;
Delete FROM customers WHERE first_name="Bob" && customer_id=9;
Delete FROM customers WHERE first_name="Bob" && customer_id=10;
Delete FROM customers WHERE first_name="Sam" && customer_id=11;
Delete FROM customers WHERE last_name="Masterson" && customer_id=12;
Delete FROM customers WHERE last_name="Masterson" && customer_id=12;
Delete FROM customers WHERE last_name="Liddel" && customer_id=13;
Delete FROM customers WHERE last_Name ="Bates" && customer_id=14;

/* Alter Table */
ALTER TABLE staff
ADD email varchar(255);

ALTER TABLE staff
DROP COLUMN  email;

ALTER TABLE staff
ADD zipcode varchar(255);

ALTER TABLE staff CHANGE zipcode zip_code varchar(255);

ALTER TABLE staff
DROP COLUMN zip_code;

# big join statement
SELECT *
FROM orders as o
LEFT JOIN order_status as o_s ON o.status_id = o_s.status_id
RIGHT JOIN staff as s ON s.staff_id = o.staff_id
LEFT JOIN payments as pay ON pay.payment_id = o.payment_id
LEFT JOIN payment_types as pay_type ON pay.payment_type_id = pay_type.payment_type_id
RIGHT JOIN customers as c ON o.customer_id = c.customer_id
LEFT JOIN driving_companies as d_comp ON o.driving_companies_driving_id = d_comp.driving_id
LEFT JOIN tours as t ON t.tour_id = o.tours_id
LEFT JOIN hotels as h ON h.hotel_id = t.hotel_id
LEFT JOIN flights as f ON f.origin_city_id = t.flight_to_id
LEFT JOIN airline_companies as a_comp ON a_comp.company_Id = f.airline_id
LEFT JOIN cities as city ON city.city_id = h.city_id
LEFT JOIN countries as cou ON cou.country_id = city.country_id;

# join statements
SELECT *
FROM customers as c1
INNER JOIN customers as c2 ON c1.customer_id = c2.customer_id;

SELECT *
FROM customers as c1
RIGHT JOIN customers as c2 ON c1.customer_id = c2.customer_id;

SELECT *
FROM countries as cou
LEFT JOIN cities as city ON cou.country_id = city.country_id;

SELECT *
FROM hotels AS h
INNER JOIN tours as t ON t.hotel_id = h.hotel_id;

SELECT c.first_name, c.last_name
FROM customers as c
LEFT JOIN orders as o ON c.customer_id = o.customer_id;

/* Select Statements */
SELECT * FROM customers ORDER BY last_name DESC;

/* aggregate functions and group by without having */
SELECT COUNT(amount)
FROM payments
GROUP BY (payment_type_id);

SELECT MIN(amount)
FROM payments
GROUP BY (payment_type_id);

SELECT MAX(amount)
FROM payments
GROUP BY (payment_type_id);

SELECT payment_id, AVG(amount)
FROM payments
GROUP BY (payment_type_id);

SELECT flight_id, SUM(price)
FROM flights
GROUP BY (origin_city_id);

SELECT flight_id, COUNT(price)
FROM flights
GROUP BY (destination_city_id);

SELECT hotel_id, hotel_name, MAX(price)
FROM hotels
GROUP BY (hotel_name);

/* aggregate functions and group by with having */
SELECT hotel_id, hotel_name, MAX(price) as max_price
FROM hotels
GROUP BY (hotel_name)
HAVING max_price > 10;

SELECT hotel_id, hotel_name, MIN(price) as min_price
FROM hotels
GROUP BY (hotel_name)
HAVING min_price < 8;

SELECT hotel_id, hotel_name, COUNT(price) as c_price
FROM hotels
GROUP BY (hotel_name)
HAVING c_price >= 5 && c_price <= 15;

SELECT flight_id, COUNT(price) as c_price
FROM flights
GROUP BY (destination_city_id)
HAVING c_price <> 0;

SELECT driving_id, AVG(price) as p 
GROUP BY (cities_city_id)
HAVING p <= 20 or p >= 10;

SELECT driving_id, SUM(price) as s
GROUP BY (cities_city_id)
HAVING s <= 100 and s >= 0;

SELECT driving_id, MIN(price) as m
GROUP BY (cities_city_id)
HAVING m < 20 and m >= 0;
