<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.stpr.paddingInput" />
<c:set var="functionId" value="stpr" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
﻿<div id="wrapper">

    <h1 id="screenTitle">[入力]指定したフォーマットでパディングされた文字列を取得できることを確認する</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/stpr/0201/001/padding"
        cssClass="form-horizontal" method="post"
        modelAttribute="stringProcessing0201001Form">
        <div class="form-group">
            <form:label path="targetValueInt"
                class="col col-md-2 control-label">パディング対象数値</form:label>
            <div class="col col-md-3">
                <form:input path="targetValueInt"
                    cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="targetValueInt" class="text-danger" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="format"
                class="col col-md-2 control-label">パディングフォーマット</form:label>
            <div class="col col-md-3">
                <form:input path="format"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="padding" class="btn btn-default">パディング</form:button>
        </div>
    </form:form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>