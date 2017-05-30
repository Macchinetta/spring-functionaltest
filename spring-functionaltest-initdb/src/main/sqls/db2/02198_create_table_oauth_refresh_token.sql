CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) NOT NULL,
  token VARCHAR(2000) FOR BIT DATA,
  authentication BLOB(2000),
  CONSTRAINT pk_oauth_refresh_token PRIMARY KEY(token_id)
)
