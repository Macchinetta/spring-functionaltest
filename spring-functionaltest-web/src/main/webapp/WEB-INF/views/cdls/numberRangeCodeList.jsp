<div id="wrapper">

  <h1 id="screenTitle">NumberRangeCodeListの読み込み</h1>

  <div>CDLS0102001</div>
  <div>
    <form:select id="cdls0102001" items="${CL_MONTH_ASC}" path="codeListForm.code" />
  </div>
  <div>CDLS0102002</div>
  <div>
    <form:form modelAttribute="clMonthAscForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0102/002">
      <form:select id="cdls0102002" items="${CL_MONTH_ASC}" path="id" />
      <form:errors path="id" />
      <button id="cdls0102002Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0102002Result">${f:h(monthAscCodeListValue)}</div>
  </div>
</div>
