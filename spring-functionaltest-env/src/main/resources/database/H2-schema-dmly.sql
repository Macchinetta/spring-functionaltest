DROP TABLE IF EXISTS t_delivery_order;
DROP TABLE IF EXISTS t_delivery_status;
DROP TABLE IF EXISTS m_delivery_type;
DROP SEQUENCE IF EXISTS s_delivery_no;

CREATE SEQUENCE s_delivery_no;

CREATE TABLE m_delivery_type (
    delivery_type_id INTEGER
    ,delivery_type_name VARCHAR(1024)
    ,CONSTRAINT pk_delivery_type PRIMARY KEY (delivery_type_id)
);

CREATE TABLE t_delivery_status (
    delivery_status VARCHAR(128)
    ,CONSTRAINT pk_delivery_status PRIMARY KEY (delivery_status)
);

CREATE TABLE t_delivery_order (
    delivery_no INTEGER
    ,delivery_type_id INTEGER
    ,sender_name VARCHAR(256)
    ,sender_address VARCHAR(1024)
    ,reciever_name VARCHAR(256)
    ,reciever_address VARCHAR(1024)
    ,accept_datetime DATETIME
    ,completion_datetime DATETIME
    ,delivery_driver VARCHAR(256)
    ,delivery_status VARCHAR(128)
    ,CONSTRAINT pk_delivery_order PRIMARY KEY (delivery_no)
    ,CONSTRAINT fk_delivery_type_id FOREIGN KEY (delivery_type_id) REFERENCES m_delivery_type(delivery_type_id)
    ,CONSTRAINT fk_delivery_status FOREIGN KEY (delivery_status) REFERENCES t_delivery_status(delivery_status)
);
