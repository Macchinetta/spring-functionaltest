<div id="wrapper">
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="inputColumn">Birth Date</th>
      <td><span id="getBirthDate"><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss.SSS"
            value="${resultBean.birthDate}" /></span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date SqlDate</th>
      <td><span id="getBirthDateSqlDate"><fmt:formatDate pattern="yyyy/MM/dd"
            value="${resultBean.birthDateSqlDate}" /></span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date SqlTime</th>
      <td><span id="getBirthDateSqlTime"><fmt:formatDate pattern="HH:mm:ss.SSS"
            value="${resultBean.birthDateSqlTime}" /></span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date Calendar</th>
      <td><span id="getBirthDateCalendar"><fmt:formatDate
            pattern="yyyy/MM/dd HH:mm:ss.SSS" value="${resultBean.birthDateCalendar.time}" /></span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date GregorianCalendar</th>
      <td><span id="getBirthDateGregorianCalendar"><fmt:formatDate
            pattern="yyyy/MM/dd HH:mm:ss.SSS" value="${resultBean.birthDateGregorianCalendar.time}" /></span></td>
    </tr>
    <tr>
      <th class="inputColumn">Birth Date Timestamp</th>
      <td><span id="getBirthDateTimestamp"><fmt:formatDate
            pattern="yyyy/MM/dd HH:mm:ss.SSS" value="${resultBean.birthDateTimestamp}" /></span></td>
    </tr>
  </table>
</div>