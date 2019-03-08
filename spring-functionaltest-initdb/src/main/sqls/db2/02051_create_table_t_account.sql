CREATE
    TABLE t_account(
        username VARCHAR(255) NOT NULL
        ,user_uuid VARCHAR(36) NOT NULL
        ,password VARCHAR(88) NOT NULL
        ,first_name VARCHAR(255)
        ,last_name VARCHAR(255)
        ,enabled NUMERIC(1) NOT NULL
        ,admin NUMERIC(1) NOT NULL
        ,CONSTRAINT pk_t_account PRIMARY KEY (username)
    )
