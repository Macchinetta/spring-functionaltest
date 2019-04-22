CREATE
    TABLE t_customer(
        customer_name VARCHAR(30) NOT NULL
        ,password VARCHAR(100) NOT NULL
        ,company_id VARCHAR(10) NOT NULL
        ,customer_address VARCHAR(100) NOT NULL
        ,enabled NUMBER(1) NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_customer PRIMARY KEY (customer_name)
    )
