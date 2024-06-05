CREATE TABLE Customer (
                          customer_id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          phone VARCHAR(50),
                          cc VARCHAR(50),
                          nationality VARCHAR(50)
);

CREATE INDEX idx_customer_email ON Customer(email);
CREATE INDEX idx_customer_phone ON Customer(phone);
CREATE INDEX idx_customer_cc ON Customer(cc);
