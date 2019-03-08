DROP TABLE IF EXISTS t_book;
DROP TABLE IF EXISTS m_category;
DROP TABLE IF EXISTS t_todo;
DROP TABLE IF EXISTS m_todo_cat;
DROP SEQUENCE IF EXISTS s_book;

CREATE SEQUENCE s_book;

CREATE TABLE m_category (
    category_id CHAR(10)
    ,name VARCHAR(1024)
    ,CONSTRAINT pk_category PRIMARY KEY (category_id)
);

CREATE TABLE t_book (
    book_id CHAR(10)
    ,category_id CHAR(10)
    ,title VARCHAR(1024)
    ,price INTEGER
    ,release_date DATE
    ,clob_code CLOB
    ,blob_code BLOB
    ,CONSTRAINT pk_book PRIMARY KEY (book_id)
    ,CONSTRAINT fk_book FOREIGN KEY (category_id) REFERENCES m_category(category_id)
);

CREATE TABLE m_todo_cat (
    category_id CHAR(10)
    ,name VARCHAR(1024)
    ,CONSTRAINT pk_todo_cat PRIMARY KEY (category_id)
);

CREATE TABLE t_todo (
    todo_id CHAR(10)
    ,category_id CHAR(10)
    ,todo_title VARCHAR(1024)
    ,finished BOOLEAN
    ,created_at DATE
    ,complete_at Timestamp
    ,version INTEGER
    ,Desc1 BLOB
    ,Desc2 CLOB
    ,CONSTRAINT pk_todo PRIMARY KEY (todo_id)
    ,CONSTRAINT fk_todo FOREIGN KEY (category_id) REFERENCES m_todo_cat(category_id)
);



CREATE ALIAS findTodo FOR "jp.co.ntt.fw.spring.functionaltest.domain.service.dam3.H2Function.findTodo";

