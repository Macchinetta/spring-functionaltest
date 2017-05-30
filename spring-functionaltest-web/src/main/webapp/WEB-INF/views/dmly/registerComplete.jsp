<form:form modelAttribute="deliveryOrderForm"
    action="${pageContext.request.contextPath}/dmly/deliveryorder/register">
    <fieldset>
        <legend>配達オーダ登録完了</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <label>配達No</label>
                </div>
                <div class="col-md-4">
                    <form:input path="deliveryNo" class="form-control"
                        value="${f:h(deliveryOrder.deliveryNo)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>配達種別</label>
                </div>
                <div class="col-md-4">
                    <form:input path="deliveryTypeName" class="form-control"
                        value="${f:h(deliveryOrder.deliveryType.deliveryTypeName)}"
                        readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>送り主名</label>
                </div>
                <div class="col-md-4">
                    <form:input path="senderName" class="form-control"
                        value="${f:h(deliveryOrder.senderName)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>送り主住所</label>
                </div>
                <div class="col-md-4">
                    <form:input path="senderAddress" class="form-control"
                        value="${f:h(deliveryOrder.senderAddress)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>送り先名</label>
                </div>
                <div class="col-md-4">
                    <form:input path="recieverName" class="form-control"
                        value="${f:h(deliveryOrder.recieverName)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>送り先住所</label>
                </div>
                <div class="col-md-4">
                    <form:input path="recieverAddress" class="form-control"
                        value="${f:h(deliveryOrder.recieverAddress)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>受付日時</label>
                </div>
                <div class="col-md-4">
                    <fmt:formatDate value="${deliveryOrder.acceptDatetime}"
                        pattern="yyyy/MM/dd HH:mm:ss" var="acceptDatetime" />
                    <form:input path="acceptDatetime" class="form-control"
                        value="${acceptDatetime}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>完了日時</label>
                </div>
                <div class="col-md-4">
                    <fmt:formatDate value="${deliveryOrder.completionDatetime}"
                        pattern="yyyy/MM/dd HH:mm:ss" var="completionDatetime" />
                    <form:input path="completionDatetime" class="form-control"
                        value="${completionDatetime}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>配達者</label>
                </div>
                <div class="col-md-4">
                    <form:input path="deliveryDriver" class="form-control"
                        value="${f:h(deliveryOrder.deliveryDriver)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>状態</label>
                </div>
                <div class="col-md-4">
                    <form:input path="deliveryStatus" class="form-control"
                        value="${f:h(deliveryOrder.deliveryStatus)}" readonly="true"
                        style="background-color: whitesmoke;" />
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-11"></div>
                <div class="col-md-1">
                    <form:button name="back"
                        class="button btn btn-default">戻る</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
