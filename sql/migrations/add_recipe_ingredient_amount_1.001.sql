-- START OF UPDATE SCRIPT --
-- Adds recipe ingredient amount column and created at/deleted at/updated at columns to recipe_ingredient table.

DROP PROCEDURE IF EXISTS `do_current_update_script`;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `do_current_update_script`(
  migration VARCHAR(255),
  version VARCHAR(50),
  created_at DATETIME
)
BEGIN
  DECLARE count_rows INT;
  START TRANSACTION;
  SET count_rows = (SELECT count(*) FROM `migration` m WHERE m.version = `version` COLLATE utf8_unicode_ci);
  IF count_rows = 0 THEN

    ALTER TABLE `recipe_ingredient`
      ADD `amount` DECIMAL(4,2) NOT NULL AFTER `ingredient_id`,
      ADD `created_at` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' AFTER `amount`,
      ADD `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() AFTER `created_at`,
      ADD `deleted_at` TIMESTAMP NULL AFTER `updated_at`;

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_recipe_ingredient_amount', '1.001', '2017-05-02');

-- END OF UPDATE SCRIPT --