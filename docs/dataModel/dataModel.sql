-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema QueueBrain
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema QueueBrain
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `QueueBrain` DEFAULT CHARACTER SET utf8 ;
USE `QueueBrain` ;

-- -----------------------------------------------------
-- Table `QueueBrain`.`QB_USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QueueBrain`.`QB_USERS` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_NAME` NVARCHAR(45) NULL,
  `USER_CONTACTS` NVARCHAR(800) NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE INDEX `USER_ID_UNIQUE` (`USER_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QueueBrain`.`QB_QUEUES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QueueBrain`.`QB_QUEUES` (
  `QUEUE_ID` INT NOT NULL,
  `QUEUE_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`QUEUE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QueueBrain`.`QB_USERS_TABLES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QueueBrain`.`QB_USERS_TABLES` (
  `USER_ID` INT NOT NULL,
  `QUEUE_ID` INT NOT NULL,
  `REFERENCE_TYPE` INT NULL,
  PRIMARY KEY (`USER_ID`, `QUEUE_ID`),
  INDEX `fk_QB_USERS_has_QB_QUEUES_QB_QUEUES1_idx` (`QUEUE_ID` ASC),
  INDEX `fk_QB_USERS_has_QB_QUEUES_QB_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_QB_USERS_has_QB_QUEUES_QB_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `QueueBrain`.`QB_USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QB_USERS_has_QB_QUEUES_QB_QUEUES1`
    FOREIGN KEY (`QUEUE_ID`)
    REFERENCES `QueueBrain`.`QB_QUEUES` (`QUEUE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
