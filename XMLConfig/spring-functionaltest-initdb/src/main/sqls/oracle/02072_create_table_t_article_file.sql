CREATE
    TABLE t_article_file(
        file_id VARCHAR(36) NOT NULL
        ,title VARCHAR(50) NOT NULL
        ,content BLOB
        ,CONSTRAINT pk_t_article_file PRIMARY KEY (file_id)
    )
