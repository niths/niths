--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `database`.`committees` DROP FOREIGN KEY `FK3CBFF896CC919F59`;

ALTER TABLE `database`.`committeeevents` DROP FOREIGN KEY `FK33C86D67DE0CE67`;

ALTER TABLE `database`.`committeeevents` DROP PRIMARY KEY;

ALTER TABLE `database`.`students` DROP PRIMARY KEY;

ALTER TABLE `database`.`committees` DROP PRIMARY KEY;

ALTER TABLE `database`.`courses` DROP PRIMARY KEY;

ALTER TABLE `database`.`students_committees` DROP INDEX `database`.`FKF6E9999D1499206A`;

ALTER TABLE `database`.`committees_committeeevents` DROP INDEX `database`.`FK69B5E92DB7375D04`;

ALTER TABLE `database`.`committees_students` DROP INDEX `database`.`FK67F647213853E149`;

ALTER TABLE `database`.`students_committees` DROP INDEX `database`.`committees_id`;

ALTER TABLE `database`.`committees_students` DROP INDEX `database`.`FK67F64721F91F8AAE`;

ALTER TABLE `database`.`committees_committeeevents` DROP INDEX `database`.`events_id`;

ALTER TABLE `database`.`students_courses` DROP INDEX `database`.`FK8687781162089A80`;

ALTER TABLE `database`.`committeeevents` DROP INDEX `database`.`FK33C86D67DE0CE67`;

ALTER TABLE `database`.`students_courses` DROP INDEX `database`.`cources_id`;

ALTER TABLE `database`.`committees_students` DROP INDEX `database`.`members_id`;

ALTER TABLE `database`.`committees` DROP INDEX `database`.`FK3CBFF896CC919F59`;

ALTER TABLE `database`.`committees_committeeevents` DROP INDEX `database`.`FK69B5E92DF91F8AAE`;

ALTER TABLE `database`.`students_courses` DROP INDEX `database`.`FK868778111499206A`;

ALTER TABLE `database`.`students_committees` DROP INDEX `database`.`FKF6E9999DF91F8AAE`;

DROP TABLE `database`.`courses`;

DROP TABLE `database`.`students_courses`;

DROP TABLE `database`.`committees_students`;

DROP TABLE `database`.`committeeevents`;

DROP TABLE `database`.`students`;

DROP TABLE `database`.`students_committees`;

DROP TABLE `database`.`committees_committeeevents`;

DROP TABLE `database`.`committees`;

CREATE TABLE `database`.`courses` (
	`id` BIGINT NOT NULL,
	`description` VARCHAR(255),
	`name` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `database`.`students_courses` (
	`students_id` BIGINT NOT NULL,
	`cources_id` BIGINT NOT NULL
);

CREATE TABLE `database`.`committees_students` (
	`committees_id` BIGINT NOT NULL,
	`members_id` BIGINT NOT NULL
);

CREATE TABLE `database`.`committeeevents` (
	`id` BIGINT NOT NULL,
	`description` VARCHAR(255),
	`name` VARCHAR(255),
	`time` DATE,
	`committee_id` BIGINT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `database`.`students` (
	`id` BIGINT NOT NULL,
	`description` VARCHAR(255),
	`name` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `database`.`students_committees` (
	`students_id` BIGINT NOT NULL,
	`committees_id` BIGINT NOT NULL
);

CREATE TABLE `database`.`committees_committeeevents` (
	`committees_id` BIGINT NOT NULL,
	`events_id` BIGINT NOT NULL
);

CREATE TABLE `database`.`committees` (
	`id` BIGINT NOT NULL,
	`description` VARCHAR(255),
	`name` VARCHAR(255),
	`leader_id` BIGINT,
	PRIMARY KEY (`id`)
);

CREATE INDEX `FKF6E9999D1499206A` ON `database`.`students_committees` (`students_id` ASC);

CREATE INDEX `FK69B5E92DB7375D04` ON `database`.`committees_committeeevents` (`events_id` ASC);

CREATE INDEX `FK67F647213853E149` ON `database`.`committees_students` (`members_id` ASC);

CREATE UNIQUE INDEX `committees_id` ON `database`.`students_committees` (`committees_id` ASC);

CREATE INDEX `FK67F64721F91F8AAE` ON `database`.`committees_students` (`committees_id` ASC);

CREATE UNIQUE INDEX `events_id` ON `database`.`committees_committeeevents` (`events_id` ASC);

CREATE INDEX `FK8687781162089A80` ON `database`.`students_courses` (`cources_id` ASC);

CREATE INDEX `FK33C86D67DE0CE67` ON `database`.`committeeevents` (`committee_id` ASC);

CREATE UNIQUE INDEX `cources_id` ON `database`.`students_courses` (`cources_id` ASC);

CREATE UNIQUE INDEX `members_id` ON `database`.`committees_students` (`members_id` ASC);

CREATE INDEX `FK3CBFF896CC919F59` ON `database`.`committees` (`leader_id` ASC);

CREATE INDEX `FK69B5E92DF91F8AAE` ON `database`.`committees_committeeevents` (`committees_id` ASC);

CREATE INDEX `FK868778111499206A` ON `database`.`students_courses` (`students_id` ASC);

CREATE INDEX `FKF6E9999DF91F8AAE` ON `database`.`students_committees` (`committees_id` ASC);

ALTER TABLE `database`.`committees` ADD CONSTRAINT `FK3CBFF896CC919F59` FOREIGN KEY (`leader_id`)
	REFERENCES `database`.`students` (`id`);

ALTER TABLE `database`.`committeeevents` ADD CONSTRAINT `FK33C86D67DE0CE67` FOREIGN KEY (`committee_id`)
	REFERENCES `database`.`committees` (`id`);

