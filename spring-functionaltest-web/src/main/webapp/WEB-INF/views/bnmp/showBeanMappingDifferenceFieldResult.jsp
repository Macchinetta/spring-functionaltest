<div id="wrapper">
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">myoji(First Name)</th>
      <td><span id="getMyoji">${f:h(resultBean.myoji)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Last Name</th>
      <td><span id="getLastName">${f:h(resultBean.lastName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">age</th>
      <td><span id="getAge">${f:h(resultBean.age)}</span></td>
    </tr>
  </table>
</div>