<div id="wrapper">

  <h1 id="screenTitle">INTR 国際化</h1>

  <div>
    <a id="english" href="${pageContext.request.contextPath}/intr/0201/006?locale=en">English</a>
  </div>
  <div>
    <a id="japanese" href="${pageContext.request.contextPath}/intr/0201/006?locale=ja">Japanese</a>
  </div>
  <div>
    <span id="changeMessage"><spring:message code="i.intr.0001" /></span>
  </div>

  <form:form action="${pageContext.request.contextPath}/intr/0201/006_check" method="get">
    <input type="submit" id="check" class="btn btn-default" value="check">
  </form:form>

</div>