<div id="wrapper">
  <h1 id="screenTitle">accessDeniedPage</h1>
  <div class="alert alert-danger">
    <ul>
      <li><c:if test="${!empty exceptionCode}">[${f:h(exceptionCode)}]</c:if> <spring:message
          code="e.sf.oth2.9002" /></li>
    </ul>
    <c:if test="${!empty exception.message}">
      <ul>
        <li>${f:h(exception.message)}</li>
      </ul>
    </c:if>
  </div>
  <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
  <br> <br> <br> <br>
</div>
