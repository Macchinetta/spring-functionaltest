<div id="wrapper">

  <h1 id="screenTitle">Method認可されたユーザが開くページ</h1>

  <fieldset>
    <legend>Device Information</legend>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>systemConfig.device</th>
        <td><span id="device"> ${f:h(systemConfig.device)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.broadcast</th>
        <td><span id="broadcast"> ${f:h(systemConfig.broadcast)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.ipaddr</th>
        <td><span id="ipaddr"> ${f:h(systemConfig.ipaddr)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.netmask</th>
        <td><span id="netmask"> ${f:h(systemConfig.netmask)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.network</th>
        <td><span id="network"> ${f:h(systemConfig.network)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.onboot</th>
        <td><span id="onboot"> ${f:h(systemConfig.onboot)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.deviceType</th>
        <td><span id="deviceType"> ${f:h(systemConfig.deviceType)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.gateway</th>
        <td><span id="gateway"> ${f:h(systemConfig.gateway)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.owner</th>
        <td><span id="owner"> ${f:h(systemConfig.owner)} </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0301/001/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
