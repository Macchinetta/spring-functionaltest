<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.jpaIndex" />
<c:set var="functionId" value="djpa" />

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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
    <div id="wrapper">

      <h1 id="screenTitle">JPA Implementation Confirmation</h1>
      <div>JPA Confirmation : EntityManager and Transaction settings</div>
      <ul>
        <li>djpa0101001 (<a id="djpa0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0102001 (<a id="djpa0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0102002 (<a id="djpa0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / Thymeleaf)</li>
        <li>djpa0103001 (<a id="djpa0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0103002 (<a id="djpa0103002_jsp" href="${pageContext.request.contextPath}/jsp/0103/002">JSP</a> / Thymeleaf)</li>
        <li>djpa0103003 (<a id="djpa0103003_jsp" href="${pageContext.request.contextPath}/jsp/0103/003">JSP</a> / Thymeleaf)</li>
        <li>djpa0103004 (<a id="djpa0103004_jsp" href="${pageContext.request.contextPath}/jsp/0103/004">JSP</a> / Thymeleaf)</li>
        <li>djpa0103005 (<a id="djpa0103005_jsp" href="${pageContext.request.contextPath}/jsp/0103/005">JSP</a> / Thymeleaf)</li>
      </ul>
      <hr>
      <div>JPA Confirmation : Interface Implementation</div>
      <ul>
        <li>djpa0201001 (<a id="djpa0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0201002 (<a id="djpa0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / Thymeleaf)</li>
        <li>djpa0201003 (<a id="djpa0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / Thymeleaf)</li>
        <li>djpa0201004 (<a id="djpa0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> / Thymeleaf)</li>
        <li>djpa0201005 (<a id="djpa0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> / Thymeleaf)</li>
        <li>djpa0201006 (<a id="djpa0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> / Thymeleaf)</li>
        <li>djpa0201007 (<a id="djpa0201007_jsp" href="${pageContext.request.contextPath}/jsp/0201/007">JSP</a> / Thymeleaf)</li>
        <li>djpa0201008 (<a id="djpa0201008_jsp" href="${pageContext.request.contextPath}/jsp/0201/008">JSP</a> / Thymeleaf)</li>
        <li>djpa0201009 (<a id="djpa0201009_jsp" href="${pageContext.request.contextPath}/jsp/0201/009">JSP</a> / Thymeleaf)</li>
        <li>djpa0201010 (<a id="djpa0201010_jsp" href="${pageContext.request.contextPath}/jsp/0201/010">JSP</a> / Thymeleaf)</li>
        <li>djpa0201011 (<a id="djpa0201011_jsp" href="${pageContext.request.contextPath}/jsp/0201/011">JSP</a> / Thymeleaf)</li>
        <li>djpa0201012 (<a id="djpa0201012_jsp" href="${pageContext.request.contextPath}/jsp/0201/012">JSP</a> / Thymeleaf)</li>
        <li>djpa0201013 (<a id="djpa0201013_jsp" href="${pageContext.request.contextPath}/jsp/0201/013">JSP</a> / Thymeleaf)</li>
        <li>djpa0201014 (<a id="djpa0201014_jsp" href="${pageContext.request.contextPath}/jsp/0201/014">JSP</a> / Thymeleaf)</li>
        <li>djpa0201015 (<a id="djpa0201015_jsp" href="${pageContext.request.contextPath}/jsp/0201/015">JSP</a> / Thymeleaf)</li>
        <li>djpa0201016 (<a id="djpa0201016_jsp" href="${pageContext.request.contextPath}/jsp/0201/016">JSP</a> / Thymeleaf)</li>
        <hr>
        <li>djpa02020001 (<a id="djpa0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / Thymeleaf)</li>
        <li>djpa02020002 (<a id="djpa0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / Thymeleaf)</li>
      </ul>
      <div>Implementation ways to query the database</div>
      <ul>
        <li>djpa0301001 (<a id="djpa0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0301002 (<a id="djpa0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> / Thymeleaf)</li>
        <li>djpa0302001 (<a id="djpa0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0303001 (<a id="djpa0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0304001 (<a id="djpa0304001_jsp" href="${pageContext.request.contextPath}/jsp/0304/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0304002 (<a id="djpa0304002_jsp" href="${pageContext.request.contextPath}/jsp/0304/002">JSP</a> / Thymeleaf)</li>
      </ul>
      <div>Implementing the process to search entities</div>
      <ul>
        <li>djpa0401001 (<a id="djpa0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0402001 (<a id="djpa0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Search conditions based on passing dynamic conditions of entities</div>
      <ul>
        <li>djpa0501001 (<a id="djpa0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0502001 (<a id="djpa0502001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>The Process of fetching entities</div>
      <ul>
        <li>djpa0601001 (<a id="djpa0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0601003 (<a id="djpa0601003_jsp" href="${pageContext.request.contextPath}/jsp/0601/003">JSP</a> / Thymeleaf)</li>
        <li>djpa0601004 (<a id="djpa0601004_jsp" href="${pageContext.request.contextPath}/jsp/0601/004">JSP</a> / Thymeleaf)</li>
        <li>djpa0602001 (<a id="djpa0602001_jsp" href="${pageContext.request.contextPath}/jsp/0602/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Adding entities to database</div>
      <ul>
        <li>djpa0701001 (<a id="djpa0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0702001 (<a id="djpa0702001_jsp" href="${pageContext.request.contextPath}/jsp/0702/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0703001 (<a id="djpa0703001_jsp" href="${pageContext.request.contextPath}/jsp/0703/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0704001 (<a id="djpa0704001_jsp" href="${pageContext.request.contextPath}/jsp/0704/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Updating entities to database</div>
      <ul>
        <li>djpa0801001 (<a id="djpa0801001_jsp" href="${pageContext.request.contextPath}/jsp/0801/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0802001 (<a id="djpa0802001_jsp" href="${pageContext.request.contextPath}/jsp/0802/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0803001 (<a id="djpa0803001_jsp" href="${pageContext.request.contextPath}/jsp/0803/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0804001 (<a id="djpa0804001_jsp" href="${pageContext.request.contextPath}/jsp/0804/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0804002 (<a id="djpa0804002_jsp" href="${pageContext.request.contextPath}/jsp/0804/002">JSP</a> / Thymeleaf)</li>
        <li>djpa0804003 (<a id="djpa0804003_jsp" href="${pageContext.request.contextPath}/jsp/0804/003">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Deleting entities from database</div>
      <ul>
        <li>djpa0901001 (<a id="djpa0901001_jsp" href="${pageContext.request.contextPath}/jsp/0901/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0902001 (<a id="djpa0902001_jsp" href="${pageContext.request.contextPath}/jsp/0902/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0903001 (<a id="djpa0903001_jsp" href="${pageContext.request.contextPath}/jsp/0903/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0804001 (<a id="djpa0904001_jsp" href="${pageContext.request.contextPath}/jsp/0904/001">JSP</a> / Thymeleaf)</li>
        <li>djpa0905001 (<a id="djpa0905001_jsp" href="${pageContext.request.contextPath}/jsp/0905/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Escaping at the time of LIKE search</div>
      <ul>
        <li>djpa1001001 (<a id="djpa1001001_jsp" href="${pageContext.request.contextPath}/jsp/1001/001">JSP</a> / Thymeleaf)</li>
        <li>djpa1001002 (<a id="djpa1001002_jsp" href="${pageContext.request.contextPath}/jsp/1001/002">JSP</a> / Thymeleaf)</li>
        <li>djpa1002001 (<a id="djpa1002001_jsp" href="${pageContext.request.contextPath}/jsp/1002/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Join Fetch</div>
      <ul>
        <li>djpa1101001 (<a id="djpa1101001_jsp" href="${pageContext.request.contextPath}/jsp/1101/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Add custom method</div>
      <ul>
        <li>djpa1201001 (<a id="djpa1201001_jsp" href="${pageContext.request.contextPath}/jsp/1201/001">JSP</a> / Thymeleaf)</li>
        <li>djpa1202001 (<a id="djpa1202001_jsp" href="${pageContext.request.contextPath}/jsp/1202/001">JSP</a> / Thymeleaf)</li>
      </ul>


      <div>Storing query fetch results in objects other than entity</div>
      <ul>
        <li>djpa1301001 (<a id="djpa1301001_jsp" href="${pageContext.request.contextPath}/jsp/1301/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Setting Audit properties</div>
      <ul>
        <li>djpa1401001 (<a id="djpa1401001_jsp" href="${pageContext.request.contextPath}/jsp/1401/001">JSP</a> / Thymeleaf)</li>
        <li>djpa1402001 (<a id="djpa1402001_jsp" href="${pageContext.request.contextPath}/jsp/1402/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>Specify Conditions in JPQL</div>
      <ul>
        <li>djpa1501001 (<a id="djpa1501001_jsp" href="${pageContext.request.contextPath}/jsp/1501/001">JSP</a> / Thymeleaf)</li>
        <li>djpa1502001 (<a id="djpa1502001_jsp" href="${pageContext.request.contextPath}/jsp/1502/001">JSP</a> / Thymeleaf)</li>
      </ul>
      
      <br>
          
      <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

    </div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>