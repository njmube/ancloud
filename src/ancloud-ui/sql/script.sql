CREATE ROLE ancloud LOGIN ENCRYPTED PASSWORD 'md5f2059c8c6d25b2c4457186880f0acaf3'
  SUPERUSER CREATEDB CREATEROLE
   VALID UNTIL 'infinity';
   
-- Create database
CREATE DATABASE ancloud
  WITH ENCODING='UTF8'
       OWNER=ancloud
       CONNECTION LIMIT=-1;

CREATE SEQUENCE seq_bb_message START 1 INCREMENT 1;
DROP TABLE bb_message CASCADE;
CREATE TABLE bb_message (
	id numeric(16,0) PRIMARY KEY DEFAULT nextval('seq_bb_message'),
	key VARCHAR( 255 ) NULL ,
    language VARCHAR( 7 ) NULL ,
    country VARCHAR( 7 ) NULL ,
    basename VARCHAR( 31 ) NOT NULL ,
    variant VARCHAR( 7 ) NULL ,
    message TEXT NULL
);
