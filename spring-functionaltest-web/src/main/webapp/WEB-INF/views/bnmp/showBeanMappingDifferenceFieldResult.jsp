<div id="wrapper">
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">Myoji(First Name)</th>
      <td><span id="getMyoji">${f:h(resultBean.myoji)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Namae(Last Name)</th>
      <td><span id="getNamae">${f:h(resultBean.namae)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Age</th>
      <td><span id="getAge">${f:h(resultBean.age)}</span></td>
    </tr>
  </table>
</div>