CREATE TABLE t_authority (
    authority_id CHAR(50) PRIMARY KEY,
    authority_name CHAR(50) NOT NULL
);

CREATE TABLE t_price (
    locale CHAR(50),
    code char(50),
    label CHAR(50) NOT NULL,
    PRIMARY KEY(locale, code)
);
