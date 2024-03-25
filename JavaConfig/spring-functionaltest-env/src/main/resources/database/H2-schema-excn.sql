DROP TABLE IF EXISTS stock;

CREATE TABLE stock (
    item_code CHAR(30)
    ,item_name VARCHAR(256)
    ,quantity integer
    ,version integer
    ,CONSTRAINT stock_pk PRIMARY KEY (item_code)
);
