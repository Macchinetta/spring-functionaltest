<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.xspr.outputEscaping" />
    <c:set var="functionId" value="msmn" />

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
        <h1 id="screenTitle">ResultMessageの表示（複数）</h1>
        <div class="container">
            <h1>Result Message</h1>
            <h2>Messages(SUCCESS)</h2>
            <t:messagesPanel messagesAttributeName="messages_success" />
            <h2>Messages(INFO)</h2>
            <t:messagesPanel messagesAttributeName="messages_info" />
            <h2>Messages(WARNING)</h2>
            <t:messagesPanel messagesAttributeName="messages_warning" />
            <h2>Messages(ERROR)</h2>
            <t:messagesPanel messagesAttributeName="messages_error" />
            <h2>Messages(DANGER)</h2>
            <t:messagesPanel messagesAttributeName="messages_danger" />
            <h2>Messages(PRIMARY)</h2>
            <t:messagesPanel messagesAttributeName="messages_primary" />
            <h2>Messages(SECONDARY)</h2>
            <t:messagesPanel messagesAttributeName="messages_secondary" />
            <h2>Messages(LIGHT)</h2>
            <t:messagesPanel messagesAttributeName="messages_light" />
            <h2>Messages(DARK)</h2>
            <t:messagesPanel messagesAttributeName="messages_dark" />
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
