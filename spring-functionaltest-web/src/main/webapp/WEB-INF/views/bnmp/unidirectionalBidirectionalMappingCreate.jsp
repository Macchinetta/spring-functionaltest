<div id="wrapper">
  <h1 id="screenTitle">単方向・双方向でマッピング</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/unidirectionalBidirectionalMapping"
    cssClass="form-horizontal" method="post" modelAttribute="carForm">
    <legend>車情報登録</legend>
    <div class="form-group">
      <div class="col col-md-2">Car Name</div>
      <div class="col col-md-3">
        <form:input path="carName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Car Color</div>
      <div class="col col-md-3">
        <form:input path="carColor" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">release Date</div>
      <div class="col col-md-3">
        <form:input path="carReleaseDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="copyUnidirectionalBean" class="btn btn-default">登録(単方向Bean)</form:button>
      <form:button name="copyBidirectionalBean" class="btn btn-default">登録(双方向Bean)</form:button>
    </div>
  </form:form>
</div>