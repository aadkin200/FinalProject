-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hikedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hikedb` ;

-- -----------------------------------------------------
-- Schema hikedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hikedb` DEFAULT CHARACTER SET utf8 ;
USE `hikedb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NULL,
  `favorite_trail_food` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `image_url` VARCHAR(250) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `difficulty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `difficulty` ;

CREATE TABLE IF NOT EXISTS `difficulty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `route_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `route_type` ;

CREATE TABLE IF NOT EXISTS `route_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail` ;

CREATE TABLE IF NOT EXISTS `trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `difficulty_id` INT NOT NULL,
  `route_type_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `elevation_change_feet` INT NULL,
  `trailhead_latitude` VARCHAR(150) NULL,
  `trailhead_longitude` VARCHAR(150) NULL,
  `city` VARCHAR(100) NULL,
  `state` CHAR(2) NULL,
  `hazards` VARCHAR(2000) NULL,
  `wildlife` VARCHAR(2000) NULL,
  `details` TEXT NULL,
  `distance_miles` DECIMAL NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `enabled` TINYINT NULL,
  `approved` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_user_idx` (`user_id` ASC),
  INDEX `fk_trail_difficulty1_idx` (`difficulty_id` ASC),
  INDEX `fk_trail_route_type1_idx` (`route_type_id` ASC),
  CONSTRAINT `fk_trail_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_difficulty1`
    FOREIGN KEY (`difficulty_id`)
    REFERENCES `difficulty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_route_type1`
    FOREIGN KEY (`route_type_id`)
    REFERENCES `route_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trail_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `message` TEXT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `in_reply_to` INT NULL,
  `enabled` TINYINT NULL,
  `subject` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_trail1_idx` (`trail_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to` ASC),
  CONSTRAINT `fk_comment_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail_image` ;

CREATE TABLE IF NOT EXISTS `trail_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trail_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `image_url` VARCHAR(500) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_img_trail1_idx` (`trail_id` ASC),
  INDEX `fk_trail_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_trail_img_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_trail` ;

CREATE TABLE IF NOT EXISTS `user_has_trail` (
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trail_id`),
  INDEX `fk_user_has_trail_trail1_idx` (`trail_id` ASC),
  INDEX `fk_user_has_trail_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_trail_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_trail_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amenity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amenity` ;

CREATE TABLE IF NOT EXISTS `amenity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `image_url` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail_has_amenity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail_has_amenity` ;

CREATE TABLE IF NOT EXISTS `trail_has_amenity` (
  `trail_id` INT NOT NULL,
  `amenity_id` INT NOT NULL,
  PRIMARY KEY (`trail_id`, `amenity_id`),
  INDEX `fk_trail_has_amenity_amenity1_idx` (`amenity_id` ASC),
  INDEX `fk_trail_has_amenity_trail1_idx` (`trail_id` ASC),
  CONSTRAINT `fk_trail_has_amenity_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_has_amenity_amenity1`
    FOREIGN KEY (`amenity_id`)
    REFERENCES `amenity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail_resource` ;

CREATE TABLE IF NOT EXISTS `trail_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trail_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `resource_url` VARCHAR(500) NULL,
  `title` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_resource_trail1_idx` (`trail_id` ASC),
  INDEX `fk_trail_resource_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_trail_resource_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_resource_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS hikeuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'hikeuser'@'localhost' IDENTIFIED BY 'hikeuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'hikeuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `favorite_trail_food`, `enabled`, `role`, `first_name`, `last_name`, `image_url`, `created_at`) VALUES (1, 'admin', '$2a$10$NHCKJEdtUxcAdXBiC6yZnel2uwIowYIz74HV7JlFh7K0yeChwTx7O', 'admin@admin.com', 'Trailnutz', 1, 'ADMIN', 'Gerry', 'Lowkey', 'https://4.bp.blogspot.com/-klQjV5L27_s/T73fozhXluI/AAAAAAAAAkI/_75K0D7Hpqw/s1600/smiley-face2.jpg', '2020-01-01 10:10');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `favorite_trail_food`, `enabled`, `role`, `first_name`, `last_name`, `image_url`, `created_at`) VALUES (2, 'bobby', '$2a$10$T18Sq1Owcw9pPn.HIzY1huZOEoAa2Vx.ewie8BItqwEQ6kb1ilRme', 'bobby@dobby.com', 'Leafy greens', 1, 'standard', 'Bobby', 'Dobby', 'https://4.bp.blogspot.com/-klQjV5L27_s/T73fozhXluI/AAAAAAAAAkI/_75K0D7Hpqw/s1600/smiley-face2.jpg', '2020-01-01 10:10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (1, 'Easy', 'A hike that is generally suitable for anyone who enjoys walking. Mostly level or with a slight incline. Generally less than 3 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (2, 'Moderate', 'A moderate hike is generally suitable for novice hikers who want a bit of a challenge. The terrain will involve a moderate incline and may have some steeper sections. Generally 3 to 5 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (3, 'Moderately Strenuous', 'Moderately Strenuous hikes will generally be challenging for an unconditioned person. The terrain will involve a steady and often steep incline. Generally 5 to 8 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (4, 'Strenuous', 'Strenuous hikes will challenge most hikers. The hike will generally be longer and steeper, but may be deemed \"Strenuous\" because of the elevation gain. Generally 7 to 10 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (5, 'Very Strenuous', 'Only well-conditioned and well-prepared hikers should attempt very strenuous hikes. The hike will generally be long and steep, and may include rock scrambling, stream crossings, and other challenging terrain. Generally 8 miles and over.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `route_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `route_type` (`id`, `name`, `description`) VALUES (1, 'Loop', 'The loop is popular for day-use trails because it enables easy access and parking. Hikers do not have to return on the same trail, thus maximizing interest and satisfaction.');
INSERT INTO `route_type` (`id`, `name`, `description`) VALUES (2, 'Horse shoe', '\nThe horseshoe, which sports two separate trailheads, can be especially valuable in areas where public transportation is available. It can also be used as an alternative to auto travel on roads where distances between terminuses are not too great. Ski touring trail development in the Mount Washington Valley of New Hampshire has trailheads at inns and restaurants in the valley that are connected by trails in the horseshoe format.');
INSERT INTO `route_type` (`id`, `name`, `description`) VALUES (3, 'Out and Back', 'The line format, also known as “out and back,” is the simplest and most common format for trails. It connects two points—the roadside trailhead and the destination. Good examples are fire wardens’ trails to lookout towers on mountain summits. Long-distance trails, such as the Appalachian Trail and Pacific Crest Trail, are prime examples of trails in the line format. Line-style trails with high scenic value are augmented with side paths, alternate routes, and connectors to form trail systems.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (1, 1, 1, 1, 'Great Smokey', 700, '35.176720', '-81.452720', 'Grover', 'NC', 'sheer cliffs', 'Racoons', 'not even worth going', 560, '2020-01-01 10:10', '2020-01-01 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (2, 2, 2, 1, 'Polly Wag Trail', 125, NULL, NULL, 'Somecity', 'SC', 'none', 'none', 'mythical location', 2, '2021-01-01 10:10', '2021-01-01 10:10', 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (1, 1, 1, 'Hello', '2020-01-01 10:10', '2020-01-01 10:10', NULL, 1, 'Yeehaw seesaw');
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (2, 1, 1, 'welcome', '2020-01-01 10:10', '2020-01-01 10:10', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (1, 1, 1, 'http://www.google.com', '2020-01-01 10:10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `amenity`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `amenity` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Bathroom', 'Bathrooms?', 'https://i.etsystatic.com/13221305/r/il/4f5666/1446826199/il_794xN.1446826199_hozl.jpg');
INSERT INTO `amenity` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Road Exit', 'Road exit on path', 'https://cdn.printablesigns.net/samples/Traffic_Exit.png');
INSERT INTO `amenity` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Water Source', 'Get some water', 'https://www.kindpng.com/picc/m/50-502486_natural-environment-clipart-water-source-tear-drop-clipart.png');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_has_amenity`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_resource` (`id`, `trail_id`, `user_id`, `resource_url`, `title`, `created_at`, `enabled`) VALUES (1, 1, 1, 'https://files.nc.gov/ncparks/MOMI-trail.png', 'Trail photos', '2020-01-01 10:10', 1);

COMMIT;

