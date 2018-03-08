<div id="wrapper">
  <h1 id="screenTitle">日付取得</h1>
  <br>
  <form action="${pageContext.request.contextPath}/dnta/getdate" class="form-horizontal"
    method="get">
    <fieldset>
      <legend>現在日付取得</legend>
      <div class="form-group">
        <button id="getNowLocalDate" name="getNowLocalDate" class="btn btn-default">LocalDate取得</button>
        <button id="getNowLocalTime" name="getNowLocalTime" class="btn btn-default">LocalTime取得</button>
        <button id="getNowLocalDateTime" name="getNowLocalDateTime" class="btn btn-default">LocalDateTime取得</button>
      </div>
    </fieldset>
  </form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dnta/getdate" cssClass="form-horizontal"
    method="get" modelAttribute="dateForm">
    <legend>指定日付取得</legend>
    <div class="form-group">
      <table>
        <tr>
          <td>year</td>
          <td><form:input path="year" cssClass="form-control input-sm" /></td>
        </tr>
        <tr>
          <td>month</td>
          <td><form:input path="month" cssClass="form-control input-sm" /></td>
        </tr>
        <tr>
          <td>day</td>
          <td><form:input path="day" cssClass="form-control input-sm" /></td>
        </tr>
        <tr>
          <td>hour</td>
          <td><form:input path="hour" cssClass="form-control input-sm" /></td>
        </tr>
        <tr>
          <td>minute</td>
          <td><form:input path="minute" cssClass="form-control input-sm" /></td>
        </tr>
        <tr>
          <td>second</td>
          <td><form:input path="second" cssClass="form-control input-sm" /></td>
        </tr>
      </table>
    </div>
    <div class="form-group">
      <form:button id="getSpecifiedDate" name="getSpecifiedDate" class="btn btn-default">LocalDate取得</form:button>
      <form:button id="getSpecifiedTime" name="getSpecifiedTime" class="btn btn-default">LocalTime取得</form:button>
      <form:button id="getSpecifiedDateTime" name="getSpecifiedDateTime" class="btn btn-default">LocalDateTime取得</form:button>
      <form:button id="getLastDateOfSpecifiedMonth" name="getLastDateOfSpecifiedMonth"
        class="btn btn-default">指定した日付のある月の最終日取得</form:button>
      <form:button id="getNextMondayOfSpecifiedDate" name="getNextMondayOfSpecifiedDate"
        class="btn btn-default">指定した日付の次の月曜日取得</form:button>
    </div>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dnta/getdate" cssClass="form-horizontal"
    method="get" modelAttribute="dateForm">
    <legend>TimeZone付き日付取得</legend>
    <div class="form-group">
      <div class="col col-md-1">Timezone</div>
      <div class="col col-md-3">
        <form:input path="zone" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="getOffsetTime" class="btn btn-default">OffsetTime取得</form:button>
      <form:button name="getOffsetDateTime" class="btn btn-default">OffsetDateTime取得</form:button>
      <form:button name="getZonedDateTime" class="btn btn-default">ZonedDateTime取得</form:button>
      <form:button name="getDateTimeSpecifiedTimezoneByArea" class="btn btn-default">TimeZoneを指定して取得(地域名)</form:button>
      <form:button name="getDateTimeSpecifiedTimezoneByTimeDifference" class="btn btn-default">TimeZoneを指定して取得(時差)</form:button>
    </div>
  </form:form>
</div>