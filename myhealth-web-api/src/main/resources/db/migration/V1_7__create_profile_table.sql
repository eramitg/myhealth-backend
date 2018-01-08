CREATE TABLE `myhealth`.`person` (
  `ID`            BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `NAME`          VARCHAR(255)            NULL,
  `SURNAME`       VARCHAR(255)            NULL,
  `GENDER`        ENUM ('FEMALE', 'MALE') NULL,
  `DATE_OF_BIRTH` DATE                    NULL,
  PRIMARY KEY (`ID`)
);
