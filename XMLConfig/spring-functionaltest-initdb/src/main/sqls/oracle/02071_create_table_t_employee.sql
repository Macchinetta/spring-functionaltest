CREATE
    TABLE t_employee(
        employee_id INT NOT NULL
        ,employee_name VARCHAR(30) NOT NULL
        ,email VARCHAR(50) NOT NULL
        ,address VARCHAR(500)
        ,CONSTRAINT pk_t_employee PRIMARY KEY (
            employee_id
            ,employee_name
        )
    )
