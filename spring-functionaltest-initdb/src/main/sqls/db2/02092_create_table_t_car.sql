CREATE
    TABLE t_car(
        car_id CHAR(10) NOT NULL
        ,name VARCHAR(60)
        ,color VARCHAR(60)
        ,release_date DATE
        ,CONSTRAINT pk_t_car PRIMARY KEY (car_id)
    )
