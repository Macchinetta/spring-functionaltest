<div id="wrapper">
    <h1 id="screenTitle">ROLE_STAFF以上を保持しているデータを入力してregister可のページ</h1>

    <form:form action="${pageContext.request.contextPath}/athr/0901/003" method="post" modelAttribute="systemConfigForm">
        <fieldset>
            <legend>Device</legend>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="device">Device</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="device" name="device" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="broadcast">Broadcast</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="broadcast" name="broadcast" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="ipaddr">Ip Address</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="ipaddr" name="ipaddr" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="netmask">Netmask</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="netmask" name="netmask" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="network">Network</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="network" name="network" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="onboot">On Boot</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="onboot" name="onboot" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="deviceType">Device Type</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="deviceType" name="deviceType" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="gateway">Gateway</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="gateway" name="gateway" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="owner">Owner</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="owner" name="owner" />
                </div>
            </div>
            <br /> <input type="submit" id="register" class="btn btn-default" value="Register" name="register" />
        </fieldset>
    </form:form>
</div>
