DROP TABLE IF EXISTS t_jms_todo;

CREATE TABLE t_jms_todo (
    jms_todo_id VARCHAR(255) NOT NULL
    ,description VARCHAR(255) NOT NULL
    ,date_time TIMESTAMP
    ,CONSTRAINT pk_t_jms_todo PRIMARY KEY (jms_todo_id)
);
