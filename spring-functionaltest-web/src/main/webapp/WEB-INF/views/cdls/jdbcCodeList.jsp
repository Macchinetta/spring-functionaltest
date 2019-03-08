<div id="wrapper">

  <h1 id="screenTitle">JdbcCodeListの読み込み</h1>

  <div>CDLS0103001</div>
  <div>
    <form:select id="cdls0103001" items="${CL_AUTHORITIES}" path="codeListForm.code" />
  </div>
  <div>CDLS0103002</div>
  <div>
    <form:form modelAttribute="clAuthoritiesForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0103/002">
      <form:select id="cdls0103002" items="${CL_AUTHORITIES}" path="id" />
      <form:errors path="id" />
      <button id="cdls0103002Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0103002Result">${f:h(authoritiesCodeListValue)}</div>
  </div>
</div>
