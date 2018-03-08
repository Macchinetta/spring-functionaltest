CREATE TABLE web_server_redirect_uris (
    client_id VARCHAR(50) NOT NULL,
    web_server_redirect_uri VARCHAR(256) NOT NULL,
    CONSTRAINT fk_web_server_redirect_uris FOREIGN KEY (client_id) REFERENCES clients(client_id)
)
