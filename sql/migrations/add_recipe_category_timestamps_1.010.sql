-- START OF UPDATE SCRIPT --
-- Add timestamp fields to recipe_category table

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
  SET count_rows = (SELECT count(*) FROM `migration` m WHERE m.version = `version`);
  IF count_rows = 0 THEN

    -- MIGRATION SCRIPT
	ALTER TABLE barbotdb.recipe_category ADD created_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.recipe_category ADD deleted_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.recipe_category ADD updated_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_recipe_category_timestamps', '1.010', '2018-02-02');

-- END OF UPDATE SCRIPT --