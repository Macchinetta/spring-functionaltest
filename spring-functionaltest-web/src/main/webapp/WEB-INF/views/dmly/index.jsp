<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dmly.index" />
<c:set var="functionId" value="dmly" />

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

  <h1 id="screenTitle">ドメイン層</h1>

  <div>[DMLY01] Entityの実装方法</div>
  <ul>
    <li><a id="dmly0101001" href="${pageContext.request.contextPath}/dmly/0101/001">dmly0101001</a></li>
    <li><a id="dmly0101002" href="${pageContext.request.contextPath}/dmly/0101/002">dmly0101002</a></li>
    <li><a id="dmly0101003" href="${pageContext.request.contextPath}/dmly/0101/003">dmly0101003</a></li>
  </ul>

  <div>[DMLY02] Repositoryの実装方法</div>
  <ul>
    <li><a id="dmly0201001" href="${pageContext.request.contextPath}/dmly/0201/001">dmly0201001</a></li>
    <li><a id="dmly0201002" href="${pageContext.request.contextPath}/dmly/0201/002">dmly0201002</a></li>
    <li><a id="dmly0201003" href="${pageContext.request.contextPath}/dmly/0201/003">dmly0201003</a></li>
    <li><a id="dmly0201004" href="${pageContext.request.contextPath}/dmly/0201/004">dmly0201004</a></li>
    <li><a id="dmly0201005" href="${pageContext.request.contextPath}/dmly/0201/005">dmly0201005</a></li>
    <li><a id="dmly0201006" href="${pageContext.request.contextPath}/dmly/0201/006">dmly0201006</a></li>
    <li><a id="dmly0201007" href="${pageContext.request.contextPath}/dmly/0201/007">dmly0201007</a></li>
    <li><a id="dmly0201008" href="${pageContext.request.contextPath}/dmly/0201/008">dmly0201008</a></li>
  </ul>

  <div>[DMLY03] Serviceの実装方法</div>
  <ul>
    <li><a id="dmly0301001" href="${pageContext.request.contextPath}/dmly/0301/001">dmly0301001</a></li>
    <li><a id="dmly0302001" href="${pageContext.request.contextPath}/dmly/0302/001">dmly0302001</a></li>
    <li><a id="dmly0303001" href="${pageContext.request.contextPath}/dmly/0303/001">dmly0303001</a></li>
  </ul>

  <div>[DMLY04] トランザクションの管理方法</div>
  <ul>
    <li><a id="dmly0401001" href="${pageContext.request.contextPath}/dmly/0401/001">dmly0401001</a></li>
    <li><a id="dmly0401002" href="${pageContext.request.contextPath}/dmly/0401/002">dmly0401002</a></li>
    <li><a id="dmly0401003" href="${pageContext.request.contextPath}/dmly/0401/003">dmly0401003</a></li>
    <li><a id="dmly0401004" href="${pageContext.request.contextPath}/dmly/0401/004">dmly0401004</a></li>
    <li><a id="dmly0401005" href="${pageContext.request.contextPath}/dmly/0401/005">dmly0401005</a></li>
    <li><a id="dmly0401006" href="${pageContext.request.contextPath}/dmly/0401/006">dmly0401006</a></li>
    <li><a id="dmly0401007" href="${pageContext.request.contextPath}/dmly/0401/007">dmly0401007</a></li>
    <li><a id="dmly0401008" href="${pageContext.request.contextPath}/dmly/0401/008">dmly0401008</a></li>
    <li><a id="dmly0401009" href="${pageContext.request.contextPath}/dmly/0401/009">dmly0401009</a></li>
    <li><a id="dmly0401010" href="${pageContext.request.contextPath}/dmly/0401/010">dmly0401010</a></li>
    <li><a id="dmly0401011" href="${pageContext.request.contextPath}/dmly/0401/011">dmly0401011</a></li>
    <li><a id="dmly0401012" href="${pageContext.request.contextPath}/dmly/0401/012">dmly0401012</a></li>
    <li><a id="dmly0401013" href="${pageContext.request.contextPath}/dmly/0401/013">dmly0401013</a></li>
    <li><a id="dmly0401014" href="${pageContext.request.contextPath}/dmly/0401/014">dmly0401014</a></li>
    <li><a id="dmly0401015" href="${pageContext.request.contextPath}/dmly/0401/015">dmly0401015</a></li>
    <li><a id="dmly0401016" href="${pageContext.request.contextPath}/dmly/0401/016">dmly0401016</a></li>
    <li><a id="dmly0401017" href="${pageContext.request.contextPath}/dmly/0401/017">dmly0401017</a></li>
    <li><a id="dmly0401018" href="${pageContext.request.contextPath}/dmly/0401/018">dmly0401018</a></li>
    <li><a id="dmly0401019" href="${pageContext.request.contextPath}/dmly/0401/019">dmly0401019</a></li>
    <li><a id="dmly0401020" href="${pageContext.request.contextPath}/dmly/0401/020">dmly0401020</a></li>
    <li><a id="dmly0401021" href="${pageContext.request.contextPath}/dmly/0401/021">dmly0401021</a></li>
    <li><a id="dmly0401022" href="${pageContext.request.contextPath}/dmly/0401/022">dmly0401022</a></li>
    <li><a id="dmly0401023" href="${pageContext.request.contextPath}/dmly/0401/023">dmly0401023</a></li>
    <li><a id="dmly0401024" href="${pageContext.request.contextPath}/dmly/0401/024">dmly0401024</a></li>
    <li><a id="dmly0401025" href="${pageContext.request.contextPath}/dmly/0401/025">dmly0401025</a></li>
    <li><a id="dmly0401026" href="${pageContext.request.contextPath}/dmly/0401/026">dmly0401026</a></li>
    <li><a id="dmly0401027" href="${pageContext.request.contextPath}/dmly/0401/027">dmly0401027</a></li>
    <li><a id="dmly0401028" href="${pageContext.request.contextPath}/dmly/0401/028">dmly0401028</a></li>
    <li><a id="dmly0401029" href="${pageContext.request.contextPath}/dmly/0401/029">dmly0401029</a></li>
    <li><a id="dmly0401030" href="${pageContext.request.contextPath}/dmly/0401/030">dmly0401030</a></li>
    <li><a id="dmly0401031" href="${pageContext.request.contextPath}/dmly/0401/031">dmly0401031</a></li>
    <li><a id="dmly0401032" href="${pageContext.request.contextPath}/dmly/0401/032">dmly0401032</a></li>
    <li><a id="dmly0401033" href="${pageContext.request.contextPath}/dmly/0401/033">dmly0401033</a></li>
    <li><a id="dmly0401034" href="${pageContext.request.contextPath}/dmly/0401/034">dmly0401034</a></li>
    <li><a id="dmly0401035" href="${pageContext.request.contextPath}/dmly/0401/035">dmly0401035</a></li>
    <li><a id="dmly0401036" href="${pageContext.request.contextPath}/dmly/0401/036">dmly0401036</a></li>
    <li><a id="dmly0401037" href="${pageContext.request.contextPath}/dmly/0401/037">dmly0401037</a></li>
    <li><a id="dmly0401038" href="${pageContext.request.contextPath}/dmly/0401/038">dmly0401038</a></li>
    <li><a id="dmly0401039" href="${pageContext.request.contextPath}/dmly/0401/039">dmly0401039</a></li>
    <li><a id="dmly0401040" href="${pageContext.request.contextPath}/dmly/0401/040">dmly0401040</a></li>
    <li><a id="dmly0401041" href="${pageContext.request.contextPath}/dmly/0401/041">dmly0401041</a></li>
    <li><a id="dmly0401042" href="${pageContext.request.contextPath}/dmly/0401/042">dmly0401042</a></li>
    <li><a id="dmly0401043" href="${pageContext.request.contextPath}/dmly/0401/043">dmly0401043</a></li>
    <li><a id="dmly0401044" href="${pageContext.request.contextPath}/dmly/0401/044">dmly0401044</a></li>
    <li><a id="dmly0401045" href="${pageContext.request.contextPath}/dmly/0401/045">dmly0401045</a></li>
    <li><a id="dmly0401046" href="${pageContext.request.contextPath}/dmly/0401/046">dmly0401046</a></li>
    <li><a id="dmly0401047" href="${pageContext.request.contextPath}/dmly/0401/047">dmly0401047</a></li>
    <li><a id="dmly0401048" href="${pageContext.request.contextPath}/dmly/0401/048">dmly0401048</a></li>
    <li><a id="dmly0401049" href="${pageContext.request.contextPath}/dmly/0401/049">dmly0401049</a></li>
    <li><a id="dmly0401050" href="${pageContext.request.contextPath}/dmly/0401/050">dmly0401050</a></li>
    <li><a id="dmly0401051" href="${pageContext.request.contextPath}/dmly/0401/051">dmly0401051</a></li>
    <li><a id="dmly0401052" href="${pageContext.request.contextPath}/dmly/0401/052">dmly0401052</a></li>
    <li><a id="dmly0402001" href="${pageContext.request.contextPath}/dmly/0402/001">dmly0402001</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>