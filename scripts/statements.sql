-- alinea A create customer
CREATE OR REPLACE PROCEDURE createCustomer(
    IN name VARCHAR,
    IN address VARCHAR,
    IN email VARCHAR,
    IN phone VARCHAR,
    IN cc VARCHAR,
    IN nationality VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO customer (name, address, email, phone, cc, nationality)
    VALUES (name, address, email, phone, cc, nationality);
END;
$$;


-- alinea E make booking procedure
CREATE OR REPLACE PROCEDURE makeBooking(
    IN p_bookingId INTEGER,
    IN p_customerId INTEGER,
    IN p_bikeId INTEGER,
    IN p_startDateTime TIMESTAMP,
    IN p_endDateTime TIMESTAMP,
    IN p_price NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Assuming you have a bookings table with the necessary columns
    INSERT INTO bookings (booking_id, customer_id, bike_id, start_date_time, end_date_time, price)
    VALUES (p_bookingId, p_customerId, p_bikeId, p_startDateTime, p_endDateTime, p_price);

    -- You can add any additional logic here, like updating availability, etc.

    COMMIT;
END;
$$;
