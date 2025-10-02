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
  CONSTRAINT `Admin_ibfk_4` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteers` (`id`),
  CONSTRAINT `Admin_ibfk_5` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Admin_ibfk_6` FOREIGN KEY (`collect_id`) REFERENCES `Collect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `Association`;
CREATE TABLE `Association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `Association` (`id`, `name`, `description`, `points`, `image`) VALUES
(1,	'Ocean Cleanup',	'Association dédiée au nettoyage des océans et à la protection de la vie marine.',	100,	'🌊'),
(2,	'Forest Guardians',	'Protection des forêts et reforestation à travers le monde.',	150,	'🌳'),
(3,	'Wildlife Protectors',	'Protection des espèces menacées et préservation de leur habitat.',	200,	'🦁'),
(4,	'Clean Air Initiative',	'Lutte contre la pollution de l\'air et promotion des énergies propres.',	120,	'🌬️');

DROP TABLE IF EXISTS `City`;
CREATE TABLE `City` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `coordinate_lat` float NOT NULL,
  `coordinate_lng` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `City` (`id`, `name`, `coordinate_lat`, `coordinate_lng`) VALUES
(1,	'Paris',	48.8566,	2.3522),
(2,	'Marseille',	43.2965,	5.3698),
(3,	'Lyon',	45.7578,	4.832),
(4,	'Toulouse',	43.6047,	1.4442),
(5,	'Nice',	43.7102,	7.262),
(6,	'Nantes',	47.2184,	-1.5536),
(7,	'Strasbourg',	48.5734,	7.7521),
(8,	'Montpellier',	43.6108,	3.8767),
(9,	'Bordeaux',	44.8378,	-0.5792),
(10,	'Lille',	50.6292,	3.0573);

DROP TABLE IF EXISTS `Collect`;
CREATE TABLE `Collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `className` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `waste_total` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `Collect` (`id`, `value`, `label`, `className`, `quantity`, `waste_total`, `date`) VALUES
(1,	'cigarette',	'🚬 Mégots de cigarette',	'badge-cigarette',	0,	NULL,	NULL),
(2,	'plastic',	'🥤 Plastique',	'badge-plastic',	0,	NULL,	NULL),
(3,	'glass',	'🍶 Verre',	'badge-glass',	0,	NULL,	NULL),
(4,	'metal',	'🥫 Métal',	'badge-metal',	0,	NULL,	NULL),
(5,	'electronic',	'📱 Électronique',	'badge-electronic',	0,	NULL,	NULL),
(6,	'other',	'❓ Autre',	'badge-other',	0,	NULL,	NULL);

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
  CONSTRAINT `Rewards_ibfk_3` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteers` (`id`),
  CONSTRAINT `Rewards_ibfk_4` FOREIGN KEY (`association_id`) REFERENCES `Association` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


DROP TABLE IF EXISTS `volunteers`;
CREATE TABLE `volunteers` (
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

INSERT INTO `volunteers` (`id`, `firstname`, `lastname`, `mail`, `password`, `city_id`, `created_at`, `updated_at`) VALUES
(1,	'Monica',	'Geller',	'monica.geller@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(2,	'Rachel',	'Green',	'rachel.green@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(3,	'Phoebe',	'Buffay',	'phoebe.buffay@test.fr',	'test1234',	6,	'2025-05-25 00:00:00',	'2025-05-25'),
(4,	'Joey',	'Tribbiani',	'joey.tribbiani@test.fr',	'test1234',	6,	'2025-05-25 00:00:00',	'2025-05-25'),
(5,	'Chandler',	'Bing',	'chandler.bing@test.fr',	'test1234',	1,	'2025-05-25 00:00:00',	'2025-05-25'),
(6,	'Ross',	'Geller',	'ross.geller@test.fr',	'test1234',	3,	'2025-05-25 00:00:00',	'2025-05-25');

-- 2025-10-02 08:29:03 UTC