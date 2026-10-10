CREATE DATABASE lafogata CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'lafogata_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON lafogata.* TO 'lafogata_user'@'localhost';