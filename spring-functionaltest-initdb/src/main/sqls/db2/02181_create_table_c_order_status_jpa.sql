CREATE
    TABLE c_order_status_jpa (
        status_code VARCHAR(10) NOT NULL,
        name VARCHAR(256),
        CONSTRAINT c_order_status_jpa_pk PRIMARY KEY(status_code)
    )
