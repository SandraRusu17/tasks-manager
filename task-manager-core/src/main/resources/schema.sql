
-- -----------------------------------------------------
-- Table `taskmanager`.`user`
-- -----------------------------------------------------
CREATE TABLE `taskmanager`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `userName` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
 -- -----------------------------------------------------
 -- Table `taskmanager`.`tasks`
 -- -----------------------------------------------------
CREATE TABLE `taskmanager`.`tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NULL,
  `description` VARCHAR(256) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `book_id`
    FOREIGN KEY (`id`)
    REFERENCES `taskmanager`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

     -- -----------------------------------------------------
    ALTER TABLE `taskmanager`.`users`
    CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;

     -- -----------------------------------------------------