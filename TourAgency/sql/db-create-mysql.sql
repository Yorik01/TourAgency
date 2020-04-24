SET NAMES utf8;

USE tour_agency;

-- drop all tables
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS transport;
DROP TABLE IF EXISTS route;
DROP TABLE IF EXISTS place;
DROP TABLE IF EXISTS hotel_service;
DROP TABLE IF EXISTS hotel_facility;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS facility;
DROP TABLE IF EXISTS service_type;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS transport_type;
DROP TABLE IF EXISTS tour_type;
DROP TABLE IF EXISTS reservation_status;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS hotel_type;
DROP TABLE IF EXISTS beach;
DROP TABLE IF EXISTS food;

-- drop store procedure
DROP PROCEDURE IF EXISTS reserve_tour;

-- roles table
CREATE TABLE role (
	role_id INT PRIMARY KEY,
	role_name VARCHAR(10) NOT NULL UNIQUE
);

-- users table
CREATE TABLE users (

	user_id INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(40) NOT NULL UNIQUE,
	password VARCHAR(40) NOT NULL,
	is_banned TINYINT(1) NOT NULL,
	discount_step DOUBLE NOT NULL,
	role_id INT NOT NULL,
	CONSTRAINT role_id_fk FOREIGN KEY (role_id) 
	REFERENCES role(role_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

-- foods table
CREATE TABLE food (
	food_id INT PRIMARY KEY,
	food_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- beaches table
CREATE TABLE beach (
	beach_id INT PRIMARY KEY,
	beach_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- hotel types table
CREATE TABLE hotel_type (
	hotel_type_id INT PRIMARY KEY,
	hotel_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- facilities table
CREATE TABLE facility (
	facility_id INT PRIMARY KEY,
	facility_name VARCHAR(20) NOT NULL UNIQUE
);

-- service types table
CREATE TABLE service_type (
	service_type_id INT PRIMARY KEY,
	service_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- services table
CREATE TABLE service (
	service_id INT AUTO_INCREMENT PRIMARY KEY,
	service_name VARCHAR(20) NOT NULL UNIQUE,
	service_type_id INT NOT NULL,
	CONSTRAINT service_type_id_fk FOREIGN KEY (service_type_id)
	REFERENCES service_type(service_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

-- hotels table
CREATE TABLE hotel (
	hotel_id INT AUTO_INCREMENT PRIMARY KEY,
	hotel_name VARCHAR(50) NOT NULL UNIQUE,
	hotel_info TEXT NOT NULL,
	hotel_address VARCHAR(50) NOT NULL,
	hotel_tel VARCHAR(15) NOT NULL,
	hotel_site VARCHAR(50) NOT NULL,
	hotel_stars TINYINT(6) UNSIGNED NOT NULL,
	hotel_max_rooms INT NOT NULL,
	hotel_price DOUBLE NOT NULL,
	hotel_type_id INT NOT NULL,
	CONSTRAINT hotel_type_id_fk FOREIGN KEY (hotel_type_id) 
	REFERENCES hotel_type(hotel_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	food_id INT NOT NULL,
	CONSTRAINT food_id_fk FOREIGN KEY (food_id)
	REFERENCES food(food_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	beach_id INT,
	CONSTRAINT beach_id_fk FOREIGN KEY (beach_id)
	REFERENCES beach(beach_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

-- hotel services table
CREATE TABLE hotel_service (
	hotel_id INT NOT NULL,
	service_id INT NOT NULL,
	CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id)
	REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	CONSTRAINT service_id_fk FOREIGN KEY (service_id)
	REFERENCES service(service_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	PRIMARY KEY(hotel_id, service_id)
);

-- hotel facilities table
CREATE TABLE hotel_facility (
	hotel_id INT NOT NULL,
	facility_id INT NOT NULL,
	CONSTRAINT hotel_id_fk_1 FOREIGN KEY (hotel_id)
	REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	CONSTRAINT facility_id_fk FOREIGN KEY (facility_id)
	REFERENCES facility(facility_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	PRIMARY KEY(hotel_id, facility_id)
);

-- transport types table
CREATE TABLE transport_type (
	transport_type_id INT PRIMARY KEY,
	transport_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- places table
CREATE TABLE place (
	place_id INT AUTO_INCREMENT PRIMARY KEY,
	place_country VARCHAR(30) NOT NULL,
	place_city VARCHAR(30) NOT NULL,
	UNIQUE KEY (place_country, place_city)
);

-- routes table
CREATE TABLE route (
	route_id INT AUTO_INCREMENT PRIMARY KEY,
	route_from INT NOT NULL,
	CONSTRAINT route_from_key FOREIGN KEY (route_from)
	REFERENCES place(place_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	route_to INT NOT NULL,
	CONSTRAINT route_to_fk FOREIGN KEY (route_to) 
	REFERENCES place(place_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

-- transports table
CREATE TABLE transport (
	transport_id INT AUTO_INCREMENT PRIMARY KEY,
	transport_code VARCHAR(50) NOT NULL,
	takeoff_time DATETIME NOT NULL,
	arrive_time DATETIME NOT NULL,
	max_places INT NOT NULL,
	transport_price DOUBLE NOT NULL,
	transport_type_id INT NOT NULL,
	CONSTRAINT transport_type_id_fk FOREIGN KEY (transport_type_id)
	REFERENCES transport_type(transport_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	route_id INT NOT NULL,
	CONSTRAINT route_Id_fk FOREIGN KEY (route_id)
	REFERENCES route(route_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	UNIQUE KEY (transport_code, transport_type_id)
);

-- tour types table
CREATE TABLE tour_type (
	tour_type_id INT PRIMARY KEY,
	tour_type_name VARCHAR(20) NOT NULL UNIQUE
);

-- tours table
CREATE TABLE tour (
	tour_id INT AUTO_INCREMENT PRIMARY KEY,
	agency_procent DOUBLE NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	is_fired TINYINT(1) NOT NULL,
	tour_max_discount DOUBLE NOT NULL,
	tour_type_id INT NOT NULL,
	CONSTRAINT tour_type_id_fk FOREIGN KEY (tour_type_id) 
	REFERENCES tour_type(tour_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	hotel_id INT NOT NULL,
	CONSTRAINT hotel_id_fk_2 FOREIGN KEY (hotel_id)
	REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT, 
	transport_to_id INT NOT NULL,
	CONSTRAINT transport_to_id_fk FOREIGN KEY (transport_to_id)
	REFERENCES transport(transport_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	transport_back_id INT NOT NULL,
	CONSTRAINT transport_back_id_fk FOREIGN KEY (transport_back_id)
	REFERENCES transport(transport_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT, 
	UNIQUE KEY (start_date, end_date, hotel_id)
);

-- reservation statuses table
CREATE TABLE reservation_status(
	reservation_status_id INT PRIMARY KEY,
	reservation_status_name VARCHAR(20) NOT NULL
);

-- reservations table
CREATE TABLE reservation (
	 reservation_id INT AUTO_INCREMENT PRIMARY KEY,
	 reserve_date DATETIME NOT NULL,
	 people_count TINYINT NOT NULL,
	 discount DOUBLE NOT NULL DEFAULT 0,
	 reservation_status_id INT NOT NULL,
	 CONSTRAINT reservation_status_id_fk FOREIGN KEY (reservation_status_id)
	 REFERENCES reservation_status(reservation_status_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT,
	 tour_id INT NOT NULL,
	 CONSTRAINT tour_id_fk FOREIGN KEY (tour_id)
	 REFERENCES tour(tour_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT,
	 user_id INT NOT NULL,
	 CONSTRAINT user_id_fk FOREIGN KEY (user_id)
	 REFERENCES users(user_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT
);

-- comments table
CREATE TABLE comment (
	comment_id INT AUTO_INCREMENT PRIMARY KEY,
	comment_date DATETIME NOT NULL,
	comment_text TEXT NOT NULL,
	comment_mark TINYINT(10) NOT NULL,
	user_id INT NOT NULL,
	CONSTRAINT user_id_comment_fk FOREIGN KEY (user_id) 
	REFERENCES users(user_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	tour_id INT NOT NULL,
	CONSTRAINT tour_id_comment_fk FOREIGN KEY (tour_id) 
	REFERENCES tour(tour_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

DELIMITER $$
-- stored procedure to reserve a tour
CREATE PROCEDURE reserve_tour (IN user_id INT, IN tour_id INT, IN people_count INT, IN reservation_status_id INT)
BEGIN
	DECLARE max_discount DOUBLE;
	DECLARE current_discount DOUBLE;
	DECLARE discount_step DOUBLE;
	DECLARE new_discount DOUBLE;
	
	DECLARE count_reservations INT;
	DECLARE free_places INT;
	
	SELECT t.tour_max_discount INTO max_discount FROM tour t WHERE t.tour_id = tour_id;
	SELECT IFNULL(MAX(r.discount), 0) INTO current_discount FROM reservation r WHERE r.user_id = user_id;
	SELECT u.discount_step INTO discount_step FROM users u WHERE u.user_id = user_id;
	
	SELECT COUNT(reservation_id) INTO count_reservations FROM reservation r WHERE r.tour_id = tour_id; 
	
	SELECT LEAST(
			trt.max_places - count_reservations,
			trb.max_places - count_reservations,
			h.hotel_max_rooms - count_reservations) 
	INTO free_places FROM tour t 
		INNER JOIN transport trt ON t.transport_to_id = trt.transport_id 
		INNER JOIN transport trb ON t.transport_back_id = trb.transport_id
		INNER JOIN hotel h USING (hotel_id) 
			WHERE t.tour_id = tour_id;
	
	SET new_discount = current_discount + discount_step;
	
	IF free_places IS NULL THEN
		SIGNAL SQLSTATE '45000'	
			SET MESSAGE_TEXT = "This tour doesn't exist!";
	END IF;
	
	IF new_discount > max_discount THEN
		SET new_discount = current_discount;
	END IF;
	
	SELECT max_discount;
	SELECT current_discount;
	SELECT discount_step;
	SELECT new_discount;
	SELECT count_reservations;
	SELECT free_places;

	IF free_places >= people_count THEN
		INSERT INTO reservation VALUES (DEFAULT, NOW(), people_count, new_discount, reservation_status_id, tour_id, user_id);
	ELSE
		SIGNAL SQLSTATE '45001'	
				SET MESSAGE_TEXT = "There are no free places in the tour!";
	END IF;
	
END$$

DELIMITER ;

-- creating of indexes
CREATE INDEX fired_index ON tour(is_fired);
CREATE INDEX comment_index ON comment(tour_id, comment_date);
CREATE INDEX reservation_index ON reservation(tour_id);

-- set roles
INSERT INTO role VALUES (1, "CLIENT");
INSERT INTO role VALUES (2, "MANAGER");
INSERT INTO role VALUES (3, "ADMIN");

-- set types of hotels
INSERT INTO hotel_type VALUES (1, "MOTEL");
INSERT INTO hotel_type VALUES (2, "RESORTS");
INSERT INTO hotel_type VALUES (3, "HOSTEL");
INSERT INTO hotel_type VALUES (4, "BOUTIQUE");

-- set types of food
INSERT INTO food VALUES (1, "AL");
INSERT INTO food VALUES (2, "FB");
INSERT INTO food VALUES (3, "HB");
INSERT INTO food VALUES (4, "BB");
INSERT INTO food VALUES (5, "OB");

-- set types of beaches
INSERT INTO beach VALUES (1, "SAND");
INSERT INTO beach VALUES (2, "PEBBLE");
INSERT INTO beach VALUES (3, "PLATE");
INSERT INTO beach VALUES (4, "SAND_PEBBLE");

-- set types of services
INSERT INTO service_type VALUES (1, "SPORT");
INSERT INTO service_type VALUES (2, "ENTERTAINMENT");

-- set services
INSERT INTO service VALUES (DEFAULT, "GYM", 1);
INSERT INTO service VALUES (DEFAULT, "FITNES", 1);
INSERT INTO service VALUES (DEFAULT, "DIVING", 1);
INSERT INTO service VALUES (DEFAULT, "YOGA", 1);

INSERT INTO service VALUES (DEFAULT, "AQUAPARK", 2);
INSERT INTO service VALUES (DEFAULT, "DISCO", 2);
INSERT INTO service VALUES (DEFAULT, "RESTAURANT", 2);

-- set facilities
INSERT INTO facility VALUES (1, "WIFI");
INSERT INTO facility VALUES (2, "MINIBAR");
INSERT INTO facility VALUES (3, "HAIRDRYER");
INSERT INTO facility VALUES (4, "CONDITIONER");
INSERT INTO facility VALUES (5, "TV");

-- set types of tours
INSERT INTO tour_type VALUES (1, "RELAX");
INSERT INTO tour_type VALUES (2, "EXCURSION");
INSERT INTO tour_type VALUES (3, "SHOPPING");

-- set types of transports
INSERT INTO transport_type VALUES (1, "AIRPLANE");
INSERT INTO transport_type VALUES (2, "BUS");

-- set statuses of reservations
INSERT INTO reservation_status VALUES (1, "RESERVED");
INSERT INTO reservation_status VALUES (2, "BOUGHT");
INSERT INTO reservation_status VALUES (3, "REVOKED");

-- set resort countries and cities
INSERT INTO place VALUES (DEFAULT, "Egypt", "SharmElSheikh");
INSERT INTO place VALUES (DEFAULT, "Egypt", "Hurgada");
INSERT INTO place VALUES (DEFAULT, "Turkey", "Alania");
INSERT INTO place VALUES (DEFAULT, "Turkey", "Kimer");
INSERT INTO place VALUES (DEFAULT, "Tailand", "Samui");

-- set Urkraine cities
INSERT INTO place VALUES (DEFAULT, "Ukraine", "Kharkiv");
INSERT INTO place VALUES (DEFAULT, "Ukraine", "Lviv");
INSERT INTO place VALUES (DEFAULT, "Ukraine", "Kiev");
INSERT INTO place VALUES (DEFAULT, "Ukraine", "Dnepr");
INSERT INTO place VALUES (DEFAULT, "Ukraine", "Odesa");

-- set admin for password '1234'
INSERT INTO users VALUES (DEFAULT, "admin@touragency.com", "81dc9bdb52d04dc20036dbd8313ed055", 0, 0, 3);

SHOW TABLES;