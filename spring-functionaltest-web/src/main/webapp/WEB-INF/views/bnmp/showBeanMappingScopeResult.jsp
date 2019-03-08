<div id="wrapper">
  マッピングの適用範囲指定なしの場合
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">First Name</th>
      <td><span id="getFirstName">${f:h(resultBean.firstName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Last Name</th>
      <td><span id="getLastName">${f:h(resultBean.lastName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">age</th>
      <td><span id="getAge">${f:h(resultBean.age)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">birth Date</th>
      <td><span id="getBirthDate"><joda:format pattern="yyyyMMdd"
            value="${resultBean.birthDate}" /></span></td>
    </tr>
  </table>
  <br> マッピングの適用範囲指定ありの場合
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">First Name</th>
      <td><span id="getScopedFirstName">${f:h(resultScopedBean.firstName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Last Name</th>
      <td><span id="getScopedLastName">${f:h(resultScopedBean.lastName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">age</th>
      <td><span id="getScopedAge">${f:h(resultScopedBean.age)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">birth Date</th>
      <td><span id="getScopedBirthDate"><joda:format pattern="yyyyMMdd"
            value="${resultScopedBean.birthDate}" /></span></td>
    </tr>
  </table>
</div>