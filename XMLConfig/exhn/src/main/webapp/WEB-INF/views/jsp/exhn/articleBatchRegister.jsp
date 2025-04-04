<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.exhn.articleBatchRegister" />
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
                <h3 id="screenTitle">記事登録画面</h3>

                <div id="count">登録件数:${f:h(count)}</div>
                <br />
                <form:form
                    action="${pageContext.request.contextPath}/jsp/${f:h(testNumber)}/confirm"
                    modelAttribute="articleBatchRegisterForm"
                    method="POST"
                    cssClass="form-horizontal"
                    enctype="multipart/form-data"
                >
                    <fieldset>
                        <legend>記事ファイル</legend>
                        <div class="form-group">
                            <form:label path="title" cssClass="col col-md-2 control-label">タイトル</form:label>
                            <div class="col col-md-5">
                                <form:input type="text" cssClass="form-control" path="title" />
                                <form:errors id="titleError" cssClass="text-danger" path="title" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="multipartFile" cssClass="col col-md-2 control-label">ファイル</form:label>
                            <div class="col col-md-5">
                                <form:input type="file" path="multipartFile" />
                                <form:errors id="multipartFileError" cssClass="text-danger" path="multipartFile" />
                            </div>
                        </div>
                    </fieldset>
                    <div class="form-group">
                        <div class="col col-md-10 col-md-offset-2">
                            <form:button id="uploadButton" name="upload" class="btn btn-default">アップロード</form:button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
