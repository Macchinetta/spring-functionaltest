CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY(username, authority),
    CONSTRAINT fk_authorities FOREIGN KEY (username) REFERENCES users(username)
)
