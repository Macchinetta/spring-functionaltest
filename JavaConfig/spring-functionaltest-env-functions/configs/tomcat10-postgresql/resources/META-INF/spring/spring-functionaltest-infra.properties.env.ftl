# JDBC Driver settings
database=POSTGRESQL
dialect=jp.co.ntt.fw.spring.functionaltest.infra.hibernate.dialect.ExtendedPostgreSQLDialect

# connection pool
cp.maxActive=96
cp.maxIdle=16
cp.minIdle=0
cp.maxWait=60000

# connection setting
host.ip=${HOST_IP!'localhost'}
host.http.port=${HTTP_PORT!'8080'}
host.https.port=${HTTPS_PORT!'80'}

# File Upload setting
app.upload.temporaryDirectory=/tmp/spring-functionaltest-web/upload/temporaryDirectory/
app.upload.directory=/tmp/spring-functionaltest-web/upload/uploadDirectory/

# enabled column value
enabledValue=true

# Email settings (No-auth)
mail.noauth.smtp.host=${MAIL_HOST!'localhost'}
mail.noauth.smtp.port=${POSTFIX_NOAUTH_SMTP_PORT!'7025'}
mail.noauth.pop3.host=${MAIL_HOST!'localhost'}
mail.noauth.pop3.port=${DOVECOT_NOAUTH_POP3_PORT!'7110'}
mail.noauth.smtp.port.invalid=7024

# Email settings (With-auth)
mail.auth.smtp.host=${MAIL_HOST!'localhost'}
mail.auth.smtp.port=${POSTFIX_AUTH_SMTP_PORT!'7025'}
mail.auth.smtp.user=info@auth-example.com
mail.auth.smtp.password=Ntt01
mail.auth.pop3.host=${MAIL_HOST!'localhost'}
mail.auth.pop3.port=${DOVECOT_AUTH_POP3_PORT!'7110'}
mail.auth.smtp.password.invalid=password

# Email settings ('From' account)
mail.from.user=info
mail.from.password=Ntt01
mail.noauth.from.address=info@noauth-example.com
mail.auth.from.address=info@auth-example.com

# SOAP&RSCL connect settings
connect.host=localhost
connect.port=8080

# SOAP timeout settings
## WebService sleep time
soap.timeout.sleepMilliseconds=3000
## WebService Proxy timeout settings
soap.request.timeout.key=jakarta.xml.ws.client.receiveTimeout
soap.request.timeout.value=1000
soap.connect.timeout.key=jakarta.xml.ws.client.connectionTimeout
soap.connect.timeout.value=2000

# REST API Capacity (RestClient)
rscl.asyncRestTemplate.corePoolSize=3
rscl.asyncRestTemplate.queueCapacity=3
rscl.asyncRestTemplate.maxPoolSize=5

# JMS settings
jms.mq.host=${MQ_HOST!'localhost'}
jms.mq.port=${MQ_PORT!'61616'}
jms.mq.minLargeMessageSize=250000
jms.mq.priority=1
jms.mq.deliveryMode=2

# 0:NONE / 1:CONNECTION / 2:SESSION / 3:CONSUMER / 4:AUTO
jms.listener.chace.level=3

jmss.JndiDestinationResolver.resourceRef=true
app.jms.temporaryDirectory=/tmp/spring-functionaltest-web/jms/temporaryDirectory/
app.jms.receiveWaitTime=${JMS_RECEIVE_WAIT_TIME!'4000'}
app.jms.addReceiveWaitTime=${JMS_ADD_RECEIVE_WAIT_TIME!'1000'}
app.jms.receiveCheckInterval=200

jndi.prefix=java:comp/env/

# OAuth2 setting
oth2.server.protocol=${OAUTH2_PROTOCOL!'http'}
oth2.server.host=${HOST_IP!'localhost'}
oth2.server.port=${OAUTH2_PORT!'8080'}
oth2.resource.server.artifactId=spring-functionaltest-web-oauth2-resource
oth2.resource.server.base-uri=${OAUTH2_PROTOCOL!'http'}://${HOST_IP!'localhost'}:${OAUTH2_PORT!'8080'}/${OAUTH2_ARTIFACTID!'spring-functionaltest-web-oauth2-resource'}
oth2.resource.connection.timeout=10000
oth2.resource.read.timeout=10000

# OAuth2 Authorization Server
oth2.authorization.server.url=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/auth
oth2.authorization.server.wrongurl=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.authorization.token.url=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/token
oth2.authorization.token.wrongurl=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.jwk.set.uri=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/certs
oth2.jwk.set.wronguri=http://localhost:8900/realms/spring-functionaltest/protocol/openid-connect/wrong
oth2.redirect.url={baseUrl}/auth/authorized
oth2.client.secret=secret

# Settings specific test cases to run for various environments
test.environment=${MAIL_SERVER_PROFILE!'nonMailServer'}
jms.test.environment=${MQ_SERVER_PROFILE!'nonMQServer'}
test.environment.view=${TEST_ENV_VIEW!'jsp'}

# base web
app.base.web.protocol=http
app.base.web.host=${HOST_IP!'localhost'}
app.base.web.port=${HTTP_PORT!'8080'}
app.base.web.name=spring-functionaltest-web
app.base.web.path=http://${HOST_IP!'localhost'}:${HTTP_PORT!'8080'}/spring-functionaltest-web

