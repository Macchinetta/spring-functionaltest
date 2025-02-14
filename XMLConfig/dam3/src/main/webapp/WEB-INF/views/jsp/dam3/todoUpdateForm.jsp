<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dam3.todoUpdateForm" />
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
            <form:form modelAttribute="todoForm" action="${pageContext.request.contextPath}/jsp/todo/${todo.todoId}/update">
                <fieldset>
                    <legend>Todo Update Form</legend>
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
                                <label>Todo Category</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoCategory" class="form-control" value="${f:h(todo.todoCategory)}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo Title</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="todoTitle" class="form-control" value="${f:h(todo.todoTitle)}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Todo Status</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="finished" class="form-control" value="${f:h(todo.finished)}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Creation date</label>
                            </div>
                            <div class="col-md-4">
                                <javatime:format pattern="yyyy/MM/dd" value="${todo.createdAt}" var="createdAt" />
                                <form:input path="createdAt" class="form-control" value="${createdAt}" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">
                                <label>Completion date</label>
                            </div>
                            <div class="col-md-4">
                                <javatime:format pattern="yyyy/MM/dd" value="${todo.completeAt}" var="completeAt" />
                                <form:input path="completeAt" class="form-control" value="${completeAt}" />
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
                        <div class="row">
                            <div class="col-md-4">
                                <label>Desc1 (BLOB)</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="description1" class="form-control" value="${f:h(todo.description1)}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Desc2 (CLOB)</label>
                            </div>
                            <div class="col-md-4">
                                <form:input path="description2" class="form-control" value="${f:h(todo.description2)}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <form:button name="update" class="btn btn-default">Update</form:button>
                                <form:button name="updateOptimistic" class="btn btn-default">Update(Consider Version)</form:button>
                                <form:button name="delete" class="btn btn-default">Delete</form:button>
                                <form:button name="deleteByTodo" class="btn btn-default">Delete By Todo Obj</form:button>
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
