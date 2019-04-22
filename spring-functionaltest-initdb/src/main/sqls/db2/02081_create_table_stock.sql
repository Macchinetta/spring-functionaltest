CREATE
    TABLE stock(
        item_code VARCHAR(30) NOT NULL
        ,item_name VARCHAR(256)
        ,quantity NUMERIC(10)
        ,version NUMERIC(10)
        ,CONSTRAINT stock_pk PRIMARY KEY (item_code)
    )
