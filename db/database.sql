-- Adminer 5.4.0 MariaDB 12.0.2-MariaDB-ubu2404 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

drop table IF EXISTS `Admin`;
create TABLE `Admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL,
  `collect_id` int(11) NOT NULL,
  `volunteer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `volunteer_id` (`volunteer_id`),
  KEY `city_id` (`city_id`),
  KEY `collect_id` (`collect_id`),
  CONSTRAINT `Admin_ibfk_4` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteers` (`id`),
  CONSTRAINT `Admin_ibfk_5` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Admin_ibfk_6` FOREIGN KEY (`collect_id`) REFERENCES `Collect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


drop table IF EXISTS `Association`;
create TABLE `Association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

insert into `Association` (`id`, `name`, `description`, `points`, `image`) VALUES
(1,	'Ocean Cleanup',	'Association dédiée au nettoyage des océans et à la protection de la vie marine.',	100,	'🌊'),
(2,	'Forest Guardians',	'Protection des forêts et reforestation à travers le monde.',	150,	'🌳'),
(3,	'Wildlife Protectors',	'Protection des espèces menacées et préservation de leur habitat.',	200,	'🦁'),
(4,	'Clean Air Initiative',	"Lutte contre la pollution de l\'air et promotion des énergies propres.",	120,	'🌬️');

drop table IF EXISTS `City`;
create TABLE `City` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `coordinate_lat` float NOT NULL,
  `coordinates_lng` double DEFAULT NULL,
  `coordinate_lng` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

insert into `City` (`id`, `name`, `coordinate_lat`, `coordinates_lng`, `coordinate_lng`) VALUES
(1,	'Paris',	48.8566,	NULL,	2.3522),
(2,	'Marseille',	43.2965,	NULL,	5.3698),
(3,	'Lyon',	45.7578,	NULL,	4.832),
(4,	'Toulouse',	43.6047,	NULL,	1.4442),
(5,	'Nice',	43.7102,	NULL,	7.262),
(6,	'Nantes',	47.2184,	NULL,	-1.5536),
(7,	'Strasbourg',	48.5734,	NULL,	7.7521),
(8,	'Montpellier',	43.6108,	NULL,	3.8767),
(9,	'Bordeaux',	44.8378,	NULL,	-0.5792),
(10, 'Lille',	50.6292,	NULL,	3.0573);

DROP TABLE IF EXISTS `Collect`;
CREATE TABLE `Collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `City_id` int(11) NOT NULL,
  `volunteer_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `City_id` (`City_id`),
  KEY `volunteer_id` (`volunteer_id`),
  CONSTRAINT `Collect_ibfk_1` FOREIGN KEY (`City_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Collect_ibfk_2` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

insert into `Collect` (`id`, `City_id`, `volunteer_id`, `date`) VALUES
(1,	0,	NULL,	NULL),
(2,	0,	NULL,	NULL),
(3,	0,	NULL,	NULL),
(4,	0,	NULL,	NULL),
(5,	0,	NULL,	NULL),
(6,	0,	NULL,	NULL);

drop table IF EXISTS `Collect_waste`;
create TABLE `Collect_waste` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_id` int(11) NOT NULL,
  `Waste_Type_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `collect_id` (`collect_id`),
  KEY `Waste_Type_id` (`Waste_Type_id`),
  CONSTRAINT `Collect_waste_ibfk_3` FOREIGN KEY (`collect_id`) REFERENCES `Collect` (`id`),
  CONSTRAINT `Collect_waste_ibfk_4` FOREIGN KEY (`Waste_Type_id`) REFERENCES `Waste_Type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


drop table IF EXISTS `Rewards`;
create TABLE `Rewards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `volunteer_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `association_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `volunteer_id` (`volunteer_id`),
  KEY `association_id` (`association_id`),
  CONSTRAINT `Rewards_ibfk_3` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteers` (`id`),
  CONSTRAINT `Rewards_ibfk_4` FOREIGN KEY (`association_id`) REFERENCES `Association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


drop table IF EXISTS `volunteers`;
create TABLE `volunteers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `city_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `volunteers_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

insert into `volunteers` (`id`, `firstname`, `lastname`, `mail`, `password`, `city_id`, `created_at`, `updated_at`) VALUES
(1,	'Monica',	'Geller',	'monica.geller@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(2,	'Rachel',	'Green',	'rachel.green@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(3,	'Phoebe',	'Buffay',	'phoebe.buffay@test.fr',	'test1234',	6,	'2025-05-25 00:00:00',	'2025-05-25'),
(4,	'Joey',	'Tribbiani',	'joey.tribbiani@test.fr',	'test1234',	6,	'2025-05-25 00:00:00',	'2025-05-25'),
(5,	'Chandler',	'Bing',	'chandler.bing@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(6,	'Ross',	'Geller',	'ross.geller@test.fr',	'test1234',	3,	'2025-05-25 00:00:00',	'2025-05-25');

drop table IF EXISTS `Waste_Type`;
create TABLE `Waste_Type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) NOT NULL,
  `label` varchar(50) NOT NULL,
  `className` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

insert into `Waste_Type` (`id`, `value`, `label`, `className`) VALUES
(1,	'cigarette',	'🚬 Mégots de cigarette',	'badge-cigarette'),
(2,	'plastic',	'🥤 Plastique',	'badge-plastic'),
(3,	'glass',	'🍶 Verre',	'badge-glass'),
(4,	'metal',	'🥫 Métal',	'badge-metal'),
(5,	'electronic',	'📱 Électronique',	'badge-electronic'),
(6,	'other',	'❓ Autre',	'badge-other');

-- 2025-10-14 13:24:55 UTC