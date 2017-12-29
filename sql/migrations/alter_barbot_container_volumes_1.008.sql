-- START OF UPDATE SCRIPT --
-- Alter barbot_container table current_volume and max_volume fields to decimal(4, 2)

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
    ALTER TABLE barbotdb.barbot_container
    MODIFY COLUMN current_volume decimal(5,2),
    MODIFY COLUMN max_volume decimal(5,2);

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('alter_barbot_container_volumes', '1.008', '2017-12-28');

-- END OF UPDATE SCRIPT --