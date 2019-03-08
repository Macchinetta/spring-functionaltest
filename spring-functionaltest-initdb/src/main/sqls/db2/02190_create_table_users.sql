CREATE TABLE users(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(88) NOT NULL,
    enabled NUMERIC(1) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY(username)
)
