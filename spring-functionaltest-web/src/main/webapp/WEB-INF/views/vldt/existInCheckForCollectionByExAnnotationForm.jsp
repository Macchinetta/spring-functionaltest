<div id="wrapper">

  <h1 id="screenTitle">コレクション内の値に対する入力チェック(拡張アノテーション利用）</h1>

  <form:form action="${pageContext.request.contextPath}/vldt/0503/001" method="post"
    modelAttribute="roleForExAnnotationForm">
    <label for="roles">Roles</label>
    <br />
    <form:checkboxes items="${CL_ROLE}" path="roles" />
    <br />
    <form:errors path="roles*" />
    <br />
    <input id="validate" class="mainbtn" style="width: 200px;" type="submit" value="Validate"
      name="Validate" />
  </form:form>

</div>