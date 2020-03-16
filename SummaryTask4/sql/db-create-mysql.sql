SET NAMES utf8;

USE tour_agency;

DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS beach;
DROP TABLE IF EXISTS hotel_type;
DROP TABLE IF EXISTS service_type;
DROP TABLE IF EXISTS hotel_service;
DROP TABLE IF EXISTS hotel_facility;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS facility;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS transport_type;
DROP TABLE IF EXISTS place;
DROP TABLE IF EXISTS route;
DROP TABLE IF EXISTS transport;
DROP TABLE IF EXISTS tour_type;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS reservation_status;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS bonus;

CREATE TABLE role (
	role_id INT PRIMARY KEY,
	role_name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(40) NOT NULL UNIQUE,
	password VARCHAR(40) NOT NULL,
	is_banned BOOLEAN NOT NULL DEFAULT FALSE,
	discount_step INT NOT NULL,
	role_id INT NOT NULL REFERENCES roles(role_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE food (
	food_id INT PRIMARY KEY,
	food_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE beach (
	beach_id INT PRIMARY KEY,
	beach_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE hotel_type (
	hotel_type_id INT PRIMARY KEY,
	hotel_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE facility (
	facility_id INT PRIMARY KEY,
	facility_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE service_type (
	service_type_id INT PRIMARY KEY,
	service_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE service (
	service_id INT AUTO_INCREMENT PRIMARY KEY,
	service_name VARCHAR(20) NOT NULL UNIQUE,
	service_type_id INT NOT NULL REFERENCES service_type(service_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE hotel (
	hotel_id INT AUTO_INCREMENT PRIMARY KEY,
	hotel_name VARCHAR(50) NOT NULL,
	hotel_info TEXT NOT NULL,
	hotel_address VARCHAR(50) NOT NULL,
	hotel_tel VARCHAR(15) NOT NULL,
	hotel_site VARCHAR(50) NOT NULL,
	hotel_stars TINYINT(6) UNSIGNED NOT NULL,
	hotel_max_rooms INT NOT NULL,
	hotel_price DOUBLE NOT NULL,
	hotel_type_id INT NOT NULL REFERENCES hotel_type(hotel_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	food_id INT NOT NULL REFERENCES food(food_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	beach_id INT REFERENCES beach(beach_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE hotel_service (
	hotel_id INT NOT NULL,
	service_id INT NOT NULL,
	FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	FOREIGN KEY (service_id) REFERENCES service(service_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	PRIMARY KEY(hotel_id, service_id)
);

CREATE TABLE hotel_facility (
	hotel_id INT NOT NULL,
	facility_id INT NOT NULL,
	FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	FOREIGN KEY (facility_id) REFERENCES facility(facility_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	PRIMARY KEY(hotel_id, facility_id)
);

CREATE TABLE transport_type (
	transport_type_id INT PRIMARY KEY,
	transport_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE place (
	place_id INT AUTO_INCREMENT PRIMARY KEY,
	place_country VARCHAR(30) NOT NULL,
	place_city VARCHAR(30) NOT NULL,
	UNIQUE KEY (place_country, place_city)
);

CREATE TABLE route (
	route_id INT AUTO_INCREMENT PRIMARY KEY,
	route_from INT NOT NULL REFERENCES place(place_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	route_to INT NOT NULL REFERENCES place(place_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);

CREATE TABLE transport (
	transport_id INT AUTO_INCREMENT PRIMARY KEY,
	transport_code VARCHAR(50) NOT NULL,
	takeoff_time DATETIME NOT NULL,
	arrive_time DATETIME NOT NULL,
	max_places INT NOT NULL,
	transport_price DOUBLE NOT NULL,
	transport_type_id INT NOT NULL REFERENCES transport_type(transport_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	route_id INT NOT NULL REFERENCES route(route_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	UNIQUE KEY (transport_code, transport_type_id)
);

CREATE TABLE tour_type (
	tour_type_id INT PRIMARY KEY,
	tour_type_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE tour (
	tour_id INT AUTO_INCREMENT PRIMARY KEY,
	agency_procent DOUBLE NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	is_fired BOOLEAN NOT NULL DEFAULT FALSE,
	tour_max_discount DOUBLE NOT NULL,
	tour_type_id INT NOT NULL REFERENCES tour_type(tour_type_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	hotel_id INT NOT NULL REFERENCES hotel(hotel_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT, 
	transport_to_id INT NOT NULL REFERENCES transport(transport_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	transport_back_id INT NOT NULL REFERENCES transport(transport_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT, 
	UNIQUE KEY (start_date, end_date, hotel_id)
);

CREATE TABLE reservation_status(
	reservation_status_id INT PRIMARY KEY,
	reservation_status_name VARCHAR(20) NOT NULL
);

CREATE TABLE reservation (
	 reservation_id INT AUTO_INCREMENT PRIMARY KEY,
	 reserve_date DATETIME NOT NULL,
	 people_count TINYINT NOT NULL,
	 reservation_status_id INT NOT NULL REFERENCES reservation_status(reservation_status_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT,
	 tour_id INT NOT NULL REFERENCES tour(tour_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT,
	 user_id INT NOT NULL REFERENCES users(user_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT
);

CREATE TABLE bonus(
	bonus_id INT AUTO_INCREMENT PRIMARY KEY,
	discount DOUBLE NOT NULL,
	tour_id INT NOT NULL REFERENCES tour(tour_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT,
	user_id INT NOT NULL REFERENCES users(user_id)
	 	ON DELETE CASCADE
	 	ON UPDATE RESTRICT
);

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
INSERT INTO facility VALUES (1, "wifi");
INSERT INTO facility VALUES (2, "minibar");
INSERT INTO facility VALUES (3, "hairdryer");
INSERT INTO facility VALUES (4, "conditioner");
INSERT INTO facility VALUES (5, "tv");

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

SHOW TABLES;