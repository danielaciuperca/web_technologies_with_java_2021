CREATE DATABASE if NOT EXISTS course13;

CREATE TABLE if NOT EXISTS destinations (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  country varchar(255) NOT NULL,
  destinationType varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE if NOT EXISTS holidays (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  accommodation varchar(255) NOT NULL,
  duration int(11) NOT NULL,
  transportation varchar(255) NOT NULL,
  price double NOT NULL,
  destinationId bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (destinationId) REFERENCES destinations (id)
);