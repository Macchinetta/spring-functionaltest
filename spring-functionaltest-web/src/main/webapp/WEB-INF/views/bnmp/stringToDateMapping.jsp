<div id="wrapper">
  <h1 id="screenTitle">文字列型と日付・時刻型フィールドコピー設定</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/stringToDateMapping"
    cssClass="form-horizontal" method="post" modelAttribute="stringToDateMappingForm">
    <legend>文字列型から日付・時刻型へのコピー設定方法確認</legend>
    <div class="form-group">
      <div class="col col-md-4">Birth Date(yyyy-MM-dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date SqlDate(yyyy-MM-dd)</div>
      <div class="col col-md-3">
        <form:input path="birthDateSqlDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date SqlTime(HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateSqlTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date Calendar(yyyy-MM-dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateCalendar" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date GregorianCalendar(yyyy-MM-dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateGregorianCalendar" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date Timestamp(yyyy-MM-dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateTimestamp" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date LocalDate(uuuu-MM-dd)</div>
      <div class="col col-md-3">
        <form:input path="birthDateLocalDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date LocalTime(HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateLocalTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date LocalDateTime(uuuu-MM-dd HH:mm:ss.SSS)</div>
      <div class="col col-md-3">
        <form:input path="birthDateLocalDateTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date OffsetTime(HH:mm:ss.SSSZZZZZ)</div>
      <div class="col col-md-3">
        <form:input path="birthDateOffsetTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date OffsetDateTime(uuuu-MM-dd HH:mm:ss.SSSZZZZZ)</div>
      <div class="col col-md-3">
        <form:input path="birthDateOffsetDateTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-4">Birth Date ZonedDateTime(uuuu-MM-dd HH:mm:ss.SSSZZZZZ[VV])</div>
      <div class="col col-md-3">
        <form:input path="birthDateZonedDateTime" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="stringToDate" class="btn btn-default">文字列-&gt日時型設定</form:button>
    </div>
  </form:form>
</div>