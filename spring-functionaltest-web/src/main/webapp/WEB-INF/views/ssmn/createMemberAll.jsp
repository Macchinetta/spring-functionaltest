<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ssmn.createMemberAll" />
<c:set var="functionId" value="ssmn" />

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
<div id="wrapper">
  <h1 id="screenTitle">会員情報登録</h1>
  <br>
  <!-- actionを指定しないと遷移されたときのURLが使われる -->
  <form:form cssClass="form-horizontal" method="post" modelAttribute="memberForm">
    <fieldset>
      <legend>個人情報</legend>
      <div class="form-group">
        <div class="col col-md-2">氏名（姓）</div>
        <div class="col col-md-3">
          <form:input path="firstName" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="firstName" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">氏名（名）</div>
        <div class="col col-md-3">
          <form:input path="lastName" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="lastName" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">氏名カナ（姓）</div>
        <div class="col col-md-3">
          <form:input path="firstNameKana" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="firstNameKana" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">氏名カナ（名）</div>
        <div class="col col-md-3">
          <form:input path="lastNameKana" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="lastNameKana" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">年齢</div>
        <div class="col col-md-3">
          <form:input path="age" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="age" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">性別</div>
        <div class="col col-md-3">
          <form:select path="gender" items="${CL_GENDER}" class="form-control" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">郵便番号</div>
        <div class="col col-md-3">
          <form:input path="zipCode" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="zipCode" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">都道府県</div>
        <div class="col col-md-3">
          <form:input path="state" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="state" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">市区町村</div>
        <div class="col col-md-3">
          <form:input path="city" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="city" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">番地建物</div>
        <div class="col col-md-3">
          <form:input path="address" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="address" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">職業</div>
        <div class="col col-md-3">
          <form:input path="occupation" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="occupation" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">メールアドレス</div>
        <div class="col col-md-3">
          <form:input path="mailAddress" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="mailAddress" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <a id="menu" href="${pageContext.request.contextPath}/ssmn" class="btn btn-default">メニューに戻る</a>
        <form:button name="confirm" class="btn btn-default">確認</form:button>
      </div>
    </fieldset>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>