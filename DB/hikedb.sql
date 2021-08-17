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
  `password` VARCHAR(200) NULL,
  `email` VARCHAR(200) NULL,
  `favorite_hike` VARCHAR(200) NULL,
  `favorite_trail_food` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
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
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail` ;

CREATE TABLE IF NOT EXISTS `trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bathroom` TINYINT NULL,
  `water_source` TINYINT NULL,
  `road_exit` TINYINT NULL,
  `elevation_change` VARCHAR(45) NULL,
  `start_point` VARCHAR(150) NULL,
  `end_point` VARCHAR(150) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `hazards` VARCHAR(45) NULL,
  `wildlife` VARCHAR(45) NULL,
  `route_type` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `difficulty_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_user_idx` (`user_id` ASC),
  INDEX `fk_trail_difficulty1_idx` (`difficulty_id` ASC),
  CONSTRAINT `fk_trail_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trail_difficulty1`
    FOREIGN KEY (`difficulty_id`)
    REFERENCES `difficulty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `trail_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_trail1_idx` (`trail_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reply` ;

CREATE TABLE IF NOT EXISTS `reply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(45) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `comment_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_reply_comment1_idx` (`comment_id` ASC),
  INDEX `fk_reply_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_reply_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail_img`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail_img` ;

CREATE TABLE IF NOT EXISTS `trail_img` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img_url` VARCHAR(500) NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_img_trail1_idx` (`trail_id` ASC),
  CONSTRAINT `fk_trail_img_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `email`, `favorite_hike`, `favorite_trail_food`, `enabled`, `role`, `first_name`, `last_name`) VALUES (1, 'admin', '', 'admin@admin.com', 'unknown', NULL, 1, 'ADMIN', 'Gerry', 'Lowkey');

COMMIT;


-- -----------------------------------------------------
-- Data for table `difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `difficulty` (`id`, `name`) VALUES (1, 'Easy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail` (`id`, `bathroom`, `water_source`, `road_exit`, `elevation_change`, `start_point`, `end_point`, `city`, `state`, `hazards`, `wildlife`, `route_type`, `name`, `user_id`, `difficulty_id`) VALUES (1, 1, 1, 1, '700', NULL, NULL, 'unknown', 'unknown', 'sheer cliffs', NULL, NULL, 'Great Smokey', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `comment` (`id`, `message`, `created_at`, `updated_at`, `trail_id`, `user_id`) VALUES (1, 'hello', NULL, NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reply`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `reply` (`id`, `message`, `created_at`, `updated_at`, `comment_id`, `user_id`) VALUES (1, 'another message', NULL, NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_img`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_img` (`id`, `img_url`, `trail_id`) VALUES (1, 'http://www.google.com', 1);

COMMIT;

