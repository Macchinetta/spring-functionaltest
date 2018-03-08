CREATE TABLE t_employee (
    employee_id int NOT NULL
    ,employee_name VARCHAR(30) NOT NULL
    ,email VARCHAR(50) NOT NULL
    ,address VARCHAR(500)
    ,CONSTRAINT pk_t_employee PRIMARY KEY (employee_id, employee_name)
);

CREATE TABLE t_article_file (
    file_id VARCHAR(36) NOT NULL
    ,title VARCHAR(50) NOT NULL
    ,content BLOB
    ,CONSTRAINT pk_t_article_file PRIMARY KEY (file_id)
);

