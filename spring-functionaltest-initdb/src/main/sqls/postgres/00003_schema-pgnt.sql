DROP TABLE IF EXISTS t_celebrity;
DROP TABLE IF EXISTS t_article;

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
