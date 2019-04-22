CREATE
    TABLE m_todo_cat(
        category_id CHAR(10) NOT NULL
        ,name VARCHAR(1024)
        ,CONSTRAINT pk_todo_cat PRIMARY KEY (category_id)
)
