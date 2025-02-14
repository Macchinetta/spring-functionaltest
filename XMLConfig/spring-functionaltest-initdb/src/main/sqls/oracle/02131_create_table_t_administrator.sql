CREATE
    TABLE t_administrator(
        username VARCHAR(30) NOT NULL
        ,password VARCHAR(255) NOT NULL
        ,enabled BOOLEAN NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_administrator PRIMARY KEY (username)
    )
