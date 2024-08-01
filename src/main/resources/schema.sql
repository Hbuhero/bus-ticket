CREATE TABLE bus (
  bus_id BIGINT PRIMARY KEY,
  state VARCHAR(255)
);


CREATE TABLE journey (
  journey_id BIGINT PRIMARY KEY,
  departing_time TIME,
  arriving_time TIME,
  departing_date DATE,
  arriving_date DATE,
  departing_location VARCHAR(255),
  arriving_location VARCHAR(255),
  fee BIGINT,
  bus_id BIGINT,
  FOREIGN KEY (bus_id) REFERENCES Bus(bus_id)
  );

  CREATE TABLE admin (
  id BIGINT PRIMARY KEY,
name VARCHAR(255),
username VARCHAR(255),
password VARCHAR(255),
role VARCHAR(255)
  )