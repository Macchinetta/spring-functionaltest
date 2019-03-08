CREATE
    TABLE t_rest_member (
        member_id CHAR(10) NOT NULL
        ,first_name VARCHAR(128) NOT NULL
        ,last_name VARCHAR(128) NOT NULL
        ,gender CHAR(1) NOT NULL
        ,date_of_birth DATE NOT NULL
        ,email_address VARCHAR(256) NOT NULL
        ,telephone_number VARCHAR(20)
        ,zip_code VARCHAR(20)
        ,address VARCHAR(256)
        ,created_at TIMESTAMP NOT NULL
        ,last_modified_at TIMESTAMP NOT NULL
        ,version integer NOT NULL
        ,CONSTRAINT pk_t_rest_member PRIMARY KEY (member_id)
)
