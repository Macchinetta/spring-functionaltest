<div id="wrapper">
  <h1 id="screenTitle">会員情報登録</h1>
  <br>
  <form:form cssClass="form-horizontal" method="post" modelAttribute="memberForm">
    <legend>個人情報登録確認</legend>
        登録内容は以下の内容でよろしいでしょうか。
        <table class="table table-striped table-bordered table-condensed">
      <tr>
        <th>登録項目</th>
        <th>登録内容</th>
      </tr>
      <tr>
        <th>氏名（姓）</th>
        <td><span id="firstName">${f:h(memberForm.firstName)}</span></td>
      </tr>
      <tr>
        <th>氏名（名）</th>
        <td><span id="lastName">${f:h(memberForm.lastName)}</span></td>
      </tr>
      <tr>
        <th>氏名カナ（姓）</th>
        <td><span id="firstNameKana">${f:h(memberForm.firstNameKana)}</span></td>
      </tr>
      <tr>
        <th>氏名カナ（名）</th>
        <td><span id="lastNameKana">${f:h(memberForm.lastNameKana)}</span></td>
      </tr>
      <tr>
        <th>年齢</th>
        <td><span id="age">${f:h(memberForm.age)}</span></td>
      </tr>
      <tr>
        <th>性別</th>
        <td><span id="gender">${f:h(CL_GENDER[memberForm.gender])}</span></td>
      </tr>
      <tr>
        <th>郵便番号</th>
        <td><span id="zipCode">${f:h(memberForm.zipCode)}</span></td>
      </tr>
      <tr>
        <th>都道府県</th>
        <td><span id="state">${f:h(memberForm.state)}</span></td>
      </tr>
      <tr>
        <th>市区町村</th>
        <td><span id="city">${f:h(memberForm.city)}</span></td>
      </tr>
      <tr>
        <th>番地建物</th>
        <td><span id="address">${f:h(memberForm.address)}</span></td>
      </tr>
      <tr>
        <th>職業</th>
        <td><span id="occupation">${f:h(memberForm.occupation)}</span></td>
      </tr>
      <tr>
        <th>メールアドレス</th>
        <td><span id="mailAddress">${f:h(memberForm.mailAddress)}</span></td>
      </tr>
    </table>
    <div class="form-group">
      <form:button name="redo" class="btn btn-default">戻る</form:button>
      <form:button class="btn btn-default">登録</form:button>
    </div>
  </form:form>
  <!-- セッション内Form確認 -->
  <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['memberForm'].getClass().getName())}</div>
</div>