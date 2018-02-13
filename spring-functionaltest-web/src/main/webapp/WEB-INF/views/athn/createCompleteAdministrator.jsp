<div id="wrapper">
  <h1 id="screenTitle">管理者登録完了</h1>
  <br>
  <t:messagesPanel />
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <td>Administrator Name</td>
      <td><span id="getAdministratorName">${f:h(administrator.username)}</span></td>
    </tr>
    <tr>
      <td>Password(エンコード後)</td>
      <td><span id="getAfterEncodePassword">${f:h(administrator.password)}</span></td>
    </tr>
    <tr>
      <td>Password(エンコード前)</td>
      <td><span id="getBeforeEncodePassword">${f:h(beforeEncodePassword)}</span></td>
    </tr>
  </table>
  <br /> <a id="goAthnMenu" href="${pageContext.request.contextPath}/athn/">戻る</a>
</div>