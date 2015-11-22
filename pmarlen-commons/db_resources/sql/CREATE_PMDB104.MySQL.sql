use mysql;

DROP DATABASE IF EXISTS PMDB104_DEVE;
create database PMDB104_DEVE;

DROP DATABASE IF EXISTS PMDB104_PROD;
create database PMDB104_PROD;


DELETE FROM user WHERE user='PMDB104_DEVE_USR';
GRANT SELECT,INSERT,UPDATE,DELETE ON PMDB104_DEVE.* TO 'PMDB104_DEVE_USR'@'localhost' IDENTIFIED BY 'PMDB104_DEVE_PWD';
GRANT SELECT,INSERT,UPDATE,DELETE ON PMDB104_DEVE.* TO 'PMDB104_DEVE_USR'@'%'         IDENTIFIED BY 'PMDB104_DEVE_PWD';

DELETE FROM user WHERE user='PMDB104_PROD_USR';
GRANT SELECT,INSERT,UPDATE,DELETE ON PMDB104_PROD.* TO 'PMDB104_PROD_USR'@'localhost' IDENTIFIED BY 'PMDB104_PROD_PWD';
GRANT SELECT,INSERT,UPDATE,DELETE ON PMDB104_PROD.* TO 'PMDB104_PROD_USR'@'%'         IDENTIFIED BY 'PMDB104_PROD_PWD';