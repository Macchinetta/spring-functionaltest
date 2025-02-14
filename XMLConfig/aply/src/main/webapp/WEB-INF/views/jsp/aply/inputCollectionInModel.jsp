<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.aply.inputCollectionInModel" />
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
                <h1 id="screenTitle">モデルに格納されたコレクション型の値を表示できることの確認</h1>
                <br />
                <form:form action="${pageContext.request.contextPath}/jsp/0304/collectionInModel" cssClass="form-horizontal" method="post" modelAttribute="jspFormListForm">
                    <legend>コレクション型の値を表示できること</legend>
                    <div class="form-group">
                        <c:forEach var="jspFormList" items="${jspFormListForm.jspFormList}" varStatus="rowStatus">
                            <spring:nestedPath path="jspFormList[${rowStatus.index}]">
                                <div class="form-group">
                                    <div class="col col-md-2">氏名&nbsp;${rowStatus.count}(姓、名)</div>
                                    <div class="col col-md-3">
                                        <form:input path="firstName" cssClass="form-control input-sm" />
                                    </div>
                                    <div class="col col-md-3">
                                        <form:input path="lastName" cssClass="form-control input-sm" />
                                    </div>
                                </div>
                            </spring:nestedPath>
                        </c:forEach>
                    </div>
                    <br />
                    <div class="form-group">
                        <form:button name="collectionInModel" class="btn btn-default">コレクション型の値を画面表示</form:button>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
