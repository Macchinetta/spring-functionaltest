CREATE 
	TABLE t_order_item_jpa (
		order_id INTEGER,
		item_num INTEGER,
		item_code CHAR(10),
		quantity INTEGER,
		logical_delete number,
		CONSTRAINT t_order_item_jpa_pk PRIMARY KEY(item_num),
		CONSTRAINT t_order_item_jpa_fk1 FOREIGN KEY(order_id) REFERENCES t_order_jpa(id),
		CONSTRAINT t_order_item_jpa_fk2 FOREIGN KEY(item_code) REFERENCES m_item_jpa(code)
	)