CREATE
    TABLE t_user(
        username VARCHAR(30) NOT NULL
        ,password VARCHAR(110) NOT NULL
        ,enabled NUMBER(1) NOT NULL
        ,authority VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_user PRIMARY KEY (username)
    )
