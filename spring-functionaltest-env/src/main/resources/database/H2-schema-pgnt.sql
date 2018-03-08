CREATE TABLE t_celebrity (
    celebrity_id integer NOT NULL
    ,first_name VARCHAR(30) NOT NULL
    ,last_name VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_t_celebrity PRIMARY KEY (celebrity_id)
);

CREATE TABLE t_article (
    article_id integer NOT NULL
    ,category VARCHAR(50) NOT NULL
    ,title VARCHAR(100) NOT NULL
    ,overview VARCHAR(100) NOT NULL
    ,publish_date timestamp NOT NULL
    ,CONSTRAINT pk_t_article PRIMARY KEY (article_id)
);
