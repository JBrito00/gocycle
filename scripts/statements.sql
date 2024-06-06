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
AS
$$
BEGIN
    INSERT INTO (name, address, email, phone, cc, nationality)
    VALUES (name, address, email, phone, cc, nationality);
END;
$$;

--alternative alinea C create customer
create or replace function alternativeCreateCustomer()
    RETURNS integer AS
$$
declare
    sp_points int := 0;
    mp_points int := 0;
begin
    if (player_id is null) then
        raise exception 'PID não pode ser nulo';
    end if;

    if ((player_id) not in (SELECT jogador.pid FROM jogador)) then
        raise exception 'Jogador não existe';
    end if;

    sp_points = (SELECT sum(pontuacao)
                 FROM singleplayer
                 WHERE pid = player_id);
    mp_points = (SELECT sum(p_pontuacao)
                 FROM multiplayer
                 WHERE pid = player_id);
    if (mp_points is null) then
        mp_points := 0;
    end if;
    if (sp_points is null) then
        sp_points := 0;
    end if;
    RETURN sp_points + mp_points;

END;
$$ LANGUAGE plpgsql;


-- alinea E make booking procedure
CREATE OR REPLACE PROCEDURE makeBooking(
    IN p_customerId INTEGER,
    IN p_bikeId INTEGER,
    IN p_startDateTime VARCHAR,
    IN p_endDateTime VARCHAR,
    IN p_price DOUBLE PRECISION
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    -- Assuming you have a bookings table with the necessary columns
    INSERT INTO reservation (start_date, end_date, bicycle_id, customer_id, price)
    VALUES (p_startDateTime, p_endDateTime,  p_bikeId, p_customerId, p_price);

    -- You can add any additional logic here, like updating availability, etc.

    COMMIT;
END;
$$;

DROP PROCEDURE IF EXISTS makeBooking;

-- alinea C check bike availability function
CREATE OR REPLACE FUNCTION checkBikeAvailability(
    IN p_bikeId INTEGER,
    IN p_dateTime VARCHAR
)
    RETURNS BOOLEAN
    LANGUAGE plpgsql
AS
$$
BEGIN
    -- Assuming there is a 'reservations' table with 'bike_id' and 'end_date'
    -- and we want to check if there are any reservations for this bike after the given date

    IF EXISTS (SELECT 1
               FROM reservation
               WHERE bicycle_id = p_bikeId
                 AND end_date  >= p_dateTime::timestamp
                 AND start_date <= p_dateTime::timestamp) THEN
        RETURN FALSE; -- Bike is not available
    ELSE
        RETURN TRUE; -- Bike is available
    END IF;
END;
$$;



