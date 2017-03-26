-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2017 at 09:21 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `barbot`
--
CREATE DATABASE IF NOT EXISTS `barbot` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `barbot`;

-- --------------------------------------------------------

--
-- Table structure for table `barbots`
--

CREATE TABLE `barbots` (
  `id` int(10) UNSIGNED NOT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barbot_containers`
--

CREATE TABLE `barbot_containers` (
  `id` int(10) UNSIGNED NOT NULL,
  `barbot_id` int(10) UNSIGNED NOT NULL,
  `ingredient_id` int(10) UNSIGNED NOT NULL,
  `number` int(11) NOT NULL,
  `current_volume` int(11) NOT NULL,
  `max_volume` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barbot_io_devices`
--

CREATE TABLE `barbot_io_devices` (
  `id` int(10) UNSIGNED NOT NULL,
  `barbot_id` int(10) UNSIGNED NOT NULL,
  `barbot_io_device_type_id` int(10) UNSIGNED NOT NULL,
  `gpio_port` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barbot_io_device_types`
--

CREATE TABLE `barbot_io_device_types` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barbot_pumps`
--

CREATE TABLE `barbot_pumps` (
  `id` int(10) UNSIGNED NOT NULL,
  `barbot_io_device_id` int(10) UNSIGNED NOT NULL,
  `barbot_container_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drink_orders`
--

CREATE TABLE `drink_orders` (
  `id` int(10) UNSIGNED NOT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `barbot_id` int(10) UNSIGNED NOT NULL,
  `ice` int(11) NOT NULL DEFAULT '0',
  `garnish` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(10) UNSIGNED NOT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `abv` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `run_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE `recipes` (
  `id` int(10) UNSIGNED NOT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT '0',
  `custom` int(11) NOT NULL DEFAULT '0',
  `image_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `recipe_ingredient`
--

CREATE TABLE `recipe_ingredient` (
  `id` int(10) UNSIGNED NOT NULL,
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `ingredient_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barbots`
--
ALTER TABLE `barbots`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barbot_containers`
--
ALTER TABLE `barbot_containers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_containers_barbots_id_fk` (`barbot_id`),
  ADD KEY `barbot_containers_ingredients_id_fk` (`ingredient_id`);

--
-- Indexes for table `barbot_io_devices`
--
ALTER TABLE `barbot_io_devices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_io_devices_barbots_id_fk` (`barbot_id`),
  ADD KEY `barbot_io_devices_barbot_io_device_types_id_fk` (`barbot_io_device_type_id`);

--
-- Indexes for table `barbot_io_device_types`
--
ALTER TABLE `barbot_io_device_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barbot_pumps`
--
ALTER TABLE `barbot_pumps`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_pumps_barbot_containers_id_fk` (`barbot_container_id`),
  ADD KEY `barbot_pumps_barbot_io_devices_id_fk` (`barbot_io_device_id`);

--
-- Indexes for table `drink_orders`
--
ALTER TABLE `drink_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `drink_orders_users_id_fk` (`user_id`),
  ADD KEY `drink_orders_recipes_id_fk` (`recipe_id`),
  ADD KEY `drink_orders_barbots_id_fk` (`barbot_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recipe_ingredient_recipes_id_fk` (`recipe_id`),
  ADD KEY `recipe_ingredient_ingredients_id_fk` (`ingredient_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barbots`
--
ALTER TABLE `barbots`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_containers`
--
ALTER TABLE `barbot_containers`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_io_devices`
--
ALTER TABLE `barbot_io_devices`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_io_device_types`
--
ALTER TABLE `barbot_io_device_types`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_pumps`
--
ALTER TABLE `barbot_pumps`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `drink_orders`
--
ALTER TABLE `drink_orders`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `barbot_containers`
--
ALTER TABLE `barbot_containers`
  ADD CONSTRAINT `barbot_containers_barbots_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbots` (`id`),
  ADD CONSTRAINT `barbot_containers_ingredients_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`);

--
-- Constraints for table `barbot_io_devices`
--
ALTER TABLE `barbot_io_devices`
  ADD CONSTRAINT `barbot_io_devices_barbot_io_device_types_id_fk` FOREIGN KEY (`barbot_io_device_type_id`) REFERENCES `barbot_io_device_types` (`id`),
  ADD CONSTRAINT `barbot_io_devices_barbots_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbots` (`id`);

--
-- Constraints for table `barbot_pumps`
--
ALTER TABLE `barbot_pumps`
  ADD CONSTRAINT `barbot_pumps_barbot_containers_id_fk` FOREIGN KEY (`barbot_container_id`) REFERENCES `barbot_containers` (`id`),
  ADD CONSTRAINT `barbot_pumps_barbot_io_devices_id_fk` FOREIGN KEY (`barbot_io_device_id`) REFERENCES `barbot_io_devices` (`id`);

--
-- Constraints for table `drink_orders`
--
ALTER TABLE `drink_orders`
  ADD CONSTRAINT `drink_orders_barbots_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbots` (`id`),
  ADD CONSTRAINT `drink_orders_recipes_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
  ADD CONSTRAINT `drink_orders_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD CONSTRAINT `recipe_ingredient_ingredients_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `recipe_ingredient_recipes_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/* Update Migration Table */
INSERT INTO migrations(migration, version, created_at, run_at) VALUES ('create_db', '1.000', '2017-03-25', NOW());
/* End Update Migration Table */
