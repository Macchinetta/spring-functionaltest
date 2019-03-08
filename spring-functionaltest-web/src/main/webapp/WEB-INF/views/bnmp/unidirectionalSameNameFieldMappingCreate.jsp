<div id="wrapper">
  <h1 id="screenTitle">単方向でのマッピング（同名フィールドをfieldタグで定義）</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/unidirectionalSameNameFieldMapping"
    cssClass="form-horizontal" method="post" modelAttribute="carSameNameFieldOneWayMappingForm">
    <legend>車情報登録</legend>
    <div class="form-group">
      <div class="col col-md-2">Car Name</div>
      <div class="col col-md-3">
        <form:input path="name" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Car Color</div>
      <div class="col col-md-3">
        <form:input path="color" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">release Date</div>
      <div class="col col-md-3">
        <form:input path="releaseDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="copyUnidirectionalBean" class="btn btn-default">登録(単方向Bean)</form:button>
    </div>
  </form:form>
</div>