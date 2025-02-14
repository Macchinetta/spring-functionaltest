CREATE
    TABLE t_book(
        book_id CHAR(10)
        ,category_id CHAR(10)
        ,title VARCHAR(1024)
        ,price NUMBER(10)
        ,release_date DATE
        ,clob_code CLOB
        ,blob_code BLOB
        ,CONSTRAINT pk_book PRIMARY KEY (book_id)
        ,CONSTRAINT fk_book FOREIGN KEY (category_id) REFERENCES m_category(category_id)
    )
