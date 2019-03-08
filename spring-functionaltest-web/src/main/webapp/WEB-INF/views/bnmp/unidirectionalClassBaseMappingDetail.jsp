<div id="wrapper">
  <h1 id="screenTitle">単方向でのマッピング（クラスベースで定義）</h1>
  <br>
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <td>登録番号</td>
      <td>${f:h(carClassBaseOneWayMappingForm.carId)}</td>
    </tr>
    <tr>
      <td>Car Name</td>
      <td><span id="getCarName">${f:h(carClassBaseOneWayMappingForm.name)}</span></td>
    </tr>
    <tr>
      <td>Car Color</td>
      <td><span id="getCarColor">${f:h(carClassBaseOneWayMappingForm.color)}</span></td>
    </tr>
    <tr>
      <td>release Date</td>
      <td><span id="getCarReleaseDate"><joda:format pattern="yyyyMMdd"
            value="${carClassBaseOneWayMappingForm.releaseDate}" /></span></td>
    </tr>
  </table>
</div>