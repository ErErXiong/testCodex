CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_no VARCHAR(40) NOT NULL UNIQUE,
    customer_name VARCHAR(30) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    pet_type VARCHAR(30) NOT NULL,
    service_name VARCHAR(50) NOT NULL,
    appointment_time DATETIME NOT NULL,
    note VARCHAR(200),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
