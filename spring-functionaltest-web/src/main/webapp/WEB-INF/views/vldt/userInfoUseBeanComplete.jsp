<legend>Method Validation確認 JavaBean 確認画面</legend>
<div class="container">
  <div class="row">
    <div class="col-md-4">
      <label>userId</label>
    </div>
    <div id="userId" class="col-md-4">${f:h(userInfo.userId)}</div>
  </div>
  <div class="row">
    <div class="col-md-4">
      <label>userName</label>
    </div>
    <div id="userName" class="col-md-4">${f:h(userInfo.userName)}</div>
  </div>
  <div class="row">
    <div class="col-md-4">
      <label>dateOfBirth</label>
    </div>
    <div id="dateOfBirth" class="col-md-4">${f:h(userInfo.dateOfBirth)}</div>
  </div>
</div>
