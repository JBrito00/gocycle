--TABLE:Shops
CREATE TABLE Shops (
    code INT PRIMARY KEY,
    manager_id INT,
    address VARCHAR(255),
    locality VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

--TABLE:: GPS
CREATE TABLE GPS (
    serial_number INT PRIMARY KEY,
    latitude DECIMAL(9, 6),
    longitude DECIMAL(9, 6),
    battery_percentage DECIMAL(5, 2)
);

--TABLE: Bicycles
CREATE TABLE Bicycles (
    identifier INT PRIMARY KEY,
    weight INT,
    model VARCHAR(255),
    brand VARCHAR(255),
    gear_system INT CHECK (gear_system IN (1, 6, 18, 24)),
    status VARCHAR(50) CHECK (status IN ('free', 'occupied', 'in reserve', 'under maintenance')),
    gps_serial_number INT,
    type VARCHAR(50) CHECK (type IN ('classic', 'electric')),
    range INT,
    max_speed INT,
    number_of_gears INT CHECK (number_of_gears BETWEEN 0 AND 5),
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (gps_serial_number) REFERENCES GPS (serial_number)
);

--TABLE: Customers
CREATE TABLE Customers (
    identifier INT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20),
    id_document VARCHAR(50),
    nationality VARCHAR(50),
    active BOOLEAN DEFAULT TRUE
);

--TABLE: Reservations
CREATE TABLE Reservations (
    reservation_number INT PRIMARY KEY,
    shop_code INT,
    customer_id INT,
    bicycle_id INT,
    start_datetime TIMESTAMP,
    end_datetime TIMESTAMP,
    value DECIMAL(10, 2),
    FOREIGN KEY (shop_code) REFERENCES Shops (code),
    FOREIGN KEY (customer_id) REFERENCES Customers (identifier),
    FOREIGN KEY (bicycle_id) REFERENCES Bicycles (identifier)
);