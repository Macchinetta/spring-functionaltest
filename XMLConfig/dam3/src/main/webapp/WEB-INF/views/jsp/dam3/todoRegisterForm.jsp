<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dam3.todoRegisterForm" />
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
                    <legend>Todo Registration</legend>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo ID</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoId" class="form-control" value="" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Category Name</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoCategory" class="form-control" value="" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo Title</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoTitle" class="form-control" value="" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form:button name="register" class="btn btn-default">Register</form:button>
                                <form:button name="registerRollback" class="btn btn-default">Register: TRN Rollback</form:button>
                                <form:button name="registerForNull" class="btn btn-default">Register : Null Property Check</form:button>
                                <form:button name="registerAndRetBool" class="btn btn-default">Register : Return boolean</form:button>
                                <form:button name="registerAndRetInt" class="btn btn-default">Register : Return int</form:button>
                                <form:button name="autoIdGenRegister" class="btn btn-default">Register : Using Auto Todo ID Generation</form:button>
                                <form:button name="cancel" class="btn btn-default">Cancel</form:button>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
