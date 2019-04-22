CREATE
    TABLE t_item(
        item_id VARCHAR(10) NOT NULL
        ,item_name VARCHAR(256) NOT NULL
        ,price NUMBER(10) NOT NULL
        ,overview_description VARCHAR(1024)
        ,detail_description CLOB
        ,CONSTRAINT pk_t_item PRIMARY KEY (item_id)
    )
