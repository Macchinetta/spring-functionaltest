CREATE
    TABLE t_celebrity(
        celebrity_id NUMERIC(10) NOT NULL
        ,first_name VARCHAR(30) NOT NULL
        ,last_name VARCHAR(30) NOT NULL
        ,CONSTRAINT pk_t_celebrity PRIMARY KEY (celebrity_id)
    )
