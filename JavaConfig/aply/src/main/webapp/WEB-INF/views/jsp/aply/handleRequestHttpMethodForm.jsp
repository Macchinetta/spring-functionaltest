<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.aply.handleRequestHttpMethodForm" />
    <c:set var="functionId" value="aply" />

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
                <h1 id="screenTitle">Controllerの実装方法(HTTPメソッドの設定)</h1>

                <form action="${pageContext.request.contextPath}/jsp/0101/001_1" method="get" class="form-horizontal">
                    <spring:nestedPath path="handleRequestForm">
                        <input type="submit" id="aply0101001_1" class="btn btn-default" value="HTTPメソッドをGETのみ指定（GET送信）" />
                    </spring:nestedPath>
                </form>
                <form:form action="${pageContext.request.contextPath}/jsp/0101/001_2" method="post" cssClass="form-horizontal" modelAttribute="handleRequestForm">
                    <form:button id="aply0101001_2" name="aply0101001_2" class="btn btn-default">HTTPメソッドをPOSTのみ指定（POST送信）</form:button>
                </form:form>
                <form action="${pageContext.request.contextPath}/jsp/0101/001_3" method="get" class="form-horizontal">
                    <spring:nestedPath path="handleRequestForm">
                        <input type="submit" id="aply0101001_3get" class="btn btn-default" value="HTTPメソッドをPOSTとGETのみ指定（GET送信）" />
                    </spring:nestedPath>
                </form>
                <form:form action="${pageContext.request.contextPath}/jsp/0101/001_3" method="post" cssClass="form-horizontal" modelAttribute="handleRequestForm">
                    <form:button id="aply0101001_3post" name="aply0101001_3post" class="btn btn-default">HTTPメソッドをPOSTとGETのみ指定（POST送信）</form:button>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
