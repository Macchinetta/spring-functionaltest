--------------------
-- DROP VIEW --
--------------------
CREATE OR REPLACE PROCEDURE DROP_VIEW(IN viewName VARCHAR(50))
LANGUAGE SQL
BEGIN
    DECLARE cnt INTEGER DEFAULT 0;

    SELECT COUNT(*) INTO cnt FROM USER_VIEWS WHERE VIEW_NAME = UPPER(viewName);
    if cnt > 0 then
        execute immediate 'DROP VIEW ' || UPPER(viewName);
    end if;
END
