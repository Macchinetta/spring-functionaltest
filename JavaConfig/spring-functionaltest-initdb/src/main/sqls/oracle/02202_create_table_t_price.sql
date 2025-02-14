CREATE TABLE t_price (
    locale VARCHAR(50),
    code VARCHAR(50),
    label VARCHAR(50) NOT NULL,
    PRIMARY KEY(locale, code)
)
