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
  SET count_rows = (SELECT count(*) FROM `migration` m WHERE m.version = `version`);
  IF count_rows = 0 THEN

    CREATE TABLE barbotdb.category
    (
        id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
        uid VARCHAR(255) NOT NULL,
        parent_category_id INT(10) UNSIGNED,
        name VARCHAR(255)
    );
    CREATE UNIQUE INDEX category_id_uindex ON barbotdb.category (id);
    CREATE UNIQUE INDEX category_uid_uindex ON barbotdb.category (uid);

    ALTER TABLE barbotdb.category
      ADD CONSTRAINT category_category_id_fk
      FOREIGN KEY (parent_category_id) REFERENCES category (id);

    ALTER TABLE barbotdb.category ADD created_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.category ADD deleted_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;
    ALTER TABLE barbotdb.category ADD updated_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL;

    CREATE TABLE barbotdb.recipe_category
    (
        id INT(10) UNSIGNED PRIMARY KEY NOT NULL,
        recipe_id INT(10) UNSIGNED,
        category_id INT(10) UNSIGNED
    );
    CREATE UNIQUE INDEX recipe_category_id_uindex ON barbotdb.recipe_category (id);

    ALTER TABLE barbotdb.recipe_category
      ADD CONSTRAINT recipe_category_recipe_id_fk
      FOREIGN KEY (recipe_id) REFERENCES recipe (id);
    ALTER TABLE barbotdb.recipe_category
      ADD CONSTRAINT recipe_category_category_id_fk
      FOREIGN KEY (category_id) REFERENCES category (id);

    INSERT INTO `migration`(migration,version,created_at,run_at)
    VALUES(migration,version,created_at,NOW());
  END IF;
  COMMIT;
END//
DELIMITER ;

CALL `do_current_update_script`('add_category_tables', '1.005', '2017-09-10');

-- END OF UPDATE SCRIPT --