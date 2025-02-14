<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.codeListView" />
    <c:set var="functionId" value="cdls" />

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
                <h1 id="screenTitle">コードリストの読み込み</h1>

                <div>CDLS0101001</div>
                <div>
                    <c:if test="${!empty CL_ORDERSTATUS}">
                        <form:form modelAttribute="codeListForm">
                            <form:select id="cdls0101001" path="code">
                                <form:option value="" label="--Select--" />
                                <form:options items="${CL_ORDERSTATUS}" />
                            </form:select>
                        </form:form>
                    </c:if>
                </div>
                <div>CDLS0101002</div>
                <div>
                    <c:if test="${!empty orderStatusCodeList}">
                        <form:form modelAttribute="codeListForm">
                            <form:select id="cdls0101002" path="code">
                                <form:option value="" label="--Select--" />
                                <form:options items="${orderStatusCodeList.asMap()}" />
                            </form:select>
                        </form:form>
                    </c:if>
                </div>

                <div>CDLS0102001</div>
                <div>
                    <c:if test="${!empty CL_MONTH_ASC}">
                        <form:select id="cdls0102001_jsp" path="codeListForm.code" items="${CL_MONTH_ASC}" />
                    </c:if>
                </div>
                <div>CDLS0102002</div>
                <div>
                    <c:if test="${!empty monthAscCodeList}">
                        <form:select id="cdls0102002_jsp" items="${monthAscCodeList.asMap()}" path="codeListForm.code" />
                    </c:if>
                </div>

                <div>CDLS0103001</div>
                <div>
                    <c:if test="${!empty CL_AUTHORITIES}">
                        <form:select id="cdls0103001_jsp" items="${CL_AUTHORITIES}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0103002</div>
                <div>
                    <c:if test="${!empty authoritiesCodeList}">
                        <form:select id="cdls0103002_jsp" items="${authoritiesCodeList.asMap()}" path="codeListForm.code" />
                    </c:if>
                </div>

                <div>CDLS0104001</div>
                <div>
                    <c:if test="${!empty CL_ENUM_ORDERSTATUS}">
                        <form:select id="cdls0104001_jsp" items="${CL_ENUM_ORDERSTATUS}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0104002</div>
                <div>
                    <c:if test="${!empty enumOrderStatusCodeList}">
                        <form:select id="cdls0104002_jsp" items="${enumOrderStatusCodeList.asMap()}" path="codeListForm.code" />
                    </c:if>
                </div>

                <div>CDLS0105001</div>
                <div>
                    <c:if test="${!empty CL_I18N_PRICE}">
                        <form:select id="cdls0105001_jsp" items="${CL_I18N_PRICE}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105002</div>
                <div>
                    <c:if test="${!empty CL_I18N_PRICE}">
                        <form:select id="cdls0105002_jsp" items="${CL_I18N_PRICE}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105003</div>
                <div>
                    <c:if test="${!empty i18nPriceCodeList}">
                        <form:select id="cdls0105003_jsp" items="${i18nPriceCodeList}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105004</div>
                <div>
                    <c:if test="${!empty i18nPriceCodeList}">
                        <form:select id="cdls0105004_jsp" items="${i18nPriceCodeList}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105005</div>
                <div>
                    <c:if test="${!empty CL_I18N_DB_PRICE}">
                        <form:select id="cdls0105005_jsp" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105006</div>
                <div>
                    <c:if test="${!empty CL_I18N_DB_PRICE}">
                        <form:select id="cdls0105006_jsp" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105007</div>
                <div>
                    <c:if test="${!empty i18nDBPriceCodeList}">
                        <form:select id="cdls0105007_jsp" items="${i18nDBPriceCodeList}" path="codeListForm.code" />
                    </c:if>
                </div>
                <div>CDLS0105008</div>
                <div>
                    <c:if test="${!empty i18nDBPriceCodeList}">
                        <form:select id="cdls0105008_jsp" items="${i18nDBPriceCodeList}" path="codeListForm.code" />
                    </c:if>
                </div>

                <div>CDLS0106001</div>
                <div>
                    <c:if test="${!empty CL_ORDERSTATUS}">
                        <div id="cdls0106001_jsp">${f:h(CL_ORDERSTATUS[orderStatusForm.orderStatus])}</div>
                    </c:if>
                </div>

                <div>CDLS0107001</div>
                <div>
                    <c:if test="${!empty CL_ORDERSTATUS}">
                        <form:form action="${pageContext.request.contextPath}/jsp/0107/001?post" method="post" modelAttribute="orderStatusForm">
                            <form:select id="cdls0107001_jsp" path="orderStatus">
                                <form:options items="${CL_ORDERSTATUS}" />
                            </form:select>
                            <form:errors id="errorCDLS0107001" path="orderStatus" />
                            <button type="submit" id="submitCDLS0107001">Submit Codelist</button>
                        </form:form>
                    </c:if>
                </div>
                <div>CDLS0107002</div>
                <div>
                    <c:if test="${!empty CL_ORDERSTATUS}">
                        <form:form action="${pageContext.request.contextPath}/jsp/0107/002?post" method="post" modelAttribute="orderStatusForm">
                            <form:select id="cdls0107002_jsp" path="orderStatus">
                                <form:option value="nonExistentValue" label="nonExistentValue" />
                            </form:select>
                            <form:errors id="errorCDLS0107002" path="orderStatus" />
                            <button type="submit" id="submitCDLS0107002">Submit Codelist</button>
                        </form:form>
                    </c:if>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
