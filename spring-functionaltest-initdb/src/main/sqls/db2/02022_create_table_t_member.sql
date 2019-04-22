CREATE
    TABLE t_member(
        member_id CHAR(10) NOT NULL
        ,first_name VARCHAR(20) NOT NULL
        ,last_name VARCHAR(20) NOT NULL
        ,first_name_kana VARCHAR(20) NOT NULL
        ,last_name_kana VARCHAR(20) NOT NULL
        ,age NUMERIC(10) NOT NULL
        ,gender CHAR(1) NOT NULL
        ,zip_code VARCHAR(8) NOT NULL
        ,state VARCHAR(30) NOT NULL
        ,city VARCHAR(30) NOT NULL
        ,address VARCHAR(30) NOT NULL
        ,occupation VARCHAR(20) NOT NULL
        ,mail_address VARCHAR(50) NOT NULL
        ,CONSTRAINT pk_t_member PRIMARY KEY (member_id)
    )
