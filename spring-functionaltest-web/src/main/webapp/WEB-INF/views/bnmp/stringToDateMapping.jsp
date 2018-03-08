<div id="wrapper">
  <h1 id="screenTitle">文字列型と日付・時刻型フィールドコピー設定</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/stringToDateMapping"
    cssClass="form-horizontal" method="post" modelAttribute="dateMappingForm">
    <legend>文字列型と日付・時刻型間でコピー設定方法確認</legend>
    <div class="form-group">
      <div class="col col-md-4">birthDate(yyyy/MM/dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="stringToDate" class="btn btn-default">文字列-&gt日時型設定</form:button>
    </div>
  </form:form>
</div>