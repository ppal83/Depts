CREATE DATABASE IF NOT EXISTS dept;
USE dept;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `dept`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `dept` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `birth_date` date NOT NULL,
 `hire_date` date NOT NULL,
 `address` varchar(30) NOT NULL,
 `email` varchar(30) NOT NULL,
 `dept_id` int(3) unsigned NOT NULL,
 `salary` int(11) unsigned NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `name` (`name`),
 UNIQUE KEY `email` (`email`),
 KEY `dept_id` (`dept_id`),
 CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE  TABLE `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(30) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO dept (name) VALUES
('Sales'),
('IT development'),
('Implementations'),
('Administrative'),
('HR'),
('Marketing');

INSERT INTO employee (name,birth_date,hire_date,address,email,dept_id,salary) VALUES
('Oleg Lyashko','1972-11-12', CURRENT_DATE, 'Sumskaya, 40','111@ukr.net',1,3000),
('Arseniy Yatseniuk','1975-09-22', CURRENT_DATE, 'Sumskaya, 30','222@ukr.net',2,3500),
('Petr Poroshenko','1963-07-14', CURRENT_DATE, 'pr.Lenina, 58','333@ukr.net',3,4000),
('Arsen Avakov','1964-01-18', CURRENT_DATE, 'Pushkinskaya, 17','444@ukr.net',4,4500),
('Oleksandr Turchinov','1960-02-13', CURRENT_DATE, 'pr.Gagarina, 167','555@ukr.net',5,5000),
('Anatoliy Gritsenko','1961-03-15', CURRENT_DATE, 'pr.Moskovskiy, 223','666@ukr.net',3,4900),
('Igor Kolomoyskiy','1961-04-19', CURRENT_DATE, 'pr.Lenina, 60','777@ukr.net',1,4700),
('Vitaliy Klichko','1966-05-16', CURRENT_DATE, 'pr.Gagarina, 27','888@ukr.net',2,4300),
('Oleg Tyagnibok','1968-06-03', CURRENT_DATE, 'pr.Moskovskiy, 155','999@ukr.net',3,4100),
('Julia Timoshenko','1962-07-05', CURRENT_DATE, 'Klochkovskaya, 32','1010@ukr.net',4,3900),
('Oleksandra Kuzhel','1960-08-07', CURRENT_DATE, 'pr.Kosiora, 45','1111@ukr.net',5,3700),
('Nataliya Korolevskaya','1969-09-09', CURRENT_DATE, 'Morozova, 28','1212@ukr.net',2,3600),
('Dmitriy Firtash','1964-10-25', CURRENT_DATE, '23 Avgusta, 59','1313@ukr.net',1,3400),
('Aleksandr Yaroslavskiy','1962-11-23', CURRENT_DATE, 'Bluhera, 93','1414@ukr.net',2,3200),
('Igor Baluta','1967-12-02', CURRENT_DATE, 'Geroev Truda, 43','1515@ukr.net',3,3100),
('Semen Semenchenko','1967-01-24', CURRENT_DATE, 'Kosmicheskaya, 10','1616@ukr.net',4,3500),
('Tatiana Chornovol','1970-12-29', CURRENT_DATE, 'Kulturi, 17','1717@ukr.net',2,3750),
('Taisiya Povaliy','1964-03-31', CURRENT_DATE, 'Mironositskaya, 20','1818@ukr.net',5,4700),
('Nestor Shufrich','1963-04-07', CURRENT_DATE, 'Artema, 26','1919@ukr.net',3,4700);

INSERT INTO users (username,password,email,enabled) VALUES
('paul','$2a$10$1fbBTJHh/6U1bGUrOba2uuVtoYARHd4Xe1kayvT6HKqiXMa5AZ.eW', 'paul@mail.ru', TRUE),
('alex','$2a$10$weLDP4DGWmCXW2sozMnp6OIBbXaLAqSN7RNR9thXg46tyjkcvy5.O', 'alex@ukr.net', TRUE);

INSERT INTO user_roles (username, ROLE) VALUES
('paul', 'ROLE_USER'),
('paul', 'ROLE_ADMIN'),
('alex', 'ROLE_USER');
