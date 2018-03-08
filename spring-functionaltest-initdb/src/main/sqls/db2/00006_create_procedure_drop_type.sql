--------------------
-- DROP FUNCTION --
--------------------
CREATE OR REPLACE PROCEDURE DROP_TYPE(IN typeName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'ABSTRACT DATATYPE' AND OBJECT_NAME = UPPER(typeName);
    if cnt > 0 then
        execute immediate 'DROP TYPE ' || UPPER(typeName);
    end if;
END
