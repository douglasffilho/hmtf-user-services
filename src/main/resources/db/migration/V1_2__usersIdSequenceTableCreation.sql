CREATE TABLE `hmtf_db`.`user_id_sequence_generation` (
  `key` VARCHAR(255) NOT NULL,
  `next` BIGINT NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;
