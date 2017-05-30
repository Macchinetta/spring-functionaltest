--------------------
-- DROP TRIGGER --
--------------------
CREATE OR REPLACE PROCEDURE DROP_TRIGGER(triggerName VARCHAR2)
IS
	cnt int := 0;
BEGIN
	SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'TRIGGER' AND OBJECT_NAME = UPPER(triggerName);
	if cnt > 0 then
		execute immediate 'DROP TRIGGER ' || UPPER(triggerName);
	end if;
END;
