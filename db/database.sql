-- Adminer 5.4.0 MariaDB 12.0.2-MariaDB-ubu2404 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL,
  `collect_id` int(11) NOT NULL,
  `volunteer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `volunteer_id` (`volunteer_id`),
  KEY `city_id` (`city_id`),
  KEY `collect_id` (`collect_id`),
  CONSTRAINT `Admin_ibfk_4` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteer` (`id`),
  CONSTRAINT `Admin_ibfk_5` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Admin_ibfk_6` FOREIGN KEY (`collect_id`) REFERENCES `Collect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `Association`;
CREATE TABLE `Association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `price_points` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `City`;
CREATE TABLE `City` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `Collect`;
CREATE TABLE `Collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `waste_total` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `Collect_volunteer`;
CREATE TABLE `Collect_volunteer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `collect_id` (`collect_id`),
  CONSTRAINT `Collect_volunteer_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Collect_volunteer_ibfk_3` FOREIGN KEY (`collect_id`) REFERENCES `Collect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `Rewards`;
CREATE TABLE `Rewards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `volunteer_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `association_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `volunteer_id` (`volunteer_id`),
  KEY `association_id` (`association_id`),
  CONSTRAINT `Rewards_ibfk_3` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteer` (`id`),
  CONSTRAINT `Rewards_ibfk_4` FOREIGN KEY (`association_id`) REFERENCES `Association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `city_id` int(11) NOT NULL,
  `collect_volunteer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `collect_volunteer_id` (`collect_volunteer_id`),
  CONSTRAINT `volunteer_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `volunteer_ibfk_2` FOREIGN KEY (`collect_volunteer_id`) REFERENCES `Collect_volunteer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- 2025-10-01 13:05:05 UTC