DROP TABLE IF EXISTS t_member;
DROP SEQUENCE IF EXISTS s_member;
DROP TABLE IF EXISTS t_order_item;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_item;
DROP SEQUENCE IF EXISTS s_order;
DROP SEQUENCE IF EXISTS s_item;

CREATE SEQUENCE s_member;

CREATE TABLE t_member (
    member_id CHAR(10)
    ,first_name VARCHAR(20) NOT NULL
    ,last_name VARCHAR(20) NOT NULL
    ,first_name_kana VARCHAR(20) NOT NULL
    ,last_name_kana VARCHAR(20) NOT NULL
    ,age integer NOT NULL
    ,gender CHAR(1) NOT NULL
    ,zip_code VARCHAR(8) NOT NULL
    ,state VARCHAR(30) NOT NULL
    ,city VARCHAR(30) NOT NULL
    ,address VARCHAR(30) NOT NULL
    ,occupation VARCHAR(20) NOT NULL
    ,mail_address VARCHAR(50) NOT NULL
    ,CONSTRAINT pk_t_member PRIMARY KEY (member_id)
);

CREATE SEQUENCE s_item;

CREATE TABLE t_item (
    item_id CHAR(10) default TO_CHAR(NEXTVAL('s_item'),'IFM000000000')
    ,item_name VARCHAR(256) NOT NULL
    ,price integer NOT NULL
    ,overview_description VARCHAR(1024)
    ,detail_description TEXT
    ,CONSTRAINT pk_t_item PRIMARY KEY (item_id)
);

CREATE SEQUENCE s_order;

CREATE TABLE t_order (
    order_id CHAR(36)
    ,CONSTRAINT pk_t_order PRIMARY KEY (order_id)
);

CREATE TABLE t_order_item (
    order_id CHAR(36)
    ,item_id CHAR(10)
    ,quantity integer NOT NULL
    ,CONSTRAINT pk_t_order_item PRIMARY KEY (order_id, item_id)
    ,CONSTRAINT fk_t_order_item1 FOREIGN KEY (order_id) REFERENCES t_order(order_id)
    ,CONSTRAINT fk_t_order_item2 FOREIGN KEY (item_id) REFERENCES t_item(item_id)
);
