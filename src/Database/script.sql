-- Suppression des tables si elles existent déjà pour éviter des erreurs lors de la création
DROP TABLE IF EXISTS reservations CASCADE;
DROP TABLE IF EXISTS pricing CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

-- Création de la table Roles
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

-- Création de la table Users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       status VARCHAR(100) NOT NULL,  -- Assurez-vous que 'status' soit défini quelque part, possiblement comme ENUM
                       role_id INTEGER NOT NULL,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Création de la table Rooms
CREATE TABLE rooms (
                       id SERIAL PRIMARY KEY,
                       room_number VARCHAR(50) NOT NULL,
                       room_type VARCHAR(100) NOT NULL,
                       availability BOOLEAN NOT NULL DEFAULT TRUE
);

-- Création de la table Pricing
CREATE TABLE pricing (
                         id SERIAL PRIMARY KEY,
                         room_id INTEGER NOT NULL,
                         season VARCHAR(100) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY (room_id) REFERENCES rooms(id)
);

-- Création de la table Reservations
CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              room_id INTEGER NOT NULL,
                              user_id INTEGER NOT NULL,
                              start_date DATE NOT NULL,
                              end_date DATE NOT NULL,
                              FOREIGN KEY (room_id) REFERENCES rooms(id),
                              FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insertions initiales pour la table des rôles
INSERT INTO roles (name) VALUES ('Admin'), ('Employee'), ('Client');

-- Vérification des insertions
SELECT * FROM roles;
