CREATE
    TABLE t_book_eg (
        book_id INTEGER NOT NULL
        ,category_id INTEGER NOT NULL
        ,title VARCHAR(1024)
        ,price INTEGER
        ,release_date DATE
        ,clob_code CLOB
        ,blob_code BLOB
        ,version INTEGER
        ,created_by VARCHAR(30) NOT NULL
        ,created_date TIMESTAMP NOT NULL
        ,last_modified_by VARCHAR(30) NOT NULL
        ,last_modified_date TIMESTAMP
        ,CONSTRAINT pk_book_eg PRIMARY KEY (book_id)
        ,CONSTRAINT fk_book_eg FOREIGN KEY (category_id) REFERENCES m_category_eg(category_id)
    )
