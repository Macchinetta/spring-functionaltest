CREATE TABLE t_order_mb3 (
    id NUMERIC NOT NULL,
    status_code VARCHAR(10) NOT NULL,
    memo VARCHAR(100),
    history_id NUMERIC,
    CONSTRAINT t_order_mb3_pk PRIMARY KEY(id),
    CONSTRAINT t_order_mb3_fk FOREIGN KEY(status_code) REFERENCES c_order_status_mb3(code)
)
