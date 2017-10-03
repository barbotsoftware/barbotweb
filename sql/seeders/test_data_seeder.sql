USE `barbotdb`;

/* empty out the database */
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE `barbot`;
TRUNCATE TABLE `ingredient`;
TRUNCATE TABLE `barbot_container`;
TRUNCATE TABLE `category`;
TRUNCATE TABLE `recipe`;
TRUNCATE TABLE `recipe_ingredient`;
TRUNCATE TABLE `recipe_category`;
TRUNCATE TABLE `user`;
SET FOREIGN_KEY_CHECKS=1;

/* populate barbot table */
INSERT INTO barbot(uid, name, password, created_at, updated_at, status) VALUES ('d9f064', 'sloshnet', '$2a$10$F2ZzOsCBrO4LSsrXZAR4eOnB/MqmXS3LzJcH9vXPIqCS6cLWPQ62O', NOW(), NOW(), 1);

/* populate ingredients table */
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('fbcbfb', 'tequila', '40', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('b0166b', 'vodka', '40', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('f35a9d', 'rum', '40', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('759692', 'whiskey', '50', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('64FC05', 'gin', '40', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('E09AAC', 'energy drink', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('6C740C', 'orange juice', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('BE9942', 'grenadine', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('BE355C', 'blue curacao', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('CED1F0', 'cranberry juice', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('766774', 'cola', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('6FFE07', 'lemon juice', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('EBBD95', 'simple syrup', '0', NOW(), NOW());
INSERT INTO ingredient(uid, name, abv, created_at, updated_at) VALUES ('544ECC', 'lime juice', '0', NOW(), NOW());

/* populate barbot_container table */
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 1, 1,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 2, 2,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 3, 3,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 4, 4,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 5, 5,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 6, 6,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 7, 7,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 8, 8,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 9, 9,100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 10, 10, 100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 11, 11, 100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 12, 12, 100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 13, 13, 100, 100, NOW(), NOW());
INSERT INTO barbot_container(barbot_id, ingredient_id, number, current_volume, max_volume, created_at, updated_at) VALUES (1, 14, 14, 100, 100, NOW(), NOW());

/* popoulate base categories */
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('A7F3A7', NULL, 'Popular', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('97F0F9', NULL, 'Cocktail Color', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('9C6ECB', NULL, 'Mood', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('CF9BD6', NULL, 'Celebrations', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('ED0167', NULL, 'Base Ingredient', NOW(), NOW());

/* populate sub-categories */
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('3675F5', 2, 'Black Cocktails', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('BE95CB', 2, 'Blue Cocktails', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('FB5900', 2, 'Green Cocktails', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('2F96E6', 2, 'Orange Cocktails', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('7AFA0A', 3, 'Sad', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('8DF5C6', 3, 'Happy', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('265AEE', 3, 'Fuckin Horny', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('5247F5', 4, 'Christmas', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('A52E9C', 4, 'Birthday', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('AA8940', 4, 'Halloween', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('DCFE0D', 4, 'Independence Day', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('5DAD28', 4, 'New Years', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('7275C6', 5, 'Tequila', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('35A6E3', 5, 'Rum', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('E95052', 5, 'Gin', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('5B71CF', 5, 'Whiskey', NOW(), NOW());
INSERT INTO category(uid, parent_category_id, name, created_at, updated_at) VALUES('C2CC3B', 5, 'Vodka', NOW(), NOW());

/* populate recipes */
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('9F25E9', 'panda juice', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('B1A3C3', 'pandacide', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('47A028', 'pandamonium', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('406C34', 'pandalicious', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('C3BDAA', 'jizz', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('56ADDC', 'smegma', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('4D190D', 'tequila sunrise', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('7789DE', 'rum and cock', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('AA5621', 'margarita', 0, 0, 'dickbutt.png', NOW(), NOW());
INSERT INTO recipe(uid, name, created_by, custom, image_url, created_at, updated_at) VALUES ('F26F14', 'barbot special', 0, 0, 'dickbutt.png', NOW(), NOW());

/* populate recipe_categories */
INSERT INTO recipe_category(recipe_id, category_id) VALUES (1,1);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (1,6);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (1,8);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (2,9);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (2,10);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (3,1);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (5,12);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (6,12);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (8,12);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (4,13);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (5,15);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (6,16);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (7,1);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (8,10);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (9,6);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (10,5);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (10,6);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (4,1);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (7,9);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (2,10);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (3,15);
INSERT INTO recipe_category(recipe_id, category_id) VALUES (9,18);

/* populate recipe_ingredients */
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (1, 1, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (1, 12, 2.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (1, 13, 3.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (2, 2, 1.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (2, 4, 3.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (3, 5, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (3, 3, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (4, 5, 4.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (4, 6, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (4, 7, 2.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (5, 3, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (5, 10, 3.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (5, 11, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (6, 4, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (6, 14, 2.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (6, 9, 1.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (7, 1, 1.40, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (7, 13, 2.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (7, 11, 3.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (8, 3, 1.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (8, 11, 2.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (9, 5, 1.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (9, 12, 3.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (10, 3, 2.00, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (10, 12, 1.50, NOW(), NOW());
INSERT INTO recipe_ingredient(recipe_id, ingredient_id, amount, created_at, updated_at) VALUES (10, 8, 2.50, NOW(), NOW());

/* populate users */
INSERT INTO user (uid, name, email, password, created_at, updated_at) VALUES ('4aeefa', 'panda', 'panda@panda.com', '$2a$10$F2ZzOsCBrO4LSsrXZAR4eOnB/MqmXS3LzJcH9vXPIqCS6cLWPQ62O', NOW(), NOW());
INSERT INTO user (uid, name, email, password, created_at, updated_at) VALUES ('4a8481', 'penis', 'penis@panda.com', '$2a$10$F2ZzOsCBrO4LSsrXZAR4eOnB/MqmXS3LzJcH9vXPIqCS6cLWPQ62O', NOW(), NOW());