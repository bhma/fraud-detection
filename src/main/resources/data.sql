CREATE TABLE IF NOT EXISTS transaction (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location VARCHAR(255),
    status VARCHAR(50) DEFAULT 'PENDING',
    is_fraud BOOLEAN DEFAULT FALSE
);

INSERT INTO transaction (user_id, amount, currency, transaction_date, location, status, is_fraud) VALUES
(1, 250.75, 'USD', '2025-02-13 12:30:00', 'New York', 'COMPLETED', false),
(2, 1000.00, 'BRL', '2025-02-13 14:45:00', 'São Paulo', 'PENDING', false),
(3, 500.50, 'EUR', '2025-02-13 16:00:00', 'Berlin', 'COMPLETED', true),
(4, 1500.25, 'USD', '2025-02-13 18:20:00', 'Los Angeles', 'FAILED', false);
