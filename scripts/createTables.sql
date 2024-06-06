-- Drop existing tables if they exist
DROP TABLE IF EXISTS Reservation CASCADE;
DROP TABLE IF EXISTS Bicycle CASCADE;
DROP TABLE IF EXISTS GPS CASCADE;
DROP TABLE IF EXISTS Customer CASCADE;
DROP TABLE IF EXISTS Shop CASCADE;

-- Create Shops table
CREATE TABLE Shop (
    shop_code SERIAL PRIMARY KEY,
    manager VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Create Customer table
CREATE TABLE Customer (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    cc VARCHAR(50),
    nationality VARCHAR(50)
);

-- Create Bicycles table
CREATE TABLE Bicycle (
    bicycle_id SERIAL PRIMARY KEY,
    id VARCHAR(255) UNIQUE NOT NULL,
    weight DOUBLE PRECISION,
    model VARCHAR(255),
    brand VARCHAR(255),
    gear_system VARCHAR(2) CHECK (gear_system IN ('1', '6', '18', '24')) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('free', 'occupied', 'in reserve', 'under maintenance')) NOT NULL,
    shop_code INT,
    FOREIGN KEY (shop_code) REFERENCES Shop(shop_code)
);

-- Create GPS table
CREATE TABLE GPS (
    number SERIAL PRIMARY KEY,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    battery INT NOT NULL,
    bicycle_id INT,
    FOREIGN KEY (bicycle_id) REFERENCES Bicycle(bicycle_id)
);

-- Create Reservations table
CREATE TABLE Reservation (
    reservation_number SERIAL PRIMARY KEY,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    bicycle_id INT NOT NULL,
    customer_id INT NOT NULL,
    price DOUBLE PRECISION,
    FOREIGN KEY (bicycle_id) REFERENCES Bicycle(bicycle_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);