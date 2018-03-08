CREATE TABLE oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(50),
  client_id VARCHAR(50),
  authentication BLOB,
  refresh_token VARCHAR(256),
  CONSTRAINT pk_oauth_access_token PRIMARY KEY(authentication_id)
)
