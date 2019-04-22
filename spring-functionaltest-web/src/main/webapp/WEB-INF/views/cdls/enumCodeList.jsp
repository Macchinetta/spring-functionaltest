<div id="wrapper">

  <h1 id="screenTitle">EnumCodeListの読み込み</h1>

  <div>CDLS0104001</div>
  <div>
    <form:select id="cdls0104001" items="${CL_ENUM_ORDERSTATUS}" path="codeListForm.code" />
  </div>
  <div>CDLS0104002</div>
  <div>
    <form:form modelAttribute="clEnumOrderStatusForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0104/002">
      <form:select id="cdls0104002" items="${CL_ENUM_ORDERSTATUS}" path="id" />
      <form:errors path="id" />
      <button id="cdls0104002Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0104002Result">${f:h(enumOrderStatusCodeListValue)}</div>
  </div>
</div>
