<div id="wrapper">
    <h1 id="screenTitle">単方向・双方向でマッピング</h1>
    <br>
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <td>登録番号</td>
            <td>${f:h(carForm.carId)}</td>
        </tr>
        <tr>
            <td>Car Name</td>
            <td><span id="getCarName">${f:h(carForm.carName)}</span></td>
        </tr>
        <tr>
            <td>Car Color</td>
            <td><span id="getCarColor">${f:h(carForm.carColor)}</span></td>
        </tr>
        <tr>
            <td>release Date</td>
            <td><span id="getCarReleaseDate"><joda:format pattern="yyyyMMdd"
                    value="${carForm.carReleaseDate}" /></span></td>
        </tr>
    </table>
</div>