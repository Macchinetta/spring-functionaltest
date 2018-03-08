CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256),
  token RAW(2000),
  authentication BLOB,
  CONSTRAINT pk_oauth_refresh_token PRIMARY KEY(token_id)
)
