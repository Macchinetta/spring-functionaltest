DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user (
    username VARCHAR(30) NOT NULL
    ,password VARCHAR(110) NOT NULL
    ,enabled BOOLEAN NOT NULL
    ,authority VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_user PRIMARY KEY (username)
);
