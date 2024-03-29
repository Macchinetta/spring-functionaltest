CREATE
    TABLE t_account (
        username VARCHAR(255)
        ,user_uuid VARCHAR(36) NOT NULL
        ,password VARCHAR(110) NOT NULL
        ,first_name VARCHAR(255)
        ,last_name VARCHAR(255)
        ,enabled NUMBER(1) NOT NULL
        ,admin NUMBER(1) NOT NULL
        ,CONSTRAINT pk_t_account PRIMARY KEY (username)
    )