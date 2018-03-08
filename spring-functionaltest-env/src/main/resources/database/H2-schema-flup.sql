CREATE TABLE t_upload_file (
    file_id CHAR(36)
    ,file_name TEXT NOT NULL
    ,content_type VARCHAR(64) NOT NULL
    ,content BLOB
    ,description TEXT
    ,uploaded_at TIMESTAMP NOT NULL
    ,CONSTRAINT pk_t_upload_file PRIMARY KEY (file_id)
);