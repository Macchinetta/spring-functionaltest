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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/bluetrip.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <h1 id="screenTitle">messagesPanelタグの属性変更</h1>
        <div class="container">
            <div id="panelElement">
                <h1>panelElementの変更</h1>
                <t:messagesPanel messagesAttributeName="messages_panelElement" panelElement="span"/>
            </div>
            <div id="panelClassName_panelTypeClassPrefix">
                <h1>panelClassName,panelTypeClassPrefixの変更</h1>
                <t:messagesPanel messagesAttributeName="messages_classs" panelClassName="" panelTypeClassPrefix=""/>
            </div>
            <div id="outerElement_innerElement">
                <h1>outerElement,innerElementの変更</h1>
                <t:messagesPanel messagesAttributeName="messages_outer_inner_element" outerElement="" innerElement="span"/>
            </div>
            <spring:message var="informationMessage" code="i.ex.od.0001" />
            <div id="disableHtmlEscape_true">
                <h1>disableHtmlEscape=true</h1>
                <t:messagesPanel messagesAttributeName="informationMessage" messagesType="info" disableHtmlEscape="true" />
            </div>
            <div id="disableHtmlEscape_false">
                <h1>disableHtmlEscape=false(default)</h1>
                <t:messagesPanel messagesAttributeName="informationMessage" messagesType="info" />
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
