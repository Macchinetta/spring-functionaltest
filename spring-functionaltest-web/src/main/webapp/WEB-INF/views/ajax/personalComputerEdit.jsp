<div id="wrapper">
    <div id="xMLHttpRequestResult" class="hidden alert">
        <ul>
            <li id="returnStatus"></li>
            <li id="contentType"></li>
        </ul>
    </div>

    <div id="resultMessage" class="hidden alert"></div>

    <form id="personalComputerForm" action="${pageContext.request.contextPath}/ajax/0201">
        <spring:nestedPath path="personalComputerForm">
            <fieldset>
                <legend>登録PC編集画面</legend>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <label for="personalComputerId">PC ID</label>
                    </div>
                    <div class="col col-md-4">
                        <input id="personalComputerId" readonly="readonly" class="form-control input-sm" value="1" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="personalComputerName">PC名</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="personalComputerName" cssClass="form-control input-sm" value="${f:h(personalComputerForm.personalComputerName)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="os">OS</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="os" cssClass="form-control input-sm" value="${f:h(personalComputerForm.os)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="cpu">CPU</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="cpu" cssClass="form-control input-sm" value="${f:h(personalComputerForm.cpu)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="ram">RAM</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="ram" cssClass="form-control input-sm" value="${f:h(personalComputerForm.ram)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="videocard">VIDEOCARD</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="videocard" cssClass="form-control input-sm" value="${f:h(personalComputerForm.videocard)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="hdd">HDD</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="hdd" cssClass="form-control input-sm" value="${f:h(personalComputerForm.hdd)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="power">POWER</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="power" cssClass="form-control input-sm" value="${f:h(personalComputerForm.power)}" />
                    </div>
                </div>
                <br />
                <div class="form-group">
                    <div class="col col-md-2">
                        <form:label path="price">PRICE(円)</form:label>
                    </div>
                    <div class="col col-md-4">
                        <form:input path="price" cssClass="form-control input-sm" type="text" value="${f:h(personalComputerForm.price)}" />
                    </div>
                </div>
                <br /> <br />
                <div class="form-group col col-md-5">
                    <form:button id="retrunToIndex" name="retrunToIndex" class="btn btn-default">戻る</form:button>
                    <input id="edit" type="button" name="edit" class="btn btn-default" onclick="return ${f:hjs(method)}('${f:hjs(path)}')" value="編集" />
                </div>
            </fieldset>
        </spring:nestedPath>
    </form>
</div>
