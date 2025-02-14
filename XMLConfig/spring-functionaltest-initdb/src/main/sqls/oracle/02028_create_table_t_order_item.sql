CREATE
    TABLE t_order_item(
        order_id VARCHAR(36)
        ,item_id VARCHAR(10)
        ,quantity NUMBER(10) NOT NULL
        ,CONSTRAINT pk_t_order_item PRIMARY KEY (
            order_id
            ,item_id
        )
        ,CONSTRAINT fk_t_order_item1 FOREIGN KEY (order_id) REFERENCES t_order(order_id)
        ,CONSTRAINT fk_t_order_item2 FOREIGN KEY (item_id) REFERENCES t_item(item_id)
    )
