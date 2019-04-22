DROP TABLE IF EXISTS t_upload_file;

CREATE TABLE t_upload_file (
    file_id CHAR(36) 
    ,file_name TEXT NOT NULL
    ,content_type VARCHAR(64) NOT NULL
    ,content BYTEA
    ,description TEXT
    ,uploaded_at TIMESTAMP NOT NULL
    ,CONSTRAINT pk_t_upload_file PRIMARY KEY (file_id)
);