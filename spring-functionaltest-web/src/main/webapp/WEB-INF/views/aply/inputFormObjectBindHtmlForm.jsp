<div id="wrapper">
  <h1 id="screenTitle">フォームオブジェクトからHTMLフォームへバインドできることの確認</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/aply/0305/bindFormObject"
    cssClass="form-horizontal" method="post" modelAttribute="jspForm">
    <legend>フォームオブジェクトからHTMLフォームへバインドできること</legend>
    <div class="form-group">
      <div class="col col-md-2">氏名</div>
      <div class="col col-md-3">
        <form:input path="firstName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">名前</div>
      <div class="col col-md-3">
        <form:input path="lastName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="bindFormObject" class="btn btn-default">Formオブジェクトバインド</form:button>
    </div>
  </form:form>
</div>