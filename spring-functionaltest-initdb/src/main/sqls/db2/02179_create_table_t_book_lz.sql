CREATE
    TABLE t_book_lz (
        book_id INTEGER NOT NULL
        ,category_id INTEGER NOT NULL
        ,title VARCHAR(1022) NOT NULL
        ,price INTEGER
        ,release_date DATE
        ,clob_code CLOB
        ,blob_code BLOB
        ,CONSTRAINT pk_book_lz PRIMARY KEY (book_id)
        ,CONSTRAINT fk_book_lz FOREIGN KEY (category_id) REFERENCES m_category_lz(category_id)
        ,CONSTRAINT uniqueTitle UNIQUE (title)
    )
