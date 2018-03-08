CREATE 
	TABLE t_order_jpa (
		id INTEGER,
		status_code VARCHAR(10),
		memo VARCHAR(100),
		amount INTEGER,
		is_logical_delete number,
		CONSTRAINT t_order_jpa_pk PRIMARY KEY(id),
		CONSTRAINT t_order_jpa_fk FOREIGN KEY(status_code) REFERENCES c_order_status_jpa(status_code)
	)