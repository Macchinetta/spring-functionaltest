<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dnta.changeType" />
    <c:set var="functionId" value="dnta" />

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <meta name="contextPath" content="${pageContext.request.contextPath}" />
        <sec:csrfMetaTags />
        <title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div class="container">
            <div id="wrapper">
                <h1 id="screenTitle">型変換</h1>
                <br />

                <form:form action="${pageContext.request.contextPath}/jsp/changeType" cssClass="form-horizontal" method="get" modelAttribute="dateForm">
                    <fieldset>
                        <legend>Date and Time API内の型変換</legend>
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
                                <tr>
                                    <td>zone</td>
                                    <td><form:input path="zone" cssClass="form-control input-sm" /></td>
                                </tr>
                            </table>
                        </div>
                        <div class="form-group">
                            <button id="getLocalTimeToLocalDateTime" name="getLocalTimeToLocalDateTime" class="btn btn-default">LocalTime to LocalDateTime</button>
                            <button id="getLocalDateToLocalDateTime" name="getLocalDateToLocalDateTime" class="btn btn-default">LocalDate to LocalDateTime</button>
                            <button id="getLocalDateTimeToLocalTime" name="getLocalDateTimeToLocalTime" class="btn btn-default">LocalDateTime to LocalTime</button>
                            <button id="getLocalDateTimeToLocalDate" name="getLocalDateTimeToLocalDate" class="btn btn-default">LocalDateTime to LocalDate</button>
                            <button id="getOffseTimeToOffsetDateTime" name="getOffseTimeToOffsetDateTime" class="btn btn-default">OffsetTime to OffsetDateTime</button>
                            <button id="getOffsetDateTimeToZonedDateTime" name="getOffsetDateTimeToZonedDateTime" class="btn btn-default">OffsetDateTime to ZonedDateTime</button>
                            <button id="getZonedDateTimeToOffsetDateTime" name="getZonedDateTimeToOffsetDateTime" class="btn btn-default">ZonedDateTime to OffsetDateTime</button>
                            <button id="getZonedDateTimeToOffsetTime" name="getZonedDateTimeToOffsetTime" class="btn btn-default">ZonedDateTime to OffsetTime</button>
                            <button id="getLocalTimeToOffsetTime" name="getLocalTimeToOffsetTime" class="btn btn-default">LocalTime to OffsetTime</button>
                        </div>
                    </fieldset>
                </form:form>
                <br />
                <form action="${pageContext.request.contextPath}/jsp/changeType" class="form-horizontal" method="get">
                    <fieldset>
                        <legend>Date and Time APIとjava.util.Dateの型変換</legend>
                        <div class="form-group">
                            <button id="getLocalDateTimeToUtilDate" name="getLocalDateTimeToUtilDate" class="btn btn-default">LocalDateTime to java.util.Date</button>
                            <button id="getUtilDateToLocalDateTime" name="getUtilDateToLocalDateTime" class="btn btn-default">java.util.Date to LocalDateTime</button>
                        </div>
                    </fieldset>
                </form>
                <br />
                <form action="${pageContext.request.contextPath}/jsp/changeType" class="form-horizontal" method="get">
                    <fieldset>
                        <legend>Date and Time APIとjava.sqlパッケージの型変換</legend>
                        <div class="form-group">
                            <button id="getLocalDateToSqlDate" name="getLocalDateToSqlDate" class="btn btn-default">LocalDate to java.sql.Date</button>
                            <button id="getSqlDateToLocalDate" name="getSqlDateToLocalDate" class="btn btn-default">java.sql.Date to LocalDate</button>
                            <button id="getLocalDateTimeToSqlTimestamp" name="getLocalDateTimeToSqlTimestamp" class="btn btn-default">LocalDateTime to java.sql.Timestamp</button>
                            <button id="getSqlTimestampToLocalDateTime" name="getSqlTimestampToLocalDateTime" class="btn btn-default">java.sql.Timestamp to LocalDateTime</button>
                            <button id="getLocalTimeToSqlTime" name="getLocalTimeToSqlTime" class="btn btn-default">LocalTime to java.sql.Time</button>
                            <button id="getSqlTimeToLocalTime" name="getSqlTimeToLocalTime" class="btn btn-default">java.sql.Time to LocalTime</button>
                        </div>
                    </fieldset>
                </form>
                <br />
                <form action="${pageContext.request.contextPath}/jsp/changeType" class="form-horizontal" method="get">
                    <fieldset>
                        <legend>DateFactoryからDate and Time APIを生成</legend>
                        <div class="form-group">
                            <button id="getDateFromDateFactory" name="getDateFromDateFactory" class="btn btn-default">生成</button>
                        </div>
                    </fieldset>
                </form>
                <br />
                <form action="${pageContext.request.contextPath}/jsp/changeType" class="form-horizontal" method="get">
                    <fieldset>
                        <legend>Date and Time APIから文字列にフォーマット</legend>
                        <div class="form-group">
                            <button id="getNormalFormat" name="getNormalFormat" class="btn btn-default">通常フォーマット</button>
                            <button id="getPredefinedFormat" name="getPredefinedFormat" class="btn btn-default">事前定義によるフォーマット</button>
                            <button id="getAnyFormat" name="getAnyFormat" class="btn btn-default">任意のフォーマット</button>
                        </div>
                    </fieldset>
                </form>
                <br />
                <form:form action="${pageContext.request.contextPath}/jsp/changeType" cssClass="form-horizontal" method="get" modelAttribute="changeTypeForm">
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
                        <form:button name="getParseDate" class="btn btn-default">パースして日付取得</form:button>
                        <form:button name="getParseTime" class="btn btn-default">パースして時間取得</form:button>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
