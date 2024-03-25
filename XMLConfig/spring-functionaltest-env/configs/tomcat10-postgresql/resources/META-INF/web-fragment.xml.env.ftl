<?xml version="1.0" encoding="UTF-8"?>
<web-fragment xmlns="https://jakarta.ee/xml/ns/jakartaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:webfragment="http://jakarta.ee/xml/ns/javaee/web-fragment_6_0.xsd"
  xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-fragment_6_0.xsd"
  version="6.0">

  <listener>
    <listener-class>org.h2.server.web.JakartaDbStarter</listener-class>
  </listener>
  <context-param>
    <param-name>db.url</param-name>
    <param-value>jdbc:h2:mem:spring-functionaltest;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:logback-ddl.sql'</param-value>
  </context-param>
  <context-param>
    <param-name>db.user</param-name>
    <param-value>sa</param-value>
  </context-param>
  <context-param>
    <param-name>db.password</param-name>
    <param-value></param-value>
  </context-param>
  <context-param>
    <param-name>db.tcpServer</param-name>
    <param-value>-tcpAllowOthers -tcpPort 9202</param-value>
  </context-param>

</web-fragment>
