CREATE OR REPLACE
    TRIGGER t_item_trigger
        BEFORE INSERT ON t_item
        REFERENCING new AS new
        FOR EACH ROW
        WHEN (new.item_id IS NULL)
        BEGIN ATOMIC
            SET new.item_id = NEXT VALUE FOR s_item;
END
