CREATE
    TABLE m_delivery_type(
        delivery_type_id NUMERIC(10) NOT NULL
        ,delivery_type_name VARCHAR(1024)
        ,CONSTRAINT pk_delivery_type PRIMARY KEY (delivery_type_id)
    )
