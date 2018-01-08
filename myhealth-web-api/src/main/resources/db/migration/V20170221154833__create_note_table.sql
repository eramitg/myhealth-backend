CREATE TABLE `myhealth`.`note` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `person_id` BIGINT(20) NULL,
  `date_taken` DATETIME NULL,
  `text` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_note_person_idx` (`person_id` ASC),
  CONSTRAINT `fk_note_person`
    FOREIGN KEY (`person_id`)
    REFERENCES `myhealth`.`person` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
