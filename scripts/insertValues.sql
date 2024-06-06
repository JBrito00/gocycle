-- Insert Values in Tables

-- Criar valores de teste para a tabela Shop
INSERT INTO Shop (manager, address, phone, email)
VALUES
    ('Michael Brown', '789 Oak St', '555-1234', 'michael.brown@example.com'),
    ('Emily Davis', '321 Maple St', '555-5678', 'emily.davis@example.com');

-- Criar valores de teste para a tabela Customer
INSERT INTO Customer (name, address, email, phone, cc, nationality)
VALUES
    ('Robert Johnson', '456 Pine St', 'robert.johnson@example.com', '555-9012', '1234-5678-9012-3456', 'Canadian'),
    ('Sarah Taylor', '901 Cedar St', 'sarah.taylor@example.com', '555-1111', '9876-5432-1098-7654', 'Australian');

-- Criar valores de teste para a tabela Bicycle
INSERT INTO bicycle (id, weight, model, brand, gear_system, status, shop_code)
VALUES
    ('B004', 28.0, 'Electric Bike', 'Haibike', '6', 'free', 1),
    ('B005', 30.0, 'Cruiser Bike', 'Electra', '18', 'occupied', 1),
    ('B006', 26.0, 'Touring Bike', 'Surly', '24', 'in reserve', 2);

-- Criar valores de teste para a tabela GPS
INSERT INTO GPS (latitude, longitude, battery, bicycle_id)
VALUES
    (37.8024, -122.4056, 95, 4),
    (37.8135, -122.4267, 85, 5),
    (37.8246, -122.4478, 75, 6);

-- Criar valores de teste para a tabela Reservation
INSERT INTO Reservation (start_date, end_date, bicycle_id, customer_id, price)
VALUES
    ('2024-06-05 10:00:00', '2024-06-06 10:00:00', 2, 3, 40.0),
    ('2024-06-07 10:00:00', '2024-06-08 10:00:00', 1, 4, 50.0);
