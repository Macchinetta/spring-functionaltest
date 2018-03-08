CREATE TABLE oauth_approvals (
  userId VARCHAR(50),
  clientId VARCHAR(50),
  scope VARCHAR(50),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP,
  CONSTRAINT pk_oauth_approvals PRIMARY KEY(userId, clientId, scope)
)
