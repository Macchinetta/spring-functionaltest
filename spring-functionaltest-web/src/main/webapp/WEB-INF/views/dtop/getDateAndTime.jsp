<div id="wrapper">
    <h1 id="screenTitle">日付取得</h1>
    <br />
    <form action="${pageContext.request.contextPath}/dtop/getdate" class="form-horizontal" method="get">
        <fieldset>
            <legend>現在日付取得</legend>
            <div class="form-group">
                <button id="getNowDateTime" name="getNowDateTime" class="btn btn-default">DateTime取得</button>
                <button id="getNowLocalDate" name="getNowLocalDate" class="btn btn-default">LocalDate取得</button>
                <button id="getNowLocalTime" name="getNowLocalTime" class="btn btn-default">LocalTime取得</button>
                <button id="getNowDateMidnight" name="getNowDateMidnight" class="btn btn-default">DateMidnight取得</button>
                <button id="getWithTimeAtStartOfDay" name="getWithTimeAtStartOfDay" class="btn btn-default">TimeAtStartOfDay取得</button>
                <button id="getPartOfDateTime" name="getPartOfDateTime" class="btn btn-default">個別取得</button>
            </div>
        </fieldset>
    </form>
    <br />
    <form:form action="${pageContext.request.contextPath}/dtop/getdate" cssClass="form-horizontal" method="get" modelAttribute="getDateAndTimeForm">
        <legend>現在日付をTimeZoneを指定して取得</legend>
        <div class="form-group">
            <div class="col col-md-1">
                <form:label path="targetTimeZone">Timezone</form:label>
            </div>
            <div class="col col-md-3">
                <form:input path="targetTimeZone" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button name="getDateTimeSpecifiedTimezone" class="btn btn-default">TimeZone指定して取得</form:button>
        </div>
    </form:form>
</div>
