<div id="wrapper">
  <h1>OAuth Approval</h1>
  <p>Do you authorize "${f:h(authorizationRequest.clientId)}" to access your protected
    resources?</p>
  <form id="confirmationForm" name="confirmationForm"
    action="${pageContext.request.contextPath}/oth2/oauth/authorize" method="post">
    <input name="user_oauth_approval" value="true" type="hidden" />
    <sec:csrfInput />
    <c:forEach var="scope" items="${scopes}">
      <li>${f:h(scope.key)}<input type="radio" name="${f:h(scope.key)}"
        id="${f:h(scope.key)}_approve" value="true" /><label for="${f:h(scope.key)}_approve">Approve</label>
        <input type="radio" name="${f:h(scope.key)}" id="${f:h(scope.key)}_deny" value="false"
        checked="checked" /><label for="${f:h(scope.key)}_deny">Deny</label>
      </li>
    </c:forEach>
    <label> <input name="authorize" value="Authorize" id="authorize" type="submit" />
    </label>
  </form>
</div>
