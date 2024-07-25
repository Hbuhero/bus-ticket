CREATE TABLE bus (
  bus_id BIGINT PRIMARY KEY,
  departing_time TIME,
  arriving_time TIME,
  departing_date DATE,
  arriving_date DATE,
  departing_location VARCHAR(255),
  arriving_location VARCHAR(255),
  fee BIGINT,
  state VARCHAR(255)
);
