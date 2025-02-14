<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dam3.todoRegisterComplete" />
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
            <form:form modelAttribute="todoForm" action="${pageContext.request.contextPath}/jsp/todo/register">
                <fieldset>
                    <legend>Todo Registration Complete</legend>
                    <c:choose>
                        <c:when test="${verifyBooleanPrimitiveRet == true}">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>boolean Value As returned:</label>
                                    </div>
                                    <div class="col-md-4">
                                        <div id="returnRes">${ regResult }</div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${verifyIntegerPrimitiveRet == true}">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>int Value As returned:</label>
                                    </div>
                                    <div class="col-md-4">
                                        <div id="returnRes">${ regResult }</div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo ID</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoId" class="form-control" value="${f:h(todo.todoId)}" readonly="true" style="background-color: whitesmoke" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Category Name</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoCategory" class="form-control" value="${f:h(todo.todoCategory)}" readonly="true" style="background-color: whitesmoke" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo Title</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoTitle" class="form-control" value="${f:h(todo.todoTitle)}" readonly="true" style="background-color: whitesmoke" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo Status</label>
                            </div>
                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${todo.finished == true}">
                                        <form:input path="finished" class="form-control" value="Complete" readonly="true" style="background-color: whitesmoke" />
                                    </c:when>
                                    <c:otherwise>
                                        <form:input path="finished" class="form-control" value="In-Complete" readonly="true" style="background-color: whitesmoke" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Creation Date</label>
                            </div>
                            <div class="col-md-4">
                                <javatime:format value="${todo.createdAt}" pattern="yyyy/MM/dd" var="createdAt" />
                                <form:input path="createdAt" class="form-control" value="${createdAt}" readonly="true" style="background-color: whitesmoke" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Version</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="version" class="form-control" value="${f:h(todo.version)}" readonly="true" style="background-color: whitesmoke" />
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-11"></div>
                            <div class="col-md-1">
                                <form:button name="back" class="button btn btn-default">戻る</form:button>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
