<div class="row">

  <h1 id="screenTitle">ユーザ一覧</h1>
  <div>
    <form:form modelAttribute="userForm"
      action="${pageContext.request.contextPath}/dtac/user/register">
      <form:button name="form" class="btn btn-default">登録</form:button>
    </form:form>
  </div>
  <br>
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th class="no">No</th>
        <th class="username">ユーザ名</th>
        <th class="enabled">有効/無効</th>
        <th class="authority">権限</th>
      </tr>
    </thead>
    <c:forEach var="user" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${(page.number * page.size)
                            + rowStatus.count}</td>
        <td class="username" id="username">${f:h(user.username)}</td>
        <td class="enabled" id="enabled">${f:h(user.enabled)}</td>
        <td class="authority" id="authority">${f:h(user.authority)}</td>
      </tr>
    </c:forEach>
  </table>

  <div class="paginationPart" align="center">
    <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}" />
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-11"></div>
      <div class="col-md-1">
        <form:form modelAttribute="userForm"
          action="${pageContext.request.contextPath}/dtac/user/list">
          <form:button name="back" class="btn btn-default">戻る</form:button>
        </form:form>
      </div>
    </div>
  </div>
</div>
