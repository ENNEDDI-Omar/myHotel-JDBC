-- Suppression des tables existantes pour éviter des erreurs lors de la création
DROP TABLE IF EXISTS reservations CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS pricing CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

-- Suppression des types ENUM si existants
DROP TYPE IF EXISTS user_status CASCADE;
DROP TYPE IF EXISTS room_type CASCADE;
DROP TYPE IF EXISTS season_type CASCADE;

-- Création de types ENUM pour 'status', 'room_type', et 'season'
CREATE TYPE user_status AS ENUM ('Active','Banned');
CREATE TYPE room_type AS ENUM ('Single', 'Double', 'Suite', 'Deluxe');
CREATE TYPE season_type AS ENUM ('high', 'low');

-- Création de la table Roles
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) unique NOT NULL
);

-- Création de la table Users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       status user_status NOT NULL DEFAULT 'Active',
                       role_id INTEGER NOT NULL,
                       FOREIGN KEY (role_id) REFERENCES roles(id),
                       loyalty_points INT DEFAULT 0,
                       department VARCHAR(255),
                       badge_number VARCHAR(50)
);

-- Création de la table Rooms
CREATE TABLE rooms (
                       id SERIAL PRIMARY KEY,
                       room_number VARCHAR(50) unique NOT NULL,
                       type room_type NOT NULL,
                       availability BOOLEAN NOT NULL DEFAULT TRUE
);

-- Index pour améliorer les recherches sur le numéro de chambre
CREATE INDEX idx_room_number ON rooms(room_number);

-- Création de la table Pricing
CREATE TABLE pricing (
                         id SERIAL PRIMARY KEY,
                         room_id INTEGER NOT NULL,
                         season season_type NOT NULL,
                         price INTEGER NOT NULL CHECK (price >= 0),
                         FOREIGN KEY (room_id) REFERENCES rooms(id)
);

-- Création de la table Reservations
CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              room_id INTEGER NOT NULL,
                              user_id INTEGER NOT NULL,
                              start_date DATE NOT NULL,
                              end_date DATE NOT NULL,
                              total_price DECIMAL(6, 2) CHECK ( total_price >= 0 ),
                              FOREIGN KEY (room_id) REFERENCES rooms(id),
                              FOREIGN KEY (user_id) REFERENCES users(id),

                              CHECK (end_date > start_date),
                              CHECK (start_date >= current_date),
                              CHECK (end_date <= start_date + interval '1 year')
);




--------------------------------------  INSERTION DES DONNEES: ---------------------------------------


-- Insertion des rôles
INSERT INTO roles (name) VALUES ('Admin'), ('Employee'), ('Client');

-- Insertion des utilisateurs
--INSERT INTO users (name, email, password, status, role_id, null, null, null) VALUES
                                                               --('John Doe', 'john.doe@example.com', 'hashedpassword', 'Active', (SELECT id FROM roles WHERE name = 'Admin')),
                                                               --('Jane Smith', 'jane.smith@example.com', 'hashedpassword', 'Active', (SELECT id FROM roles WHERE name = 'Employee')),
                                                               --('Alice Johnson', 'alice.johnson@example.com', 'hashedpassword', 'Active', (SELECT id FROM roles WHERE name = 'Client'));

-- Insertion des clients
--INSERT INTO clients (user_id, loyalty_points) VALUES
    --((SELECT id FROM users WHERE email = 'alice.johnson@example.com'), 100);

-- Insertion des employés
--INSERT INTO employees (user_id, department, badge_number) VALUES
    --((SELECT id FROM users WHERE email = 'jane.smith@example.com'), 'HR', '12345');

-- Insertion des chambres
INSERT INTO rooms (room_number, type, availability) VALUES
                                                        ('101', 'Single', TRUE),
                                                        ('102', 'Double', TRUE),
                                                        ('103', 'Suite', TRUE),
                                                        ('104', 'Deluxe', TRUE);


-- Insertion des prix
-- Insérer les prix pour chaque type de chambre et chaque saison
INSERT INTO pricing (room_id, season, price) VALUES
                                                 ((SELECT id FROM rooms WHERE room_number = '101'), 'low', 40),
                                                 ((SELECT id FROM rooms WHERE room_number = '101'), 'high', 60),
                                                 ((SELECT id FROM rooms WHERE room_number = '102'), 'low', 60),
                                                 ((SELECT id FROM rooms WHERE room_number = '102'), 'high', 90),
                                                 ((SELECT id FROM rooms WHERE room_number = '103'), 'low', 100),
                                                 ((SELECT id FROM rooms WHERE room_number = '103'), 'high', 150),
                                                 ((SELECT id FROM rooms WHERE room_number = '104'), 'low', 200),
                                                 ((SELECT id FROM rooms WHERE room_number = '104'), 'high', 300);


-- Insertion des réservations
--INSERT INTO reservations (room_id, user_id, start_date, end_date, total_price) VALUES
    --((SELECT id FROM rooms WHERE room_number = '101'), (SELECT user_id FROM clients WHERE user_id = (SELECT id FROM users WHERE email = 'alice.johnson@example.com')), CURRENT_DATE, CURRENT_DATE + interval '3 days', 240);



------------------------------------------ Vérification des jointures ------------------------------------


-- Requête pour trouver toutes les réservations avec détails des clients et des chambres


-- Requête pour obtenir le total des points de fidélité pour tous les clients


-- Requête pour vérifier les prix des chambres par saison


