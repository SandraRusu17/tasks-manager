
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
CREATE TABLE tasks(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL,
    user_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);