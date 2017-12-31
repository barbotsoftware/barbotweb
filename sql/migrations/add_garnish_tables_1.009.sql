-- START OF UPDATE SCRIPT --
-- Adds Garnish tables to barbotdb schema

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
    CREATE TABLE barbotdb.garnish
    (
        id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
        uid VARCHAR(255) NOT NULL,
        name VARCHAR(255)
    );

    CREATE UNIQUE INDEX garnish_id_uindex ON barbotdb.garnish (id);
    CREATE UNIQUE INDEX garnish_uid_uindex ON barbotdb.garnish (uid);

    ALTER TABLE barbotdb.garnish ADD created_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.garnish ADD deleted_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.garnish ADD updated_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    
	CREATE TABLE barbotdb.barbot_garnish
    (
        id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
        barbot_id INT(10) UNSIGNED NOT NULL,
        garnish_id INT(10) UNSIGNED NOT NULL,
		option_number INT(10) NOT NULL,
        quantity INT(10) NOT NULL
    );
    
    CREATE UNIQUE INDEX barbot_garnish_id_uindex ON barbotdb.barbot_garnish (id);

    ALTER TABLE barbotdb.barbot_garnish
      ADD CONSTRAINT barbot_garnish_barbot_id_fk
      FOREIGN KEY (barbot_id) REFERENCES barbot (id);
    ALTER TABLE barbotdb.barbot_garnish
      ADD CONSTRAINT barbot_garnish_garnish_id_fk
      FOREIGN KEY (garnish_id) REFERENCES garnish (id);
      
	ALTER TABLE barbotdb.barbot_garnish ADD created_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.barbot_garnish ADD deleted_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.barbot_garnish ADD updated_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    
    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_garnish_tables', '1.009', '2017-12-30');

-- END OF UPDATE SCRIPT --
