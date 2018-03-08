CREATE TABLE t_motorcycle (
    name VARCHAR(256) NOT NULL,
    displacement_cc NUMERIC,
    last_modified_at TIMESTAMP,
    CONSTRAINT t_motorcycle_pk PRIMARY KEY(name)
)
