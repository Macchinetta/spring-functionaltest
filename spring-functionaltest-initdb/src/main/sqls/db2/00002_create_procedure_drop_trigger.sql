--------------------
-- DROP TRIGGER --
--------------------
CREATE OR REPLACE PROCEDURE DROP_TRIGGER(IN triggerName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_OBJECTS WHERE OBJECT_TYPE = 'TRIGGER' AND OBJECT_NAME = UPPER(triggerName);
    if cnt > 0 then
        execute immediate 'DROP TRIGGER ' || UPPER(triggerName);
    end if;
END
