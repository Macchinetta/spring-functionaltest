CREATE TABLE t_order_mb3 (
    id number,
    status_code VARCHAR2(10),
    memo VARCHAR2(100),
    history_id number,
    CONSTRAINT t_order_mb3_pk PRIMARY KEY(id),
    CONSTRAINT t_order_mb3_fk FOREIGN KEY(status_code) REFERENCES c_order_status_mb3(code)
)
