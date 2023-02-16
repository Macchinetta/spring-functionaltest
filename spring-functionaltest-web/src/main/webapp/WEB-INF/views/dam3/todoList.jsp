<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dam3.todoList" />
<c:set var="functionId" value="dam3" />

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
<div class="row">
  <h1 id="screenTitle">Todo List</h1>
  <div>
    <form:form modelAttribute="todoForm"
      action="${pageContext.request.contextPath}/dam3/todo/register">
      <form:button name="form" class="btn btn-default">Add Todo</form:button>
    </form:form>
  </div>
  <div>
    <form:form modelAttribute="todoForm"
      action="${pageContext.request.contextPath}/dam3/todo/bulkReg" method="post">
      <form:button name="addBulk" class="btn btn-default">Add Bulk Todo</form:button>
      <form:button name="batchReg" class="btn btn-default">Add Todos in Batch</form:button>
      <c:if test="${batchReg == true || batchDL == true || batchUp == true || batchRepoUp == true}">
        <div id="regCnt" style="color: green;">Total Todos Registered/Updated in Batch
          :&nbsp;${regCount }</div>

      </c:if>
    </form:form>
  </div>
  <div>
    <form:form modelAttribute="todoForm"
      action="${pageContext.request.contextPath}/dam3/todo/addByReUse" method="post">
      <form:button name="addReUse" class="btn btn-default">Add Todo(REUSE SQL Mode)</form:button>
    </form:form>
  </div>
  <c:set var="complete" value="${0}" />
  <c:set var="incomplete" value="${0}" />
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th class="no">No</th>
        <th class="todoId">Todo Id</th>
        <th class="title">Todo Title</th>
        <th class="createdDate">Created Date</th>
        <th class="status">Status</th>
        <th class="categoryName">Todo Category</th>
        <th class="createdDate">Completion Date</th>
        <th class="createdDate">Desc1(Blob)</th>
        <th class="createdDate">Desc2(Clob)</th>
      </tr>
    </thead>
    <c:choose>

      <c:when test="${mapRes == true}">
        <c:forEach var="todoItem" items="${todoMapRes}" varStatus="rowStatus">
          <tr>
            <td class="no" id="no">${rowStatus.count}</td>
            <td class="bookId"><a id="todoLink_${todoItem.key}"
              href="${pageContext.request.contextPath}/dam3/todo/${todoItem.key}/update">
                ${f:h(todoItem.key)}</a></td>
            <td class="title" id="title">${f:h(todoItem.value.todoTitle)}</td>
            <td class="releaseDate" id="createdDate"><javatime:format pattern="yyyy-MM-dd" value="${todoItem.value.createdAt}" /></td>
            <c:choose>
              <c:when test="${todoItem.value.finished == true}">
                <c:set var="complete" value="${complete + 1}" />
                <td class="title" id="todoStatus">Completed</td>
              </c:when>
              <c:otherwise>
                <c:set var="incomplete" value="${incomplete + 1}" />
                <td class="title" id="todoStatus">Incomplete</td>
              </c:otherwise>
            </c:choose>
            <td class="categoryName" id="categoryName">Not Fetched</td>
            <td class="releaseDate" id="completeAt"><javatime:format pattern="yyyy-MM-dd" value="${todoItem.value.completeAt}" /></td>
          </tr>
        </c:forEach>
      </c:when>
      <c:when test="${isPaginated == true}">
        <c:forEach var="todo" items="${page.content}" varStatus="rowStatus">
          <tr>
            <td class="no" id="no">${(page.number * page.size)
                            + rowStatus.count}</td>
            <td class="bookId"><a id="todoLink_${todo.todoId}"
              href="${pageContext.request.contextPath}/dam3/todo/${todo.todoId}/update">
                ${f:h(todo.todoId)}</a></td>
            <td class="title" id="title">${f:h(todo.todoTitle)}</td>
            <td class="releaseDate" id="createdDate"><javatime:format value="${todo.createdAt}" pattern="yyyy-MM-dd" /></td>
            <c:choose>
              <c:when test="${todo.finished == true}">
                <c:set var="complete" value="${complete + 1}" />
                <td class="title" id="todoStatus">Completed</td>
              </c:when>
              <c:otherwise>
                <c:set var="incomplete" value="${incomplete + 1}" />
                <td class="title" id="todoStatus">Incomplete</td>
              </c:otherwise>
            </c:choose>
            <td class="categoryName" id="categoryName"><a id="todoLink_${rowStatus.count}"
              href="${pageContext.request.contextPath}/dam3/todo/${todo.todoId}/update"> Refer Details Page</a></td>
            <td class="releaseDate" id="completeAt"><javatime:format pattern="yyyy-MM-dd" value="${todo.completeAt}" /></td>
          </tr>
        </c:forEach>
      </c:when>

      <c:otherwise>
        <c:forEach var="todo" items="${todos}" varStatus="rowStatus">
          <tr>
            <td class="no" id="no">${rowStatus.count}</td>
            <td class="bookId"><a id="todoLink_${todo.todoId}" href="${pageContext.request.contextPath}/dam3/todo/${todo.todoId}/update">${f:h(todo.todoId)}</a></td>
            <td class="title" id="title">${f:h(todo.todoTitle)}</td>
            <td class="releaseDate" id="createdDate">
              <javatime:format value="${todo.createdAt}" pattern="yyyy-MM-dd" />
            </td>
            <c:choose>
              <c:when test="${todo.finished == true}">
                <c:set var="complete" value="${complete + 1}" />
                <td class="title" id="todoStatus">Completed</td>
              </c:when>
              <c:otherwise>
                <c:set var="incomplete" value="${incomplete + 1}" />
                <td class="title" id="todoStatus">Incomplete</td>
              </c:otherwise>
            </c:choose>
            <td class="categoryName" id="categoryName">${f:h(todo.category.name)}</td>
            <td class="releaseDate" id="completeAt"><javatime:format pattern="yyyy-MM-dd" value="${todo.completeAt}" /></td>
            <td class="title" id="desc1">${f:h(todo.normDesc1)}</td>
            <td class="title" id="desc1">${f:h(todo.normDesc2)}</td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </table>
  <c:if test="${isPaginated == true}">
    <!-- This choose tag is used to identify whether the pagination is done by standard MyBatis method or the SQL refinement method. -->
    <c:choose>
      <c:when test="${isSQLRfinePageSrch == true}">
        <c:set var="paginationContextName" value="sqlRefPageSearch"></c:set>
      </c:when>
      <c:otherwise>
        <c:set var="paginationContextName" value="stdPageSearch"></c:set>
      </c:otherwise>
    </c:choose>
    <javatime:format value="${todoForm.createdAt}" pattern="yyyy-MM-dd" var="createdAt" />
    <div class="paginationPart" align="center">
      <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination"
        queryTmpl="page={page}&size={size}&${paginationContextName }=''&todoTitle=${ todoForm.todoTitle}&createdAt=${createdAt }" />
    </div>
  </c:if>


  <div>
    <table class="table-striped table-bordered table-condensed">
      <tr>
        <th>Completed Todo</th>
        <td id="completedTodo" colspan="2">${ complete }</td>
      </tr>
      <tr>
        <th>Incomplete Todo</th>
        <td id="incompleteTodo">${ incomplete}</td>
      </tr>
      <tr>
        <th>Total Todos</th>
        <td id="totalTodo">${ complete + incomplete}</td>
      </tr>
    </table>
  </div>
  <br>
  <div>
    <table class="table-striped table-bordered table-condensed">
      <form:form modelAttribute="todoForm"
        action="${pageContext.request.contextPath}/dam3/todo/update" method="post">
        <tr>
          <th>Enter Todo ID to be marked as Complete(Multiple todo id can be separated by comma)</th>
          <td><form:input path="todoIds" class="form-control" value="" /></td>
          <td><form:button name="bulkUpdate" class="btn btn-default">Update in Batch</form:button></td>
          <td><form:button name="batchRepoUpdt" class="btn btn-default">Update Using Batch Mode</form:button></td>
        </tr>
        <tr>
          <th>Specify Date : Completed Todos before this date can be deleted</th>
          <td><form:input path="deleteDate" class="form-control" value="" /></td>
          <td colspan="2"><form:button name="bulkDelete" class="btn btn-default">Delete in Batch</form:button></td>
        </tr>
      </form:form>
    </table>
  </div>
  <br>
  <div>
    <form:form modelAttribute="todoForm"
      action="${pageContext.request.contextPath}/dam3/todo/search" method="get">
      <fieldset>
        <legend>Input Fields</legend>
        <table class="table-striped table-bordered table-condensed">
          <tr>
            <td>Enter Todo ID :<form:input path="todoId" class="form-control" value="" /></td>
            <td>Enter Category Name :<form:input path="todoCategory" class="form-control" value="" /></td>
            <td>Enter Todo Title :<form:input path="todoTitle" class="form-control" value="" /></td>
            <td>Enter Todo creation date : <javatime:format value="${todoForm.createdAt}"
                pattern="yyyy-MM-dd" var="createdAt" /> <form:input path="createdAt"
                class="form-control" value="${createdAt}" /></td>
          </tr>
        </table>
      </fieldset>
      <fieldset>
        <legend>Action Buttons</legend>
        <table class="table-striped table-bordered table-condensed">
          <tr>
            <td><form:button name="autoMapSrch" class="btn btn-default">Auto Map Search</form:button></td>
            <td><form:button name="asClauseSrch" class="btn btn-default">Use of AS Clause</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Todo Id</td>
          </tr>
          <tr>
            <td><form:button name="pkSrch" class="btn btn-default">Search By PK</form:button>&nbsp;<form:button
                name="pkSrchNG" class="btn btn-default">Search By PK:NA</form:button></td>
            <td><form:button name="spSrch" class="btn btn-default">Search Using Stored Procedure</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Todo Id</td>
          </tr>
          <tr>
            <td><form:button name="cmpKeySrch" class="btn btn-default">Composite Key Search</form:button></td>
            <td><form:button name="cmpKeySrchNoParamAnnt" class="btn btn-default">Composite Key Search: No @Param Annotation</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Todo Id and Category Name</td>
          </tr>
          <tr>
            <td><form:button name="criteriaSrch" class="btn btn-default">Search By Criteria</form:button>
              &nbsp;<form:button name="criteriaSrchMapRes" class="btn btn-default">Search By Criteria: Return Result in Map</form:button></td>
            <td><form:button name="downloadTodo" class="btn btn-default">CSV Download : ResultHandler Demo</form:button>
              <c:if test="${showCSVPath == true}">
                <br>File path<div style="color: green; font: bold;" id="csvPath">${csvFilePath }</div>
              </c:if></td>
            <td style="color: blue; font: bolder;">Enter values in Todo title and Created
              At(yyyy/mm/dd)</td>
          </tr>
          <tr>
            <td>Get Todo Count for Given Status-->::&nbsp;Incomplete&nbsp;<form:radiobutton
                id="incomplete" path="finished" value="0" />&nbsp;&nbsp;Finished&nbsp;<form:radiobutton
                id="complete" path="finished" value="1" /></td>
            <td colspan="1"><form:button name="countByStatus" class="btn btn-default">Get Count</form:button>&nbsp;<form:button
                name="ifSrch" class="btn btn-default">IF element demo search</form:button></td>
            <td id="statCount" style="color: green; font: bolder;">${status}&nbsp;:&nbsp;${count }</td>
          </tr>
          <tr>
            <td><form:button name="stdPageSearch" class="btn btn-default">Page Search : Standard Method</form:button>&nbsp;
              <form:button name="sqlRefPageSearch" class="btn btn-default">Page Search : SQL Refine Method</form:button></td>
            <td><form:button name="clTypeAliasSrch" class="btn btn-default">Type Alias : Per Class</form:button>&nbsp;
              <form:button name="typeAlOverWrDefName" class="btn btn-default">Type Alias : Overwrite Def Name</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Todo title and Date</td>
          </tr>
          <tr>
            <td><form:button name="bindSrch" class="btn btn-default">BIND element Search</form:button></td>
            <td><form:button name="escpSrch" class="btn btn-default">Escape Search</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Todo title for like search</td>
          <tr>
          <tr>
            <td colspan="2"><form:button name="chooseSrch" class="btn btn-default">CHOOSE element demo search</form:button></td>
            <td style="color: blue; font: bolder;">Enter values in Create Date and optional
              Todo title</td>
          <tr>
          </tr>
        </table>
      </fieldset>
    </form:form>
  </div>
  <br>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>