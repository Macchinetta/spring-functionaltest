<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dnta.showCompareResult" />
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
                <p>
                    Now is earlier than Past:<span id="NowIsBeforePast">${f:h(NowIsBeforePast)}</span><br />
                    Now is earlier than Now:<span id="NowIsBeforeNow">${f:h(NowIsBeforeNow)}</span><br />
                    Now is earlier than Future:<span id="NowIsBeforeFuture">${f:h(NowIsBeforeFuture)}</span><br />
                    Now is later than past:<span id="NowIsAfterPast">${f:h(NowIsAfterPast)}</span><br />
                    Now is later than Now:<span id="NowIsAfterNow">${f:h(NowIsAfterNow)}</span><br />
                    Now is later than Future:<span id="NowIsAfterFuture">${f:h(NowIsAfterFuture)}</span><br />
                    Now is equal to past:<span id="NowEqualsPast">${f:h(NowEqualsPast)}</span><br />
                    Now is equal to Now:<span id="NowEqualsNow">${f:h(NowEqualsNow)}</span><br />
                    Now is equal to Now:<span id="NowEqualsFuture">${f:h(NowEqualsFuture)}</span><br />
                </p>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
