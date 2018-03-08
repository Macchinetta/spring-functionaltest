# JDBC Driver settings
database=POSTGRESQL

# connection pool
cp.maxActive=96
cp.maxIdle=16
cp.minIdle=0
cp.maxWait=60000

# OAuth2 setting
oth2.server.protocol=${OAUTH2_PROTOCOL!'http'}
oth2.server.host=${HOST_IP!'localhost'}
oth2.server.port=${OAUTH2_PORT!'8080'}
oth2.resourceServer.id=testClient
oth2.resourceServer.secret=demo
oth2.resourceServer.secret.illegal=illegal_secret
