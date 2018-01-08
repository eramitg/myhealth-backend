CREATE TABLE `myhealth`.`bp_measurement` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `person_id` BIGINT(20) NOT NULL,
  `systolic` INT NOT NULL,
  `diastolic` INT NOT NULL,
  `date_taken` DATETIME NOT NULL,
  `comments` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
