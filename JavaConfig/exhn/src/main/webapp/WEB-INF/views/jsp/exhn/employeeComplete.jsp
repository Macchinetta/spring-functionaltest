<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.exhn.employeeComplete" />
    <c:set var="functionId" value="exhn" />

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
                <h3 id="screenTitle">社員情報編集完了画面</h3>

                <form:form action="${pageContext.request.contextPath}/jsp/${f:h(testNumber)}" cssClass="form-horizontal" method="get" modelAttribute="employeeForm">
                    <fieldset>
                        <legend>社員情報</legend>
                        <div class="form-group">
                            <div class="col col-md-2">
                                <label>社員ID</label>
                            </div>
                            <div class="col col-md-4">${f:h(employeeForm.employeeId)}</div>
                        </div>
                        <div class="form-group">
                            <div class="col col-md-2">
                                <label>名前</label>
                            </div>
                            <div class="col col-md-4">${f:h(employeeForm.employeeName)}</div>
                        </div>
                        <div class="form-group">
                            <div class="col col-md-2">
                                <label>Email</label>
                            </div>
                            <div class="col col-md-4">${f:h(employeeForm.email)}</div>
                        </div>
                        <div class="form-group">
                            <div class="col col-md-2">
                                <label>住所</label>
                            </div>
                            <div class="col col-md-4">${f:h(employeeForm.address)}</div>
                        </div>
                        <div class="form-group col col-md-5">
                            <form:button id="backToIndex" class="btn btn-default">戻る</form:button>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
