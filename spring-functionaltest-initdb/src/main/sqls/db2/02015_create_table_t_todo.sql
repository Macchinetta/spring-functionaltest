CREATE
    TABLE t_todo(
        todo_id CHAR(10) NOT NULL
        ,category_id CHAR(10)
        ,todo_title VARCHAR(1024)
        ,finished NUMERIC(1)
        ,created_at DATE
        ,complete_at Timestamp
        ,version INTEGER
        ,Desc1 BLOB
        ,Desc2 CLOB
        ,CONSTRAINT pk_todo PRIMARY KEY (todo_id)
        ,CONSTRAINT fk_todo FOREIGN KEY (category_id) REFERENCES m_todo_cat(category_id)
)