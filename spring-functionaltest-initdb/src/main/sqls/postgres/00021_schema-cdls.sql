DROP TABLE IF EXISTS t_authority;
DROP TABLE IF EXISTS t_price;

DROP TABLE IF EXISTS t_cron_refresh_codelist;
DROP TABLE IF EXISTS t_refresh_codelist;

CREATE TABLE t_authority (
    authority_id VARCHAR(50) PRIMARY KEY,
    authority_name VARCHAR(50) NOT NULL
);

CREATE TABLE t_price (
    locale VARCHAR(50),
    code VARCHAR(50),
    label VARCHAR(50) NOT NULL,
    PRIMARY KEY(locale, code)
);

