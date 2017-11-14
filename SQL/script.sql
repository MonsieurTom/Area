DROP DATABASE IF EXISTS areaData;
create database areaData;

USE areaData;
CREATE TABLE `User` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `banned` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

INSERT INTO User (mail, password) VALUES ("admin@admin.admin", "admin");

CREATE TABLE `Area`
(
  `area_id` int (11) NOT NULL AUTO_INCREMENT,
  `user_id` int (11) NOT NULL,
  `tmpData` TEXT NOT NULL,
  `actionName` varchar (45) NOT NULL,
  `actionArgs` TEXT NOT NULL,
  `reactionName` varchar (45) NOT NULL,
  `reactionArgs` TEXT NOT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `Account`
(
  `id` int (11) NOT NULL AUTO_INCREMENT,
  `user_id` int (11) NOT NULL,
  `token` TEXT NOT NULL,
  `moduleName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT  CHARSET=utf8;