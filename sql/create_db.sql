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
-- Database: `barbotdb`
--
CREATE DATABASE IF NOT EXISTS `barbotdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `barbotdb`;

-- --------------------------------------------------------

--
-- Table structure for table `barbot`
--

CREATE TABLE `barbot` (
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
-- Table structure for table `barbot_container`
--

CREATE TABLE `barbot_container` (
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
-- Table structure for table `barbot_io_device`
--

CREATE TABLE `barbot_io_device` (
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
-- Table structure for table `barbot_io_device_type`
--

CREATE TABLE `barbot_io_device_type` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barbot_pump`
--

CREATE TABLE `barbot_pump` (
  `id` int(10) UNSIGNED NOT NULL,
  `barbot_io_device_id` int(10) UNSIGNED NOT NULL,
  `barbot_container_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drink_order`
--

CREATE TABLE `drink_order` (
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
-- Table structure for table `ingredient`
--

CREATE TABLE `ingredient` (
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
-- Table structure for table `migration`
--

CREATE TABLE `migration` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `version` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `run_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
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
-- Table structure for table `user`
--

CREATE TABLE `user` (
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
-- Indexes for table `barbot`
--
ALTER TABLE `barbot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barbot_container`
--
ALTER TABLE `barbot_container`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_container_barbot_id_fk` (`barbot_id`),
  ADD KEY `barbot_container_ingredient_id_fk` (`ingredient_id`);

--
-- Indexes for table `barbot_io_device`
--
ALTER TABLE `barbot_io_device`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_io_device_barbots_id_fk` (`barbot_id`),
  ADD KEY `barbot_io_device_barbot_io_device_type_id_fk` (`barbot_io_device_type_id`);

--
-- Indexes for table `barbot_io_device_type`
--
ALTER TABLE `barbot_io_device_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barbot_pump`
--
ALTER TABLE `barbot_pump`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barbot_pump_barbot_container_id_fk` (`barbot_container_id`),
  ADD KEY `barbot_pump_barbot_io_device_id_fk` (`barbot_io_device_id`);

--
-- Indexes for table `drink_order`
--
ALTER TABLE `drink_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `drink_order_user_id_fk` (`user_id`),
  ADD KEY `drink_order_recipe_id_fk` (`recipe_id`),
  ADD KEY `drink_order_barbot_id_fk` (`barbot_id`);

--
-- Indexes for table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recipe_ingredient_recipe_id_fk` (`recipe_id`),
  ADD KEY `recipe_ingredient_ingredient_id_fk` (`ingredient_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `migration`
--
ALTER TABLE `migration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barbot`
--
ALTER TABLE `barbot`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_container`
--
ALTER TABLE `barbot_container`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_io_device`
--
ALTER TABLE `barbot_io_device`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_io_device_type`
--
ALTER TABLE `barbot_io_device_type`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `barbot_pump`
--
ALTER TABLE `barbot_pump`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `drink_order`
--
ALTER TABLE `drink_order`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `migration`
--
ALTER TABLE `migration`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `barbot_container`
--
ALTER TABLE `barbot_container`
  ADD CONSTRAINT `barbot_container_barbot_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbot` (`id`),
  ADD CONSTRAINT `barbot_container_ingredient_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`);

--
-- Constraints for table `barbot_io_device`
--
ALTER TABLE `barbot_io_device`
  ADD CONSTRAINT `barbot_io_device_barbot_io_device_type_id_fk` FOREIGN KEY (`barbot_io_device_type_id`) REFERENCES `barbot_io_device_type` (`id`),
  ADD CONSTRAINT `barbot_io_device_barbot_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbot` (`id`);

--
-- Constraints for table `barbot_pump`
--
ALTER TABLE `barbot_pump`
  ADD CONSTRAINT `barbot_pump_barbot_container_id_fk` FOREIGN KEY (`barbot_container_id`) REFERENCES `barbot_container` (`id`),
  ADD CONSTRAINT `barbot_pump_barbot_io_device_id_fk` FOREIGN KEY (`barbot_io_device_id`) REFERENCES `barbot_io_device` (`id`);

--
-- Constraints for table `drink_order`
--
ALTER TABLE `drink_order`
  ADD CONSTRAINT `drink_order_barbot_id_fk` FOREIGN KEY (`barbot_id`) REFERENCES `barbot` (`id`),
  ADD CONSTRAINT `drink_order_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
  ADD CONSTRAINT `drink_order_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD CONSTRAINT `recipe_ingredient_ingredient_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `recipe_ingredient_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/* Update Migration Table */
INSERT INTO migration(migration, version, created_at, run_at) VALUES ('create_db', '1.000', '2017-03-25', NOW());
/* End Update Migration Table */
