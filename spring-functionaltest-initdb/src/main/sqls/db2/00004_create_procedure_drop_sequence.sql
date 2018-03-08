--------------------
-- DROP SEQUENCE --
--------------------
CREATE OR REPLACE PROCEDURE DROP_SEQUENCE(IN sequenceName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_SEQUENCES WHERE SEQUENCE_NAME = UPPER(sequenceName);
    if cnt > 0 then
        execute immediate 'DROP SEQUENCE ' || UPPER(sequenceName);
    end if;
END
