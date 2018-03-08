CREATE TABLE resource_ids (
    client_id VARCHAR(50) NOT NULL,
    resource_id VARCHAR(50) NOT NULL,
    CONSTRAINT fk_resource_ids FOREIGN KEY (client_id) REFERENCES clients(client_id)
)
