<div id="wrapper">
  <h1 id="screenTitle">型変換</h1>
  <br>

  <form:form action="${pageContext.request.contextPath}/dtop/getdatetype" cssClass="form-horizontal"
    method="get">
    <fieldset>
      <legend>日時生成時型変換</legend>
      <div class="form-group">
        <button id="getDateToDateTimeObject" name="getDateToDateTimeObject" class="btn btn-default">Date
          To DateTime</button>
        <button id="getDateTimeToDateObject" name="getDateTimeToDateObject" class="btn btn-default">DateTime
          To Date</button>
      </div>
    </fieldset>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dtop/getdatetype" cssClass="form-horizontal"
    method="get" modelAttribute="changeTypeForm">
    <legend>日付文字列をパース</legend>
    <div class="form-group">
      <div class="col col-md-2">
        <label for="name">日付文字列</label>
      </div>
      <div class="col col-md-2">
        <form:input path="targetDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="getParseDate" class="btn btn-default">パースして取得</form:button>
    </div>
  </form:form>
</div>