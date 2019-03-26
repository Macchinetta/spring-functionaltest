<div id="wrapper">
  <h1 id="screenTitle">文字列型と日付・時刻型フィールドコピー設定</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/dateToStringMappingFailed"
    cssClass="form-horizontal" method="post" modelAttribute="dateToStringMappingForm">
    <legend>日付・時刻型から文字列型へのコピー時、マッピング定義XMLファイルでSimpleDateFormatに定義されていない書式は使用できないこと</legend>
    <div class="form-group">
      <div class="col col-md-4">Birth Date ZonedDateTime(uuuu/MM/dd HH:mm:ss.SSSZZZZZ[VV])</div>
      <div class="col col-md-3">
        <form:input path="birthDateZonedDateTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="dateToStringFailed" class="btn btn-default">日時型-&gt文字列設定</form:button>
    </div>
  </form:form>
</div>