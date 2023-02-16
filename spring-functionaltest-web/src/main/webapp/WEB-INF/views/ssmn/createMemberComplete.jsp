<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ssmn.createMemberComplete" />
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
  <fieldset>
    <legend>個人情報登録完了</legend>
    <t:messagesPanel />

    <table class="table table-striped table-bordered table-condensed">
      <tr>
        <th>会員ID</th>
        <td><span id="memberId">${f:h(member.memberId)}</span></td>
      </tr>
      <tr>
        <th>氏名（姓）</th>
        <td><span id="firstName">${f:h(member.firstName)}</span></td>
      </tr>
      <tr>
        <th>氏名（名）</th>
        <td><span id="lastName">${f:h(member.lastName)}</span></td>
      </tr>
      <tr>
        <th>氏名カナ（姓）</th>
        <td><span id="firstNameKana">${f:h(member.firstNameKana)}</span></td>
      </tr>
      <tr>
        <th>氏名カナ（名）</th>
        <td><span id="lastNameKana">${f:h(member.lastNameKana)}</span></td>
      </tr>
      <tr>
        <th>年齢</th>
        <td><span id="age">${f:h(member.age)}</span></td>
      </tr>
      <tr>
        <th>性別</th>
        <td><span id="gender">${f:h(CL_GENDER[member.gender])}</span></td>
      </tr>
      <tr>
        <th>郵便番号</th>
        <td><span id="zipCode">${f:h(member.zipCode)}</span></td>
      </tr>
      <tr>
        <th>都道府県</th>
        <td><span id="state">${f:h(member.state)}</span></td>
      </tr>
      <tr>
        <th>市区町村</th>
        <td><span id="city">${f:h(member.city)}</span></td>
      </tr>
      <tr>
        <th>番地建物</th>
        <td><span id="address">${f:h(member.address)}</span></td>
      </tr>
      <tr>
        <th>職業</th>
        <td><span id="occupation">${f:h(member.occupation)}</span></td>
      </tr>
      <tr>
        <th>メールアドレス</th>
        <td><span id="mailAddress">${f:h(member.mailAddress)}</span></td>
      </tr>
    </table>
    <div class="row">
      <a id="menu" href="${pageContext.request.contextPath}/ssmn" class="btn btn-default">戻る</a>
    </div>
  </fieldset>
  <!-- セッション内Form確認 -->
  <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['memberForm'].getClass().getName())}</div>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>