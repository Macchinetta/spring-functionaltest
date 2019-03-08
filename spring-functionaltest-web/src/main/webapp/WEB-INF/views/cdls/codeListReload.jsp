<div id="wrapper">

  <h1 id="screenTitle">コードリストのリロード</h1>

  <div>CDLS0201001</div>
  <form:form modelAttribute="updateCodeListForm"
    action="${pageContext.request.contextPath}/cdls/0201/001">
    <form:select id="cdls0201001" path="id" items="${CL_CRON_REFRESH_CODELIST}" />
    <form:errors path="id" />
    <p>
      Value:
      <form:input id="cronValue" path="value" />
      <form:errors path="value" />
    </p>
    <button id="updateCdls0201001" type="submit" name="update">cron table update</button>
  </form:form>

  <div>CDLS0201002</div>
  <form:form modelAttribute="updateCodeListForm"
    action="${pageContext.request.contextPath}/cdls/0201/002">
    <form:select id="cdls0201002" path="id" items="${refreshCodeList}" />
    <form:errors path="id" />
    <p>
      Value:
      <form:input id="refreshValue" path="value" />
      <form:errors path="value" />
    </p>
    <button id="updateCdls0201002" type="submit" name="update">refresh table update</button>
  </form:form>
  <a id="codeListRefreshEndPoint" href="${pageContext.request.contextPath}/cdls/0201/002?refresh">コードリストリフレッシュエンドポイント</a>
</div>
