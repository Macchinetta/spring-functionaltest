CREATE TABLE scopes (
    client_id VARCHAR(50) NOT NULL,
    scope VARCHAR(50) NOT NULL,
    CONSTRAINT fk_scopes FOREIGN KEY (client_id) REFERENCES clients(client_id)
)
