<div id="wrapper">
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">First Name</th>
      <td><span id="getFirstName">${f:h(resultBean.personDto.firstName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Last Name</th>
      <td><span id="getLastName">${f:h(resultBean.personDto.lastName)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Age</th>
      <td><span id="getAge">${f:h(resultBean.age)}</span></td>
    </tr>
  </table>
</div>