CREATE TABLE listings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    currency VARCHAR(10),
    location VARCHAR(255),
    url VARCHAR(500)
);
