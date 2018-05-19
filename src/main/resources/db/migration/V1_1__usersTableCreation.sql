CREATE TABLE `hmtf_db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(170) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` LONGTEXT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `account_expiration_date` DATETIME NOT NULL DEFAULT '1990-01-01 00:00:01',
  `credentials_expiration_date` DATETIME NOT NULL DEFAULT '1990-01-01 00:00:01',
  `is_account_non_expired` TINYINT NOT NULL,
  `is_account_non_locked` TINYINT NOT NULL,
  `is_credentials_non_expired` TINYINT NOT NULL,
  `is_enabled` TINYINT NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB;
