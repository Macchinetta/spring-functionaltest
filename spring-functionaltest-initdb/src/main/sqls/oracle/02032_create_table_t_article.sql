CREATE
    TABLE t_article(
        article_id NUMBER(10) NOT NULL
        ,category VARCHAR(50) NOT NULL
        ,title VARCHAR(100) NOT NULL
        ,overview VARCHAR(100) NOT NULL
        ,publish_date TIMESTAMP NOT NULL
        ,CONSTRAINT pk_t_article PRIMARY KEY (article_id)
    )
