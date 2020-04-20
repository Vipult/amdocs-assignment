DROP TABLE IF EXISTS USER;

CREATE TABLE USER(
  USERNAME varchar(50) NOT NULL,
  PASSWORD varchar(65) NOT NULL,
  ROLE varchar(15) NOT NULL);

--DROP TABLE IF EXISTS employee;
--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS authorities;


--create table users (
--    username varchar(50) not null primary key,
--    password varchar(120) not null,
--    enabled boolean not null
--);

--create table authorities (
--    username varchar(50) not null,
--    authority varchar(50) not null,
--    foreign key (username) references users (username)
--);

--DROP TABLE IF EXISTS EMPLOYEE;


--CREATE TABLE EMPLOYEE(
--USERNAME varchar(50) NOT NULL,
--PASSWORD varchar(65) NOT NULL,
--ROLE varchar(15) NOT NULL);

