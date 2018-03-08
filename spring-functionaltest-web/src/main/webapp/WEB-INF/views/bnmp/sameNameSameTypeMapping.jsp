<div id="wrapper">
  <h1 id="screenTitle">フィールドが同名同型でマッピング</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/sameNameSameTypeMapping"
    cssClass="form-horizontal" method="post" modelAttribute="beanMapperForm">
    <legend>同名同型フィールドのコピーができること</legend>
    <div class="form-group">
      <div class="col col-md-2">First Name</div>
      <div class="col col-md-3">
        <form:input path="firstName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Last Name</div>
      <div class="col col-md-3">
        <form:input path="lastName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">age</div>
      <div class="col col-md-3">
        <form:input path="age" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="copySameNameSameTypeBean" class="btn btn-default">同名同型フィールドのコピー</form:button>
    </div>
  </form:form>
</div>