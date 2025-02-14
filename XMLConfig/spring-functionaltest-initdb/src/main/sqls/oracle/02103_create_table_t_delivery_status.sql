CREATE
    TABLE t_delivery_status(
        delivery_status VARCHAR(128)
        ,CONSTRAINT uk_delivery_status UNIQUE (delivery_status)
    )
