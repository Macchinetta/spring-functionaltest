CREATE 
	TABLE t_book_lz (
		book_id INTEGER
		,category_id integer
		,title VARCHAR(1024)
		,price INTEGER
		,release_date DATE
		,clob_code CLOB
		,blob_code BLOB
		,CONSTRAINT pk_book_lz PRIMARY KEY (book_id)
		,CONSTRAINT fk_book_lz FOREIGN KEY (category_id) REFERENCES m_category_lz(category_id)
		,CONSTRAINT uniqueTitle unique (title)
	)