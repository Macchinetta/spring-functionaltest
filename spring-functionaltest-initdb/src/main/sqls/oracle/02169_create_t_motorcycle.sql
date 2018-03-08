CREATE TABLE t_motorcycle (
    name VARCHAR2(256),
    displacement_cc number,
    last_modified_at timestamp,
    CONSTRAINT t_motorcycle_pk PRIMARY KEY(name)
)
