<?xml version='1.0' encoding='utf-8'?>
<!-- web application context file for tomcat. -->
<!-- this file should be place at CATALINA_HOME/conf/Catalina/localhost/spring-functionaltest-web.xml -->
<Context>

    <Resource
       name="jdbc/springFunctionaltestDataSource" 
       type="javax.sql.DataSource"
       driverClassName="oracle.jdbc.OracleDriver"
       username="cfw"
       password="cfw"
       url="jdbc:oracle:thin:@${HOST_IP!'localhost'}:${DBSRV_DB_PORT!'1521'}/teradb"
       maxIdle="16"
       minIdle="0"
       maxWait="60000"
       maxActive="96"/>

    <Loader className="org.apache.catalina.loader.VirtualWebappLoader"
        virtualClasspath="${OAUTH2_VM_TOMCAT_ENV_JAR_DIR!'/opt/tomcat/tomcat/webapps-env-jars/spring-functionaltest-env-oauth2-tomcat-oracle'}/*.jar" />
    <JarScanner scanAllDirectories="true" />

</Context>
