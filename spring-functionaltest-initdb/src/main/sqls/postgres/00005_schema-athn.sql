DROP TABLE IF EXISTS t_account;
DROP TABLE IF EXISTS t_customer;
DROP TABLE IF EXISTS t_administrator;

CREATE TABLE t_account (
    username VARCHAR(255),
    user_uuid VARCHAR(36) NOT NULL,
    password VARCHAR(60) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    enabled BOOLEAN NOT NULL,
    admin BOOLEAN NOT NULL,
    CONSTRAINT pk_t_account PRIMARY KEY (username)
);

CREATE TABLE t_customer (
    customer_name VARCHAR(30) NOT NULL
    ,password VARCHAR(100) NOT NULL
    ,company_id VARCHAR(10) NOT NULL
    ,customer_address VARCHAR(100) NOT NULL
    ,enabled BOOLEAN NOT NULL
    ,authority VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_t_customer PRIMARY KEY (customer_name)
);

CREATE TABLE t_administrator (
    username VARCHAR(30) NOT NULL
    ,password VARCHAR(128) NOT NULL
    ,enabled BOOLEAN NOT NULL
    ,authority VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_t_administrator PRIMARY KEY (username)
);

