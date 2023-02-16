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
﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">

	<h1 id="screenTitle">RESTクライアント処理結果【ユーザ情報出力】</h1>
	<t:messagesPanel />
	<c:if test="${!empty f:h(resultDescription)}">
		<h4>
			<span id="resultDescription"> 【&nbsp;${f:h(resultDescription)}&nbsp;】 </span>
		</h4>
	</c:if>
	<table
		class="table table-striped table-bordered table-condensed pgnttable">
		<thead>
			<tr>
				<th>Name</th>
				<th>Age</th>
			</tr>
		</thead>
		<tr>
			<td><span id="userName">${f:h(user.name)}</span></td>
			<td><span id="userAge">${f:h(user.age)}</span></td>
		</tr>
	</table>
	<c:if test="${!empty f:h(compleatDateTime)}">
		<h4>
			完了日時 : <span id="compleatDateTime"><javatime:format pattern="uuuu-MM-dd HH:mm:ss.SSSSSSSSS" value="${compleatDateTime}" /></span>
		</h4>
	</c:if>
	<c:if test="${!empty f:h(executeTime)}">
		<h4>
			処理時間 : <span id="executeTime">${f:h(executeTime)}</span> [ms]
		</h4>
	</c:if>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>