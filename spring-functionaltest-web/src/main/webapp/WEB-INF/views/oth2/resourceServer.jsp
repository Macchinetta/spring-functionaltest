<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.oth2.resourceServer" />
<c:set var="functionId" value="oth2" />

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
  <div>Login to Keycloak using: demo/demo</div>
  <br/>
  <ul class="list-group">
    <li class="list-group-item">
      <div>InterceptUrl</div>
      <br/>
      <!-- すべてのスコープを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_ALL</div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=CREATE&amp;pattern=intercept">CREATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=DELETE&amp;pattern=intercept">DELETE</a>
        </li>
      </ul>

      <!-- READ及びDELETEのみスコープに設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_PARTIAL - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=CREATE&amp;pattern=intercept">CREATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=DELETE&amp;pattern=intercept">DELETE</a>
        </li>
      </ul>
      
      <!-- READスコープのみを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a>
        </li>
      </ul>
      
      <!-- 認可サーバに登録されていないクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - WRONG_SCOPE - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_wrong&amp;scope=GET&amp;pattern=intercept">GET</a>
        </li>
      </ul>
    </li>
  </ul>
  <ul class="list-group">
    <li class="list-group-item">
      <div>PreAuthorize</div>
      <br/>
      <!-- すべてのスコープを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_ALL</div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=CREATE&amp;pattern=annotation">CREATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=DELETE&amp;pattern=annotation">DELETE</a>
        </li>
      </ul>

      <!-- READ及びDELETEのみスコープに設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_PARTIAL - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=CREATE&amp;pattern=annotation">CREATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=DELETE&amp;pattern=annotation">DELETE</a>
        </li>
      </ul>
      
      <!-- READスコープのみを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a>
        </li>
      </ul>
      
      <!-- 認可サーバに登録されていないクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - WRONG_SCOPE - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/oth2/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_wrong&amp;scope=GET&amp;pattern=annotation">GET</a>
        </li>
      </ul>
    </li>
  </ul>

  <p>=======================================================================</p>
  <div>
    <c:if test="${not empty message}">
      <h2>RESULT : ${message}</h2>
      
      <h2>Access Token</h2>
      <p>Access Token Issued : ${accessTokenIssued}</p>
      <p>Access Token Expires : ${accessTokenExpires}</p>
      <p>Access Token Scopes : ${accessTokenScopes}</p>
      <p>Access Token Type : ${accessTokenType}</p>
      <p>Access Token Value : ${accessTokeValue}</p>
      
      <h2>Refresh Token</h2>
      <p>Refresh Token Issued : ${refreshTokenIssued}</p>
      <p>Refresh Token Expires : ${refreshTokenExpires}</p>
      <p>Refresh Token Value : ${refreshTokeValue}</p>
    </c:if>
  </div>
  <p>=======================================================================</p>

</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>