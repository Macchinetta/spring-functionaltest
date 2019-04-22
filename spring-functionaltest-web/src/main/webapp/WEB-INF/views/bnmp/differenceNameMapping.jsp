<div id="wrapper">
  <h1 id="screenTitle">フィールドが異名でマッピング</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/differenceNameMapping"
    cssClass="form-horizontal" method="post" modelAttribute="beanMapperForm">
    <legend>異名フィールドでもコピーができること</legend>
    <div class="form-group">
      <div class="col col-md-2">First Name -&gt; Myoji</div>
      <div class="col col-md-3">
        <form:input path="firstName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Last Name -&gt; Namae</div>
      <div class="col col-md-3">
        <form:input path="lastName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Age</div>
      <div class="col col-md-3">
        <form:input path="age" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="copyDifferenceNameField" class="btn btn-default">異名フィールドのコピー</form:button>
    </div>
  </form:form>
</div>