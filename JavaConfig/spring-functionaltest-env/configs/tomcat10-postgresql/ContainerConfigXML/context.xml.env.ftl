<?xml version='1.0' encoding='utf-8'?>
<!-- web application context file for tomcat. -->
<!-- this file should be place at CATALINA_HOME/conf/Catalina/${HOST_IP!'localhost'}/spring-functionaltest-web.xml -->
<Context>

    <Resource name="jdbc/springFunctionaltestDataSource" type="javax.sql.DataSource"
        driverClassName="org.postgresql.Driver" username="postgres" password="P0stgres"
        url="jdbc:postgresql://${HOST_IP!'localhost'}:${DBSRV_DB_PORT!'5432'}/spring-functionaltest"
        maxIdle="16" minIdle="0" maxWaitMillis="60000" maxTotal="96" />

    <Resource name="jdbc/springFunctionaltestDataSourceOpen" type="javax.sql.DataSource"
        driverClassName="org.postgresql.Driver" username="postgres" password="P0stgres"
        url="jdbc:postgresql://${HOST_IP!'localhost'}:${DBSRV_DB_PORT!'5432'}/spring-functionaltest-open"
        maxIdle="16" minIdle="0" maxWaitMillis="60000" maxTotal="96" />

    <Resource name="jdbc/springFunctionaltestDataSourceClose" type="javax.sql.DataSource"
        driverClassName="org.postgresql.Driver" username="postgres" password="P0stgres"
        url="jdbc:postgresql://${HOST_IP!'localhost'}:${DBSRV_DB_PORT!'5432'}/spring-functionaltest-close"
        maxIdle="16" minIdle="0" maxWaitMillis="60000" maxTotal="96" />

    <Resources className="org.apache.catalina.webresources.StandardRoot">
        <PreResources className="org.apache.catalina.webresources.DirResourceSet"
            base="${VM_TOMCAT_ENV_JAR_DIR!'/opt/tomcat/tomcat/webapps-env-jars/spring-functionaltest-env-tomcat10-postgresql'}/"
            internalPath="/" webAppMount="/WEB-INF/lib" />
    </Resources>
    <JarScanner scanAllDirectories="true" />

    <Resource name="mail/session" auth="Container" type="jakarta.mail.Session"
        mail.smtp.host="${MAIL_HOST!'localhost'}" mail.smtp.port="${SMTP_PORT!'7025'}" mail.pop3.host="${MAIL_HOST!'localhost'}"
        mail.pop3.port="${POP3_PORT!'7110'}" />

    <Resource auth="Container" brokerName="ActiveMQBroker" brokerURL="tcp://${MQ_HOST!'localhost'}:${MQ_PORT!'61616'}"
        password="admin" userName="admin" description="JMS Connection Factory" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        name="jms/NormalConnectionFactory" type="org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory"
        useEmbeddedBroker="false" />
    <Resource name="jms/topic/TestTopic0301004" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQTopic" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestTopic0301004" />

    <Resource name="jms/queue/TestQueue0102001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0102001" />
    <Resource name="jms/queue/TestQueue0103001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0103001" />
    <Resource name="jms/queue/TestQueue0301001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0301001" />
    <Resource name="jms/queue/TestQueue0301003" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0301003" />
    <Resource name="jms/queue/TestQueue0302001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0302001" />
    <Resource name="jms/queue/TestQueue0303001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0303001" />
    <Resource name="jms/queue/TestQueue0303002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0303002" />
    <Resource name="jms/queue/TestQueue0401002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0401002" />
    <Resource name="jms/queue/TestQueue0401003" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0401003" />
    <Resource name="jms/queue/TestQueue0402001A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0402001A" />
    <Resource name="jms/queue/TestQueue0402001B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0402001B" />
    <Resource name="jms/queue/TestQueue0403001A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0403001A" />
    <Resource name="jms/queue/TestQueue0403001B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0403001B" />
    <Resource name="jms/queue/TestQueue0403001C" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0403001C" />
    <Resource name="jms/queue/TestQueue0403003A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0403003A" />
    <Resource name="jms/queue/TestQueue0403003B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0403003B" />
    <Resource name="jms/queue/TestQueue0404001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0404001" />
    <Resource name="jms/queue/TestQueue0404002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0404002" />
    <Resource name="jms/queue/TestQueue0501001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0501001" />
    <Resource name="jms/queue/TestQueue0601001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0601001" />
    <Resource name="jms/queue/TestQueue0601002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0601002" />
    <Resource name="jms/queue/TestQueue0602001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0602001" />
    <Resource name="jms/queue/TestQueue0602002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0602002" />
    <Resource name="jms/queue/TestQueue0603001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0603001" />
    <Resource name="jms/queue/TestQueue0603002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0603002" />
    <Resource name="jms/queue/TestQueue0604001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0604001" />
    <Resource name="jms/queue/TestQueue0604002" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0604002" />
    <Resource name="jms/queue/TestQueue0604005" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0604005" />
    <Resource name="jms/queue/TestQueue0604006" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0604006" />
    <Resource name="jms/queue/TestQueue0701001A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701001A" />
    <Resource name="jms/queue/TestQueue0701001B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701001B" />
    <Resource name="jms/queue/TestQueue0701002A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701002A" />
    <Resource name="jms/queue/TestQueue0701002B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701002B" />
    <Resource name="jms/queue/TestQueue0701003" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701003" />
    <Resource name="jms/queue/TestQueue0701004" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701004" />
    <Resource name="jms/queue/TestQueue0701005" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701005" />
    <Resource name="jms/queue/TestQueue0701006" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701006" />
    <Resource name="jms/queue/TestQueue0701007" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701007" />
    <Resource name="jms/queue/TestQueue0701008" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0701008" />
    <Resource name="jms/queue/TestQueue0802001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0802001" />
    <Resource name="jms/queue/TestQueue0803001" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0803001" />
    <Resource name="jms/queue/TestQueue0803002A" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0803002A" />
    <Resource name="jms/queue/TestQueue0803002B" auth="Container"
        type="org.apache.activemq.artemis.jms.client.ActiveMQQueue" factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
        address="TestQueue0803002B" />
</Context>