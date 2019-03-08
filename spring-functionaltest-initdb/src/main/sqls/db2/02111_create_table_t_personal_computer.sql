CREATE
    TABLE t_personal_computer(
        personal_computer_id NUMERIC(10) NOT NULL
        ,personal_computer_name VARCHAR(50) NOT NULL
        ,os VARCHAR(50) NOT NULL
        ,cpu VARCHAR(50) NOT NULL
        ,ram VARCHAR(50) NOT NULL
        ,videocard VARCHAR(50) NOT NULL
        ,hdd VARCHAR(50) NOT NULL
        ,POWER VARCHAR(50) NOT NULL
        ,price NUMERIC(10) NOT NULL
        ,CONSTRAINT pk_t_personal_computer PRIMARY KEY (personal_computer_id)
    )
