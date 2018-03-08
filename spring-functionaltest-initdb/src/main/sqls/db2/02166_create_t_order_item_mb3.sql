CREATE TABLE t_order_item_mb3 (
    order_id NUMERIC NOT NULL,
    item_code CHAR(10) NOT NULL,
    quantity NUMERIC,
    CONSTRAINT t_order_item_mb3_pk PRIMARY KEY(order_id, item_code),
    CONSTRAINT t_order_item_mb3_fk1 FOREIGN KEY(order_id) REFERENCES t_order_mb3(id),
    CONSTRAINT t_order_item_mb3_fk2 FOREIGN KEY(item_code) REFERENCES m_item_mb3(code)
)
