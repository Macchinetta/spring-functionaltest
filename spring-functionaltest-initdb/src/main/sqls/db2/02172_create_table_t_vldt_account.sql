CREATE
    TABLE t_vldt_account(
        username VARCHAR(30) NOT NULL
        ,password VARCHAR(100) NOT NULL
        ,enabled NUMERIC(1) NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_vldt_account PRIMARY KEY (username)
    )
