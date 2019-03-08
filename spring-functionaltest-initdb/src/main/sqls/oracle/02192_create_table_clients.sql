CREATE TABLE clients (
    client_id VARCHAR(50) NOT NULL,
    client_secret VARCHAR(88) NOT NULL,
    client_name VARCHAR(256) NOT NULL,
    access_token_validity NUMBER(5) NOT NULL,
    refresh_token_validity NUMBER(5),
    CONSTRAINT pk_clients PRIMARY KEY(client_id)
)
