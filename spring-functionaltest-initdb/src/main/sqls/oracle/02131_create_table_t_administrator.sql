CREATE
    TABLE t_administrator(
        username VARCHAR(30) NOT NULL
        ,password VARCHAR(128) NOT NULL
        ,enabled CHAR(1) NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_administrator PRIMARY KEY (username)
    )
