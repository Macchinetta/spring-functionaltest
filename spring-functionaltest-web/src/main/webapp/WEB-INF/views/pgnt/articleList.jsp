<h3 id="screenTitle">Article List Screen</h3>
<div class="text-center">
  <form:form action="${pageContext.request.contextPath}/pgnt/${f:hjs(path)}" method="get"
    modelAttribute="articleSearchCriteria">
    <fieldset>
      <legend>Article List</legend>
      <form:label cssClass="space" path="title">Title</form:label>
      <form:input cssClass="space title-text-field form-control inline" path="title" />
      <input id="searchBtn" type="submit" name="Search" class="btn btn-default" value="Search" />
    </fieldset>
  </form:form>
</div>

<c:if test="${page != null}">
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(articleSearchCriteria)}" />
  </div>
  <table class="table table-striped table-bordered table-condensed pgnttable">
    <thead>
      <tr>
        <th>No.</th>
        <th>Category</th>
        <th>Title</th>
        <th>Overview</th>
        <th>Publish Date</th>
      </tr>
    </thead>

    <c:forEach var="article" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td>${f:h(article.articleId)}</td>
        <td>${f:h(article.category)}</td>
        <td><a href="">${f:h(article.title)}</a></td>
        <td>${f:h(article.overview)}</td>
        <td><fmt:formatDate value="${article.publishDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>

  </table>
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(articleSearchCriteria)}" />
  </div>
  <div class="text-center">
    <fmt:formatNumber value="${page.totalElements}" />
    results
  </div>
  <c:if test="${page.totalElements != 0}">
    <div class="text-center">${f:h(page.number + 1) }&nbsp;/&nbsp;${f:h(page.totalPages)}&nbsp;Pages
    </div>
  </c:if>
</c:if>

