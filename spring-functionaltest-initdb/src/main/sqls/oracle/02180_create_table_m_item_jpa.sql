CREATE 
	TABLE m_item_jpa (
		code CHAR(10),
		name VARCHAR(256),
		price INTEGER,
		CONSTRAINT m_item_jpa_pk PRIMARY KEY(code)
	)