<div id="wrapper">
    <h1 id="screenTitle">単方向でのマッピング（同名フィールドをfieldタグで定義）</h1>
    <br />
    <t:messagesPanel />
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <td>登録番号</td>
            <td>${f:h(car.carId)}</td>
        </tr>
        <tr>
            <td>Car Name</td>
            <td><span id="getCarName">${f:h(car.name)}</span></td>
        </tr>
        <tr>
            <td>Car Color</td>
            <td><span id="getCarColor">${f:h(car.color)}</span></td>
        </tr>
        <tr>
            <td>Release Date</td>
            <td>
                <span id="getCarReleaseDate"><fmt:formatDate pattern="yyyyMMdd" value="${car.releaseDate}" /></span>
            </td>
        </tr>
    </table>
    <div class="form-group">
        <a id="copyUnidirectionalBean" href="${pageContext.request.contextPath}/bnmp/unidirectionalSameNameFieldMapping/car/${f:h(car.carId)}?unidirectional" class="btn btn-default"
            >詳細(単方向Bean)</a
        >
    </div>
</div>
