<div id="wrapper">

  <h1 id="screenTitle">Controllerの実装方法(HTTPメソッドの設定)</h1>

  <form:form action="${pageContext.request.contextPath}/aply/0101/002_1" method="post"
    cssClass="form-horizontal" modelAttribute="handleRequestForm">
    <form:button id="aply0101002_1" name="aply0101002_1" class="btn btn-default">HTTPメソッドをPOSTのみ指定（POST送信）</form:button>
  </form:form>
  <form:form action="${pageContext.request.contextPath}/aply/0101/002_2" method="post"
    cssClass="form-horizontal" modelAttribute="handleRequestForm">
    <form:button id="aply0101002_2post" name="aply0101002_2post" class="btn btn-default">HTTPメソッドをPOSTとGETのみ指定（POST送信）</form:button>
  </form:form>
  <form action="${pageContext.request.contextPath}/aply/0101/002_2" method="get"
    class="form-horizontal">
    <spring:nestedPath path="handleRequestForm">
      <input type="submit" id="aply0101002_2get" class="btn btn-default"
        value="HTTPメソッドをPOSTとGETのみ指定（GET送信）" />
    </spring:nestedPath>
  </form>
</div>
