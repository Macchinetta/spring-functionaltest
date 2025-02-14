<div>
  <hr>
  <p align="right">
    JVM version : <span id="jvmVersion"><%=System.getProperty("java.version")%></span>
  </p>
  <p align="right">
    AP Server : <span id="apServerName"><%=application.getServerInfo()%></span>
  </p>
  <p align="right">
    Template Engine : <span id="templateEngineName">JSP</span>
  </p>
  <p id="copyright">
    Copyright &copy; 20XX CompanyName (X-Track:<span id="xTrack">${f:h(requestScope["X-Track"])}</span>)
  </p>
</div>