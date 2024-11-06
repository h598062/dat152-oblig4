-- DB, if you want to use MySQL.
-- 
-- Login: mysql -h localhost -u dat152 --password=jGt3eRf dat152_obl
--
CREATE DATABASE SecOblig;

-- Create MySQL-user:
CREATE USER dat152@'%';
SET PASSWORD for 'dat152'@'%'= 'jGt3eRf';
-- refer to: https://dev.mysql.com/doc/refman/8.0/en/set-password.html

-- Grant rights to user dat152:
GRANT ALTER,ALTER ROUTINE,CREATE,CREATE ROUTINE,CREATE TEMPORARY TABLES,CREATE VIEW,DELETE,
  DROP,EXECUTE,INDEX,INSERT,LOCK TABLES,SELECT,SHOW VIEW,UPDATE ON  SecOblig.* TO 'dat152'@'%';

-- Change database to SecOblig;
USE SecOblig;

DROP TABLE IF EXISTS History;
DROP TABLE IF EXISTS AppUser;

-- To Create the AppUser table
CREATE TABLE AppUser (
  username      VARCHAR(50), 
  passhash      VARCHAR(50),
  firstname     VARCHAR(50),
  lastname      VARCHAR(50),
  mobilephone   VARCHAR(20),
  role   VARCHAR(10),
  PRIMARY KEY (username, passhash)
) ENGINE=InnoDB;

-- To create the History table
CREATE TABLE History (
  datetime      TIMESTAMP, 
  username      VARCHAR(50),
  searchkey     VARCHAR(50),
  PRIMARY KEY (datetime, username),
  FOREIGN KEY (username) REFERENCES user(username)
) ENGINE=InnoDB;

-- To see that everything works correctly
DESCRIBE AppUser;
DESCRIBE History;
