CREATE
    TABLE t_order_item_jpa (
        order_id INTEGER NOT NULL,
        item_num INTEGER NOT NULL,
        item_code CHAR(10) NOT NULL,
        quantity INTEGER,
        logical_delete NUMERIC,
        CONSTRAINT t_order_item_jpa_pk PRIMARY KEY(item_num),
        CONSTRAINT t_order_item_jpa_fk1 FOREIGN KEY(order_id) REFERENCES t_order_jpa(id),
        CONSTRAINT t_order_item_jpa_fk2 FOREIGN KEY(item_code) REFERENCES m_item_jpa(code)
    )
