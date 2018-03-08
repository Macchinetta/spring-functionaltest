CREATE TABLE oauth_approvals (
  userId VARCHAR(50) NOT NULL,
  clientId VARCHAR(50) NOT NULL,
  scope VARCHAR(50) NOT NULL,
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP,
  CONSTRAINT pk_oauth_approvals PRIMARY KEY(userId, clientId, scope)
)
