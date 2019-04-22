<div id="wrapper">
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">Birth Date</th>
      <td><span id="getBirthDate">${f:h(resultBean.birthDate)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date SqlDate</th>
      <td><span id="getBirthDateSqlDate">${f:h(resultBean.birthDateSqlDate)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date SqlTime</th>
      <td><span id="getBirthDateSqlTime">${f:h(resultBean.birthDateSqlTime)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date Calendar</th>
      <td><span id="getBirthDateCalendar">${f:h(resultBean.birthDateCalendar)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date GregorianCalendar</th>
      <td><span id="getBirthDateGregorianCalendar">${f:h(resultBean.birthDateGregorianCalendar)}</span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date Timestamp</th>
      <td><span id="getBirthDateTimestamp">${f:h(resultBean.birthDateTimestamp)}</span></td>
    </tr>
  </table>
</div>