<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.rscl.resultUserInf" />
    <c:set var="functionId" value="rscl" />

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <meta name="contextPath" content="${pageContext.request.contextPath}" />
        <sec:csrfMetaTags />
        <title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
        <style>
            .grid {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 8px;
            }
            .cell {
                border: 1px solid #ccc;
                padding: 8px;
                background: #fafafa;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div class="container">
             <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

            <div id="wrapper">
                <h1 id="screenTitle">RESTクライアント処理結果【レスポンス情報出力】</h1>
                <t:messagesPanel />
                <c:if test="${!empty f:h(resultDescription)}">
                    <h4>
                        <span id="resultDescription"> 【&nbsp;${f:h(resultDescription)}&nbsp;】 </span>
                    </h4>
                </c:if>
                <div class="grid">
                    <div class="cell">HttpStatus:</div>
                    <div class="cell" id="httpStatus">${f:h(responseInfo.httpStatusCode)}</div>
                    <div class="cell">ResponseHeader:</div>
                    <div class="cell" id="responseHeader">${f:h(responseInfo.responseHeader)}</div>
                    <div class="cell">ResponseBody:</div>
                    <div class="cell" id="responseBody">${f:h(responseInfo.responseBody)}</div>
                </div>
            </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
