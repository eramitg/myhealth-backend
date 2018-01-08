ALTER TABLE `myhealth`.`bp_measurement` 
ADD INDEX `FK_BP_PERSON_idx` (`person_id` ASC);
ALTER TABLE `myhealth`.`bp_measurement` 
ADD CONSTRAINT `FK_BP_PERSON`
  FOREIGN KEY (`person_id`)
  REFERENCES `myhealth`.`person` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
