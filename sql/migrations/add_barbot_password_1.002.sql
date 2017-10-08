-- START OF UPDATE SCRIPT --
-- Adds password column to barbot table.

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

    ALTER TABLE barbotdb.barbot ADD password VARCHAR(255) DEFAULT '' NOT NULL;
    ALTER TABLE barbotdb.barbot
      MODIFY COLUMN password VARCHAR(255) NOT NULL DEFAULT '' AFTER name;

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_barbot_password', '1.002', '2017-05-18');

-- END OF UPDATE SCRIPT --