# JDBC Driver settings
database=H2
database.url=jdbc:h2:mem:spring-functionaltest;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=2000
database.username=sa
database.password=
database.driverClassName=org.h2.Driver
database.url.open=jdbc:h2:mem:spring-functionaltest-open;DB_CLOSE_DELAY=-1
database.url.close=jdbc:h2:mem:spring-functionaltest-close;DB_CLOSE_DELAY=-1
dialect=jp.co.ntt.fw.spring.functionaltest.infra.hibernate.dialect.ExtendedH2Dialect

# connection pool
cp.maxActive=96
cp.maxIdle=16
cp.minIdle=0
cp.maxWait=60000

# connection setting
host.ip=${HOST_IP!'localhost'}
host.http.port=${HTTP_PORT!'8080'}
host.https.port=${HTTPS_PORT!'80'}
