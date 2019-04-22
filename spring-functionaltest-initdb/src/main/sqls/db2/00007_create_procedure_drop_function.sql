--------------------
-- DROP FUNCTION --
--------------------
CREATE OR REPLACE PROCEDURE DROP_FUNCTION(IN functionName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'FUNCTION' AND OBJECT_NAME = UPPER(functionName);
    if cnt > 0 then
        execute immediate 'DROP FUNCTION ' || UPPER(functionName);
    end if;
END
