CREATE
    TABLE t_delivery_order(
        delivery_no NUMERIC(10) NOT NULL
        ,delivery_type_id NUMERIC(10) NOT NULL
        ,sender_name VARCHAR(256)
        ,sender_address VARCHAR(1024)
        ,reciever_name VARCHAR(256)
        ,reciever_address VARCHAR(1024)
        ,accept_datetime TIMESTAMP
        ,completion_datetime TIMESTAMP
        ,delivery_driver VARCHAR(256)
        ,delivery_status VARCHAR(128) NOT NULL
        ,CONSTRAINT pk_delivery_order PRIMARY KEY (delivery_no)
        ,CONSTRAINT fk_delivery_type_id FOREIGN KEY (delivery_type_id) REFERENCES m_delivery_type(delivery_type_id)
        ,CONSTRAINT fk_delivery_status FOREIGN KEY (delivery_status) REFERENCES t_delivery_status(delivery_status)
    )
