DROP TABLE IF EXISTS t_committer;

CREATE TABLE t_committer (
    username VARCHAR(30) NOT NULL
    ,password VARCHAR(110) NOT NULL
    ,email VARCHAR(50) NOT NULL
    ,url VARCHAR(200)
    ,company VARCHAR(100)
    ,location VARCHAR(500)
    ,CONSTRAINT pk_t_committer PRIMARY KEY (username)
);

