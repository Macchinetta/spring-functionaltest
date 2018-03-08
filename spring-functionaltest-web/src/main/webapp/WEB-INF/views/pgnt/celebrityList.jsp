<h3 id="screenTitle">Celebrity List Screen</h3>
<div class="text-center">
  <form:form action="${pageContext.request.contextPath}/pgnt/${f:hjs(path)}" method="get"
    modelAttribute="celebritySearchCriteria">
    <fieldset>
      <legend>Celebrity List</legend>
      <form:label cssClass="space" path="firstName">First Name</form:label>
      <form:input cssClass="space name-text-field form-control inline" path="firstName" />

      <form:label cssClass="space" path="lastName">Last Name</form:label>
      <form:input cssClass="space name-text-field form-control inline" path="lastName" />

      <form:select cssClass="space sort-field form-control inline" path="sort">
        <form:option id="celebrityIdASC" value="celebrity_id,ASC">No. ASC</form:option>
        <form:option id="celebrityIdDESC" value="celebrity_id,DESC">No. DESC</form:option>
        <form:option id="firstNameASC" value="first_name,ASC">First Name ASC</form:option>
        <form:option id="firstNameDESC" value="first_name,DESC">First Name DESC</form:option>
        <form:option id="lastNameASC" value="last_name,ASC">Last Name ASC</form:option>
        <form:option id="lastNameDESC" value="last_name,DESC">Last Name DESC</form:option>
      </form:select>

      <input id="searchBtn" type="submit" name="Search" class="btn btn-default" value="Search" />
    </fieldset>
  </form:form>
</div>

<c:if test="${page != null}">
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(celebritySearchCriteria)}" />
  </div>
  <table class="table table-striped table-bordered table-condensed pgnttable">
    <thead>
      <tr>
        <th>No.</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th />
      </tr>
    </thead>

    <c:forEach var="user" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td>${f:h(user.celebrityId)}</td>
        <td>${f:h(user.firstName)}</td>
        <td>${f:h(user.lastName)}</td>
        <td class="text-center"><input type="submit" class="btn btn-default" value="Details" />
        </td>
      </tr>
    </c:forEach>

  </table>
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(celebritySearchCriteria)}" />
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

