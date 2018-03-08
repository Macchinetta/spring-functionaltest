<div id="wrapper">
  <h1 id="screenTitle">疑似乱数(鍵)生成(StringKeyGenerator)</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/encr/0203/001/generateStringKey"
    cssClass="form-horizontal" method="post" modelAttribute="encryptionDataForm">
    <legend>疑似乱数(鍵)</legend>
    <div class="form-group">
      <form:button id="generate" class="btn btn-default">疑似乱数(鍵)生成</form:button>
    </div>
  </form:form>
</div>