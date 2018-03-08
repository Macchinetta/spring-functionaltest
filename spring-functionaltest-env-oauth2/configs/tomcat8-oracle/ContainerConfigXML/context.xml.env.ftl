<?xml version='1.0' encoding='utf-8'?>
<!-- web application context file for tomcat. -->
<!-- this file should be place at CATALINA_HOME/conf/Catalina/localhost/spring-functionaltest-web.xml -->
<Context>

  <Resource name="jdbc/springFunctionaltestDataSource" type="javax.sql.DataSource"
    driverClassName="oracle.jdbc.OracleDriver" username="cfw" password="cfw"
    url="jdbc:oracle:thin:@${HOST_IP!'localhost'}:${DBSRV_DB_PORT!'1521'}/teradb" maxIdle="16"
    minIdle="0" maxWaitMillis="60000" maxTotal="96" />

  <Resources className="org.apache.catalina.webresources.StandardRoot">
    <PreResources className="org.apache.catalina.webresources.DirResourceSet"
      base="${OAUTH2_VM_TOMCAT_ENV_JAR_DIR!'/opt/tomcat/tomcat/webapps-env-jars/spring-functionaltest-env-oauth2-tomcat8-oracle'}/"
      internalPath="/" webAppMount="/WEB-INF/lib" />
  </Resources>
  <JarScanner scanAllDirectories="true" />

</Context>
