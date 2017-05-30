CREATE TABLE oauth_refresh_token (
  code VARCHAR(256) NOT NULL,
  authentication BLOB(2000),
  CONSTRAINT pk_oauth_code PRIMARY KEY(code)
)
