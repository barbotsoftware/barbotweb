-- START OF UPDATE SCRIPT --
-- [DESCRIPTION]

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

    -- MIGRATION SCRIPT

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('[migration_name]_[date]', '#.###', 'YYYY-MM-DD');

-- END OF UPDATE SCRIPT --