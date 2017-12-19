-- START OF UPDATE SCRIPT --
-- Adds image_url column to the category table

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
    ALTER TABLE `category`
		ADD `image_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL;
    
    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_category_image_url', '1.007', '2017-12-18');

-- END OF UPDATE SCRIPT --