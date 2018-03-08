--------------------
-- DROP TABLES --
--------------------
CREATE OR REPLACE PROCEDURE DROP_TABLE(tableName VARCHAR2)
IS
	cnt int := 0;
BEGIN
	SELECT COUNT(*) INTO cnt FROM USER_TABLES WHERE TABLE_NAME = UPPER(tableName);
	if cnt > 0 then
		execute immediate 'DROP TABLE ' || UPPER(tableName) || ' CASCADE CONSTRAINTS';
	end if;
END;
