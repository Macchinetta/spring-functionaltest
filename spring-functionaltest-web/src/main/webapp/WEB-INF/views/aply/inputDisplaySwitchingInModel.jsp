<div id="wrapper">
  <h1 id="screenTitle">モデルに格納された値により条件分岐して表示切替ができることの確認</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/aply/0303/displaySwitching"
    cssClass="form-horizontal" method="post" modelAttribute="jspForm">
    <legend>条件分岐して表示切替ができること</legend>
    <div class="form-group">
      <div class="col col-md-2">条件</div>
      <div class="col col-md-3">
        <form:input path="conditions" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="cIfFormat" class="btn btn-default">c:if形式</form:button>
      <form:button name="cChooseFormat" class="btn btn-default">c:choose形式</form:button>
    </div>
  </form:form>
</div>