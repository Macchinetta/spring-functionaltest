--------------------
-- DROP FUNCTION --
--------------------
CREATE OR REPLACE PROCEDURE DROP_PROCEDURE(IN procedureName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'PROCEDURE' AND OBJECT_NAME = UPPER(procedureName);
    if cnt > 0 then
        execute immediate 'DROP PROCEDURE ' || UPPER(procedureName);
    end if;
END
