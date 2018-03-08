--------------------
-- DROP FUNCTION --
--------------------
CREATE OR REPLACE PROCEDURE DROP_PROCEDURE(procedureName VARCHAR2)
IS
	cnt int := 0;
BEGIN
	SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'PROCEDURE' AND OBJECT_NAME = UPPER(procedureName);
	if cnt > 0 then
		execute immediate 'DROP PROCEDURE ' || UPPER(procedureName);
	end if;
END;
