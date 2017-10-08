-- START OF UPDATE SCRIPT --
-- Drops barbot_io_device, barbot_io_device_type and barbot_pump tables.

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

    DROP TABLE `barbot_pump`;
    DROP TABLE `barbot_io_device`;
    DROP TABLE `barbot_io_device_type`;

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('drop_io_device_pump', '1.004', '2017-06-06');

-- END OF UPDATE SCRIPT --