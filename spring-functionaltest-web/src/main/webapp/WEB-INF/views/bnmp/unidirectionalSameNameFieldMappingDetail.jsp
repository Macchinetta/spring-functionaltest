<div id="wrapper">
  <h1 id="screenTitle">単方向でのマッピング（同名フィールドをfieldタグで定義）</h1>
  <br>
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <td>登録番号</td>
      <td>${f:h(carSameNameFieldOneWayMappingForm.carId)}</td>
    </tr>
    <tr>
      <td>Car Name</td>
      <td><span id="getCarName">${f:h(carSameNameFieldOneWayMappingForm.name)}</span></td>
    </tr>
    <tr>
      <td>Car Color</td>
      <td><span id="getCarColor">${f:h(carSameNameFieldOneWayMappingForm.color)}</span></td>
    </tr>
    <tr>
      <td>Release Date</td>
      <td><span id="getCarReleaseDate"><joda:format pattern="yyyyMMdd"
            value="${carSameNameFieldOneWayMappingForm.releaseDate}" /></span></td>
    </tr>
  </table>
</div>