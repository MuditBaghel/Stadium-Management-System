-- Create the database
CREATE DATABASE IF NOT EXISTS stadium_db;
USE stadium_db;

-- Drop existing tables (optional for reset)
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    role VARCHAR(20) DEFAULT 'user'
);

-- Create events table
CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    match_date DATE NOT NULL,
    time VARCHAR(20),
    venue VARCHAR(100),
    total_seats INT,
    promo_code VARCHAR(20)
);

-- Create bookings table with foreign keys
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    event_id INT,
    seat_type VARCHAR(50),
    promo_code VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

-- Insert sample users
INSERT INTO users (full_name, username, password, email, phone, role) VALUES
('Rohan Kapoor', 'rohan', 'pass123', 'rohan@example.com', '9876543210', 'user'),
('Neha Bansal', 'neha', 'admin123', 'neha@example.com', '9123456780', 'admin');

-- Insert sample events
INSERT INTO events (event_name, match_date, time, venue, total_seats, promo_code) VALUES
('Cricket World Cup Final', '2025-08-15', '6:00 PM', 'Mumbai Stadium', 500, 'WC2025'),
('Football Championship', '2025-09-10', '8:30 PM', 'Delhi Arena', 300, 'FOOTBALL50'),
('Live Concert', '2025-07-30', '7:00 PM', 'Bangalore Grounds', 200, 'MUSIC10');

-- Insert sample bookings
INSERT INTO bookings (user_id, event_id, seat_type, promo_code) VALUES
(1, 1, 'VIP', 'WC2025'),
(2, 2, 'Regular', 'FOOTBALL50');
