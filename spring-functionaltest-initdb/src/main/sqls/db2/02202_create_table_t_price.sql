CREATE TABLE t_price (
    locale VARCAHR(50),
    code VARCAHR(50),
    label VARCAHR(50) NOT NULL,
    PRIMARY KEY(locale, code)
)
