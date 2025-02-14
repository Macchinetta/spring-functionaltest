<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.ajax.messageBoard" />
    <c:set var="functionId" value="ajax" />

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
        <jsp:include page="../layout/header.jsp" />
        <div class="container">
            <div id="wrapper">
                <div id="xMLHttpRequestResult" class="hidden alert">
                    <ul>
                        <li id="returnStatus"></li>
                        <li id="contentType"></li>
                    </ul>
                </div>
                <div id="resultMessage" class="hidden alert"></div>

                <div class="text-center">
                    <form id="messageBoardForm">
                        <spring:nestedPath path="messageBoardForm">
                            <fieldset>
                                <legend>Spring 掲示板</legend>

                                <form:label path="comment" cssClass="space">コメント</form:label>
                                <form:input path="comment" cssClass="space write-comment-field form-control inline" />
                                <form:button id="writeBtn" class="btn btn-default" onclick="return ajax.write('${f:hjs(path)}')">書き込み</form:button>
                            </fieldset>
                        </spring:nestedPath>
                    </form>
                </div>
                <br />

                <div class="text-center">ご自由にコメントしてください!!</div>
                <div id="result">
                    <c:forEach var="messages" items="${messageResults}" varStatus="rowStatus">
                        <ul>
                            <li>${f:h(messages.comment)}</li>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp" />
    </body>
</html>
