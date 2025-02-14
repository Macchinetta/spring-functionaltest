<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.flup.screenFlowFileUploadConfirm" />
    <c:set var="functionId" value="flup" />

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
                <h1 id="screenTitle">ファイルアップロード(確認)</h1>

                <form:form modelAttribute="screenFlowUploadForm" method="post" cssClass="form-horizontal">
                    <div class="form-group">
                        <label for="temporaryFileIdText" class="col col-md-2 control-label">ファイルID</label>
                        <div class="col col-md-10">
                            <span id="temporaryFileIdText" class="display-item form-control">${f:h(screenFlowUploadSessionInfo.temporaryFileId)}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="fileNameText" class="col col-md-2 control-label">ファイル名</label>
                        <div class="col col-md-10">
                            <span id="fileNameText" class="display-item form-control">${f:h(screenFlowUploadForm.fileName)}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="descriptionText" class="col col-md-2 control-label">説明</label>
                        <div class="col col-md-10">
                            <span id="descriptionText" class="display-item form-control">${f:br(f:h(screenFlowUploadForm.description))}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col col-md-10 col-md-offset-2">
                            <form:hidden path="fileName" />
                            <form:hidden path="description" />
                            <form:button id="uploadButton" name="upload" class="btn btn-default"> <span class="glyphicon glyphicon-upload"></span>アップロード </form:button>
                            <form:button id="confirmButton" name="redo" class="btn btn-default"> <span class="glyphicon glyphicon-arrow-left"></span>戻る</form:button>
                        </div>
                    </div>

                    <form:hidden path="contentType" />
                </form:form>

                <c:if test="${!(empty displayConfirmTemporaryDirectoryButton)}">
                    <form action="${pageContext.request.contextPath}/flup/temporaryFiles?list" method="get" class="form-horizontal">
                        <div class="form-group">
                            <div class="col col-md-10 col-md-offset-2">
                                <button id="confirmTemporaryDirectory" name="list" class="btn btn-default"><span class="glyphicon glyphicon-folder-open"></span>一時ディレクトリの確認</button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
