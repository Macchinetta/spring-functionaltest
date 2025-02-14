<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dam3.index" />
    <c:set var="functionId" value="dam3" />

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
        <div class="container">
            <div id="wrapper">
                <h1 id="screenTitle">データベースアクセス（Mybatis3編）</h1>

                <div>[DAM301] Settings for integration of MyBatis3 and Spring</div>
                <ul>
                    <li><a id="dam30101001" href="${pageContext.request.contextPath}/jsp/0101/001">dam30101001</a></li>
                    <li><a id="dam30102001" href="${pageContext.request.contextPath}/jsp/0102/001">dam30102001</a></li>
                    <li><a id="dam30102002" href="${pageContext.request.contextPath}/jsp/0102/002">dam30102002</a></li>
                    <li><a id="dam30103001" href="${pageContext.request.contextPath}/jsp/0103/001">dam30103001</a></li>
                </ul>

                <div>[DAM302] MyBatis3 settings</div>
                <ul>
                    <li><a id="dam30201001" href="${pageContext.request.contextPath}/jsp/0201/001">dam30201001</a></li>
                    <li><a id="dam30202001" href="${pageContext.request.contextPath}/jsp/0202/001">dam30202001</a></li>
                    <li><a id="dam30204001" href="${pageContext.request.contextPath}/jsp/0204/001">dam30204001</a></li>
                    <li><a id="dam30205001" href="${pageContext.request.contextPath}/jsp/0205/001">dam30205001</a></li>
                    <li><a id="dam30206001" href="${pageContext.request.contextPath}/jsp/0206/001">dam30206001</a></li>
                </ul>

                <div>[DAM303] Implementation of database access process.</div>
                <ul>
                    <li><a id="dam30301001" href="${pageContext.request.contextPath}/jsp/0301/001">dam30301001</a></li>
                </ul>

                <div>[DAM304] How to map a JavaBean in Search results</div>
                <ul>
                    <li><a id="dam30401001" href="${pageContext.request.contextPath}/jsp/0401/001">dam30401001</a></li>
                    <li><a id="dam30401002" href="${pageContext.request.contextPath}/jsp/0401/002">dam30402002</a></li>
                    <li><a id="dam30402001" href="${pageContext.request.contextPath}/jsp/0402/001">dam30402001</a></li>
                </ul>

                <div>[DAM305] Search process for Entity</div>
                <ul>
                    <li><a id="dam30501001" href="${pageContext.request.contextPath}/jsp/0501/001">dam30501001</a></li>
                    <li><a id="dam30501002" href="${pageContext.request.contextPath}/jsp/0501/002">dam30501002</a></li>
                    <li><a id="dam30502001" href="${pageContext.request.contextPath}/jsp/0502/001">dam30502001</a></li>
                    <li><a id="dam30502002" href="${pageContext.request.contextPath}/jsp/0502/002">dam30502002</a></li>
                    <li><a id="dam30503001" href="${pageContext.request.contextPath}/jsp/0503/001">dam30503001</a></li>
                    <li><a id="dam30503002" href="${pageContext.request.contextPath}/jsp/0503/002">dam30503002</a></li>
                    <li><a id="dam30504001" href="${pageContext.request.contextPath}/jsp/0504/001">dam30504001</a></li>
                    <li><a id="dam30505001" href="${pageContext.request.contextPath}/jsp/0505/001">dam30505001</a></li>
                    <li><a id="dam30506001" href="${pageContext.request.contextPath}/jsp/0506/001">dam30506001</a></li>
                    <li><a id="dam30507001" href="${pageContext.request.contextPath}/jsp/0507/001">dam30507001</a></li>
                </ul>

                <div>[DAM306] Entity registration process</div>
                <ul>
                    <li><a id="dam30601002" href="${pageContext.request.contextPath}/jsp/0601/002">dam30601002</a></li>
                    <li><a id="dam30601003" href="${pageContext.request.contextPath}/jsp/0601/003">dam30601003</a></li>
                    <li><a id="dam30602001" href="${pageContext.request.contextPath}/jsp/0602/001">dam30602001</a></li>
                    <li><a id="dam30603001" href="${pageContext.request.contextPath}/jsp/0603/001">dam30603001</a></li>
                </ul>

                <div>[DAM307] Update process of Entity</div>
                <ul>
                    <li><a id="dam30701001" href="${pageContext.request.contextPath}/jsp/0701/001">dam30701001</a></li>
                    <li><a id="dam30702001" href="${pageContext.request.contextPath}/jsp/0702/001">dam30702001</a></li>
                </ul>

                <div>[DAM308] Delete process for Entity</div>
                <ul>
                    <li><a id="dam30801001" href="${pageContext.request.contextPath}/jsp/0801/001">dam30801001</a></li>
                    <li><a id="dam30802001" href="${pageContext.request.contextPath}/jsp/0802/001">dam30802001</a></li>
                </ul>

                <div>[DAM309] Implementing dynamic SQL</div>
                <ul>
                    <li><a id="dam30901001" href="${pageContext.request.contextPath}/jsp/0901/001">dam30901001</a></li>
                    <li><a id="dam30902001" href="${pageContext.request.contextPath}/jsp/0902/001">dam30902001</a></li>
                    <li><a id="dam30904001" href="${pageContext.request.contextPath}/jsp/0904/001">dam30904001</a></li>
                    <li><a id="dam30906001" href="${pageContext.request.contextPath}/jsp/0906/001">dam30906001</a></li>
                </ul>

                <div>[DAM310] Escape during LIKE search</div>
                <ul>
                    <li><a id="dam31001001" href="${pageContext.request.contextPath}/jsp/1001/001">dam31001001</a></li>
                </ul>

                <div>[DAM311] Escape during LIKE search</div>
                <ul>
                    <li><a id="dam31101001" href="${pageContext.request.contextPath}/jsp/1101/001">dam31101001</a></li>
                </ul>

                <div>[DAM313] Implementation of TypeHandler</div>
                <ul>
                    <li><a id="dam31301001" href="${pageContext.request.contextPath}/jsp/1301/001">dam31301001</a></li>
                    <li><a id="dam31304001" href="${pageContext.request.contextPath}/jsp/1304/001">dam31304001</a></li>
                    <li><a id="dam31305001" href="${pageContext.request.contextPath}/jsp/1305/001">dam31305001</a></li>
                </ul>

                <div>[DAM314] Implementation of ResultHandler</div>
                <ul>
                    <li><a id="dam31401001" href="${pageContext.request.contextPath}/jsp/1401/001">dam31401001</a></li>
                </ul>

                <div>[DAM316] Implementation of a stored procedure</div>
                <ul>
                    <li><a id="dam31601001" href="${pageContext.request.contextPath}/jsp/1601/001">dam31601001</a></li>
                </ul>

                <div>[DAM318] TypeAlias settings</div>
                <ul>
                    <li><a id="dam31801001" href="${pageContext.request.contextPath}/jsp/1801/001">dam31801001</a></li>
                    <li><a id="dam31801002" href="${pageContext.request.contextPath}/jsp/1801/002">dam31801002</a></li>
                </ul>

                <div>[DAM320] How to fetch a related Entity by a single SQL</div>
                <ul>
                    <li><a id="dam32001001" href="${pageContext.request.contextPath}/jsp/2001/001">dam32001001</a></li>
                    <li><a id="dam32001002" href="${pageContext.request.contextPath}/jsp/2001/002">dam32001002</a></li>
                </ul>

                <div>[DAM321] How to fetch a related Entity using a nested SQL</div>
                <ul>
                    <li><a id="dam32101001" href="${pageContext.request.contextPath}/jsp/2101/001">dam32101001</a></li>
                    <li><a id="dam32102001" href="${pageContext.request.contextPath}/jsp/2102/001">dam32102001</a></li>
                    <li><a id="dam32102002" href="${pageContext.request.contextPath}/jsp/2101/001">dam32102002</a></li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
