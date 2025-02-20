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
oth2.resource.server.artifactId=spring-functionaltest-web-oauth2-resource
oth2.resource.server.base-uri=${OAUTH2_PROTOCOL!'http'}://${HOST_IP!'localhost'}:${OAUTH2_PORT!'8080'}/spring-functionaltest-web-oauth2-resource
oth2.resource.connection.timeout=10000
oth2.resource.read.timeout=10000
oth2.send.wait.seconds=180

# OAuth2 Authorization Server
oth2.authorization.server.url=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/auth
oth2.authorization.server.wrongurl=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.authorization.token.url=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/token
oth2.authorization.token.wrongurl=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.jwk.set.uri=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/certs
oth2.jwk.set.wronguri=${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.redirect.url={baseUrl}/jsp/auth/authorized
oth2.client.secret=secret