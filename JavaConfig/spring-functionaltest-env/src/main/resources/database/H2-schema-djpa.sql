DROP TABLE IF EXISTS t_book_lz;
DROP TABLE IF EXISTS m_category_lz;
DROP SEQUENCE IF EXISTS s_book_lz;
DROP SEQUENCE IF EXISTS s_jpa_delivery_no;

CREATE SEQUENCE s_book_lz;

CREATE SEQUENCE s_jpa_delivery_no start with 6 INCREMENT BY 1 ;

CREATE TABLE m_category_lz (
    category_id integer
    ,name VARCHAR(1024)
    ,CONSTRAINT pk_category_lz PRIMARY KEY (category_id)
);

CREATE TABLE t_book_lz (
    book_id INTEGER
    ,category_id CHAR(10)
    ,title VARCHAR(1024)
    ,price INTEGER
    ,release_date DATE
    ,clob_code CLOB
    ,blob_code BLOB
    ,CONSTRAINT pk_book_lz PRIMARY KEY (book_id)
    ,CONSTRAINT fk_book_lz FOREIGN KEY (category_id) REFERENCES m_category_lz(category_id)
    ,CONSTRAINT UniqueTitle UNIQUE (title)
);



DROP TABLE IF EXISTS t_book_eg;
DROP TABLE IF EXISTS m_category_eg;
DROP SEQUENCE IF EXISTS s_book_eg;

CREATE SEQUENCE s_book_eg start with 3 INCREMENT BY 1 ;

CREATE TABLE m_category_eg (
    category_id integer
    ,name VARCHAR(1024)
    ,CONSTRAINT pk_category_eg PRIMARY KEY (category_id)
);

CREATE TABLE t_book_eg (
    book_id INTEGER
    ,category_id CHAR(10)
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
);
-- ***********************************************************************************************


DROP TABLE IF EXISTS t_order_item_jpa;
DROP TABLE IF EXISTS t_order_jpa;
DROP TABLE IF EXISTS c_order_status_jpa;
DROP TABLE IF EXISTS m_item_jpa;

DROP SEQUENCE IF EXISTS s_order_jpa;
DROP SEQUENCE IF EXISTS s_order_item_jpa;

CREATE SEQUENCE s_order_jpa start with 8 INCREMENT BY 1 ;
CREATE SEQUENCE s_order_item_jpa start with 13 INCREMENT BY 1 ;

CREATE TABLE m_item_jpa (
    code CHAR(10),
    name VARCHAR(256),
    price INTEGER,
    CONSTRAINT m_item_jpa_pk PRIMARY KEY(code)
);

CREATE TABLE c_order_status_jpa (
    status_code VARCHAR(10),
    name VARCHAR(256),
    CONSTRAINT c_order_status_jpa_pk PRIMARY KEY(status_code)
);

CREATE TABLE t_order_jpa (
    id INTEGER,
    status_code VARCHAR(10),
    memo VARCHAR(100),
    amount INTEGER,
    is_logical_delete BOOLEAN,
    CONSTRAINT t_order_jpa_pk PRIMARY KEY(id),
    CONSTRAINT t_order_jpa_fk FOREIGN KEY(status_code) REFERENCES c_order_status_jpa(status_code)
);

CREATE TABLE t_order_item_jpa (
    order_id INTEGER,
    item_num INTEGER,
    item_code CHAR(10),
    quantity INTEGER,
    logical_delete BOOLEAN,
    CONSTRAINT t_order_item_jpa_pk PRIMARY KEY(item_num),
    CONSTRAINT t_order_item_jpa_fk1 FOREIGN KEY(order_id) REFERENCES t_order_jpa(id),
    CONSTRAINT t_order_item_jpa_fk2 FOREIGN KEY(item_code) REFERENCES m_item_jpa(code)
);


