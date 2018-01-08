CREATE TABLE `myhealth`.`weight_measurement` (
  `id`         BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `person_id`  BIGINT(20)    NULL,
  `date_taken` DATETIME      NULL,
  `value`      DOUBLE        NULL,
  `comments`   VARCHAR(1000) NULL,
  PRIMARY KEY (`id`)
);
