ALTER TABLE `myhealth`.`weight_measurement`
  ADD INDEX `FK_PERSON_WEIGHT_idx` (`person_id` ASC);
ALTER TABLE `myhealth`.`weight_measurement`
  ADD CONSTRAINT `FK_PERSON_WEIGHT`
FOREIGN KEY (`person_id`)
REFERENCES `myhealth`.`person` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `myhealth`.`height_measurement`
  ADD INDEX `FK_PERSON_HEIGHT_idx` (`person_id` ASC);
ALTER TABLE `myhealth`.`height_measurement`
  ADD CONSTRAINT `FK_PERSON_HEIGHT`
FOREIGN KEY (`person_id`)
REFERENCES `myhealth`.`person` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

