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
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (1, 1, 3, 1, 'Grand Father Mountain', 4000, '36.0850028', '-81.8462513', 'Grandfather', 'NC', 'sheer cliffs', 'Racoons, Bears', 'It\'s a beautiful northern hardwood forest you\'re hiking through with a few Red Spruce and Eastern Hemlock mixed in. It\'s a beautiful start to the hike, but you\'ll enjoy the sights of nature more than any sounds since you\'re right beside the busy highway and it\'s rather noisy with trucks and other vehicles coming up the steep grade at high speed. (In fact, the only real complaint I\'d have about this hike, besides the crowds, is that the faint sound of traffic - especially motorcycles - stays with you all the way to the summit).', 2, '2020-01-01 10:21', '2020-01-01 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (2, 2, 2, 1, 'Art Loed Trail', 3000, '35.2811954', '-82.72263240000001', 'Brevard', 'NC', 'none', 'none', 'The Art Loeb Trail, named for a hiking and conservation pioneer, links trails in Pisgah National Forest. Shelters and other refinements have been provided along the marked trails, making it a good choice if you are up for an extended 30-mile hike. Complete its entire length and you’ll see Neil Gap, Pilot Mountain and Deep Gap along the way.', 33, '2021-01-04 21:21', '2021-01-05 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (3, 1, 3, 1, 'Chimney Rock', 800, '35.432857', '-82.23960499999998', 'Lake Lure', 'NC', 'Sheer Cliffs', 'nothing extraordinary', 'Originally purchased and protected by the Morse family as a public attraction, this area became Chimney Rock State Park in 2007. Explore both as you take one of the park’s six hiking trails. One leads to Hickory Nut Falls, which plunges 404 feet. It and other locales here were used as background in the 1992 film The Last of the Mohicans starring Daniel Day-Lewis. Hike up the Outcroppings Trail, nearly 500 steps, to the top of the rock that gives the park its name. Once up there, 360-degree views are yours for the taking.', 1, '2021-04-10 04:31', '2021-01-05 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (4, 2, 2, 2, 'Roan Mountain', 2500, '36.016157', '-82.12567899999999', 'Bakersville', 'NC', 'unknown', 'unknown', 'Naturalists have journeyed to Roan Mountain for more than two centuries to study its diverse plant life. The Roan Mountain Gardens Trail is an easy 1-mile hike through the popular rhododendron gardens, which explode in color between mid-June and early July each year, drawing visitors from around the world. The trail is divided into three loops, each suitable for hikers of all ages. The first loop is a paved, handicap-accessible interpretive trail with 16 stations keyed to information in a free brochure explaining the exotic and rare plants found on the mountain', 1, '2021-05-12 02:23', '2021-01-05 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (5, 1, 3, 1, 'Graveyard Fields', 450, '35.32091', '-82.85291169999999', 'Brevard', 'NC', 'clearly the wildlife', 'Lions, tigers, and bears o my', 'One major reason for its popularity is the three waterfalls that plummet through the area: Upper Falls, Second Falls and Yellowstone Falls. Another reason is the rich variety of plants that have re-carpeted and repopulated the landscape. Yellow birches, mountain ashes, Fraser firs, flowering dogwoods and red spruces have replaced the once-abundant American chestnuts. Wildflowers grace the valley floor.', 3, '2020-05-12 04:12', '2021-01-05 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (6, 2, 4, 3, 'Grays Peak', 3005, '39.662458', '-105.78398', 'Silver Plume', 'CO', 'Bears', NULL, 'Grays Peak is a 7.3 mile heavily trafficked out and back trail located near Silver Plume, Colorado that features a river and is rated as difficult. The trail is primarily used for hiking and is best used from May until October. Dogs are also able to use this trail but must be kept on leash.', 7.3, '2020-05-12 04:12', '2021-01-05 10:10', 1, 1);
INSERT INTO `trail` (`id`, `user_id`, `difficulty_id`, `route_type_id`, `name`, `elevation_change_feet`, `trailhead_latitude`, `trailhead_longitude`, `city`, `state`, `hazards`, `wildlife`, `details`, `distance_miles`, `created_at`, `updated_at`, `enabled`, `approved`) VALUES (7, 2, 4, 3, 'North Mount Elbert Trail', 4468, '39.151596', '-106.412341', 'Leadville', 'CO', 'Bears, Cliffs', 'best you dont know', 'North Mount Elbert Trail is a 10.4 mile heavily trafficked out and back trail located near Twin Lakes, Colorado that features beautiful wild flowers and is only recommended for very experienced adventurers. The trail is primarily used for hiking and is best used from May until October.', 10.4, '2020-05-12 04:12', '2021-01-05 10:10', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (1, 1, 1, 'Beautiful and not a bad hike either', '2021-01-02 14:33', '2020-01-01 10:10', NULL, 1, 'Grandfather wow!');
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (2, 1, 2, 'I have to agree!', '2021-01-04 10:10', '2020-01-01 10:10', 1, 1, NULL);
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (3, 1, 1, 'Beatuiful sites, beatiful landscape, just wow', '2020-01-01 10:10', '2020-01-01 10:10', NULL, 1, 'What nice views');
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (4, 1, 1, 'seriously though', '2020-01-01 10:10', '2020-01-01 10:10', 1, 1, NULL);
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (5, 1, 2, 'I cant agree more!', '2020-01-02 10:10', '2020-01-02 10:10', 3, 1, NULL);
INSERT INTO `comment` (`id`, `trail_id`, `user_id`, `message`, `created_at`, `updated_at`, `in_reply_to`, `enabled`, `subject`) VALUES (6, 1, 2, 'In love.', '2020-01-02 10:10', '2020-01-02 10:10', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (1, 1, 1, 'https://i.wnc.io/s1024/2011-05-15_grandfather-mountain-state-park_calloway-peak-view-attic-window-peak-hazy.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (2, 2, 1, 'https://i.wnc.io/s1024/2018-07-08_pisgah-black-balsam_art-loeb-trail-wooden-sign.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (3, 3, 2, 'https://www.romanticasheville.com/sites/default/files/u13/chimney-rock-outcropping-trail.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (4, 3, 2, 'https://i.wnc.io/s1024/2012-09-09_chimney-rock-state-park_outcroppings-trail-structure-rock-view.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (5, 1, 2, 'https://www.romanticasheville.com/sites/default/files/images/basic_page/GrandfatherMtnFall.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (6, 4, 2, 'https://ashevilletrails.com/wp-content/uploads/2018/04/roan-mountain-appalachian-trail-spring.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (7, 5, 2, 'http://ashevilletrails.com/wp-content/uploads/2018/02/graveyard-fields-waterfalls-trail-03.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (8, 6, 2, 'https://upload.wikimedia.org/wikipedia/commons/7/73/Grays_and_Torreys_Peaks_2006-08-06.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (9, 6, 2, 'https://www.outdoorproject.com/sites/default/files/styles/hero_image_desktop_2x/public/features/dsc01017-2.jpg?itok=aXPS89oL', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (10, 7, 1, 'https://cdn-assets.alltrails.com/uploads/photo/image/19637199/extra_large_832261429ba7979a47a44567a2e5a5c3.jpg', '2020-01-01 10:10');
INSERT INTO `trail_image` (`id`, `trail_id`, `user_id`, `image_url`, `created_at`) VALUES (11, 7, 1, 'https://cdn-assets.alltrails.com/uploads/photo/image/39872034/extra_large_dd23b41b1563cfc291df4a29b5f75ee3.jpg', '2020-01-01 10:10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (1, 1);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (2, 1);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (2, 2);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (1, 2);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (1, 3);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (1, 4);
INSERT INTO `user_has_trail` (`user_id`, `trail_id`) VALUES (2, 3);

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
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (1, 2);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (1, 3);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (2, 1);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (2, 2);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (3, 3);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (4, 1);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (4, 3);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (5, 1);
INSERT INTO `trail_has_amenity` (`trail_id`, `amenity_id`) VALUES (5, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikedb`;
INSERT INTO `trail_resource` (`id`, `trail_id`, `user_id`, `resource_url`, `title`, `created_at`, `enabled`) VALUES (1, 1, 1, 'https://files.nc.gov/ncparks/MOMI-trail.png', 'Trail photos', '2020-01-01 10:10', 1);

COMMIT;

