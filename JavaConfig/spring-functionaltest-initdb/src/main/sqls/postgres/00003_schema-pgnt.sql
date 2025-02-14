DROP TABLE IF EXISTS t_celebrity;
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_person;

CREATE TABLE t_celebrity (
    celebrity_id integer NOT NULL
    ,first_name varchar(30) NOT NULL
    ,last_name varchar(30) NOT NULL
    ,CONSTRAINT pk_t_celebrity PRIMARY KEY (celebrity_id)
);

CREATE TABLE t_article (
    article_id int NOT NULL
    ,category varchar(50) NOT NULL
    ,title varchar(100) NOT NULL
    ,overview varchar(100) NOT NULL
    ,publish_date timestamp NOT NULL
    ,CONSTRAINT pk_t_article PRIMARY KEY (article_id)
);

CREATE TABLE t_person (
    person_id int NOT NULL,
    firstname varchar(25) NOT NULL,
    lastname varchar(25)  NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (person_id)
);