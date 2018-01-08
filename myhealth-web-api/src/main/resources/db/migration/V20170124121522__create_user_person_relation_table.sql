CREATE TABLE `myhealth`.`person_user_rel` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `person_id` BIGINT(20) NULL,
  `user_id`   BIGINT(20) NULL,
  PRIMARY KEY (`id`)
);
