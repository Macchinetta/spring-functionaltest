CREATE
    TABLE stock(
        item_code VARCHAR(30)
        ,item_name VARCHAR(256)
        ,quantity NUMBER(10)
        ,version NUMBER(10)
        ,CONSTRAINT stock_pk PRIMARY KEY (item_code)
    )
