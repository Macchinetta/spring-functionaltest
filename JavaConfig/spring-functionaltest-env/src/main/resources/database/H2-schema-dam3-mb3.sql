CREATE TABLE m_item_mb3 (
    code CHAR(10),
    name VARCHAR(256),
    price INTEGER,
    CONSTRAINT m_item_mb3_pk PRIMARY KEY(code)
);

CREATE TABLE m_category_mb3 (
    code CHAR(10),
    name VARCHAR(256),
    CONSTRAINT m_category_mb3_pk PRIMARY KEY(code)
);

CREATE TABLE m_item_category_mb3 (
    item_code CHAR(10),
    category_code CHAR(10),
    CONSTRAINT m_item_category_mb3_pk PRIMARY KEY(item_code, category_code),
    CONSTRAINT m_item_category_mb3_fk1 FOREIGN KEY(item_code) REFERENCES m_item_mb3(code),
    CONSTRAINT m_item_category_mb3_fk2 FOREIGN KEY(category_code) REFERENCES m_category_mb3(code)
);


CREATE TABLE c_order_status_mb3 (
    code VARCHAR(10),
    name VARCHAR(256),
    CONSTRAINT c_order_status_mb3_pk PRIMARY KEY(code)
);

CREATE TABLE t_order_mb3 (
    id INTEGER,
    status_code VARCHAR(10),
    memo VARCHAR(100),
    history_id INTEGER,
    CONSTRAINT t_order_mb3_pk PRIMARY KEY(id),
    CONSTRAINT t_order_mb3_fk FOREIGN KEY(status_code) REFERENCES c_order_status_mb3(code)
);

CREATE TABLE t_order_item_mb3 (
    order_id INTEGER,
    item_code CHAR(10),
    quantity INTEGER,
    CONSTRAINT t_order_item_mb3_pk PRIMARY KEY(order_id, item_code),
    CONSTRAINT t_order_item_mb3_fk1 FOREIGN KEY(order_id) REFERENCES t_order_mb3(id),
    CONSTRAINT t_order_item_mb3_fk2 FOREIGN KEY(item_code) REFERENCES m_item_mb3(code)
);

CREATE TABLE t_order_history_mb3 (
    id IDENTITY (1, 1) ,
    order_id INTEGER,
    biko VARCHAR(100),
    upd_time timestamp,
    CONSTRAINT t_order_dummy_mb3 PRIMARY KEY(id)
);

CREATE TABLE t_motorcycle (
    name VARCHAR(256),
    displacement_cc INTEGER,
    CONSTRAINT t_motorcycle_pk PRIMARY KEY(name)
);
