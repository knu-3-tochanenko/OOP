CREATE TYPE AUTO_CLASS AS ENUM ('A', 'B', 'C', 'D');

CREATE TYPE RIDE_STATUS AS ENUM ('WAITING', 'COMPLETED', 'REJECTED');

CREATE TYPE USER_ROLE AS ENUM ('DRIVER', 'ADMIN', 'USER');

CREATE TABLE Automobile
(
    id                   SERIAL PRIMARY KEY,
    seats                INTEGER DEFAULT 4,
    last_inspection_date DATE       NOT NULL,
    class                AUTO_CLASS NOT NULL
);

CREATE TABLE Driver
(
    id      SERIAL PRIMARY KEY,
    car_id  INTEGER REFERENCES Automobile (id),
    name    VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    role    USER_ROLE DEFAULT 'DRIVER'
);

CREATE TABLE Ride
(
    id         SERIAL PRIMARY KEY,
    car_id     INTEGER REFERENCES Automobile (id),
    booking_id INTEGER REFERENCES Booking (id),
    cost       INTEGER NOT NULL
);

CREATE TABLE Booking
(
    id          SERIAL PRIMARY KEY,
    min_class   AUTO_CLASS  DEFAULT 'D',
    depart      VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    min_seats   INTEGER     DEFAULT 1,
    status      RIDE_STATUS DEFAULT 'WAITING'
);

