CREATE OR REPLACE
    TRIGGER t_item_trigger BEFORE INSERT
            ON t_item FOR EACH ROW
            WHEN (
                new.item_id IS null
            ) BEGIN
                SELECT
                        s_item.nextval INTO
                            :new.item_id
                FROM
                    DUAL;
END t_item_trigger;
