CREATE
    TABLE t_staff(
        username VARCHAR(30) NOT NULL
        ,password VARCHAR(100) NOT NULL
        ,enabled NUMBER(1) NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_staff PRIMARY KEY (username)
    )
