CREATE TABLE authorized_grant_types (
    client_id VARCHAR(50) NOT NULL,
    authorized_grant_type VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorized_grant_types FOREIGN KEY (client_id) REFERENCES clients(client_id)
)
