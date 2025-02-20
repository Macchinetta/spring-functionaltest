<div id="wrapper">
    <h1 id="screenTitle">モデルに格納されたコレクション型の値を表示できることの確認</h1>
    <br />
    <form:form action="${pageContext.request.contextPath}/aply/0304/collectionInModel" cssClass="form-horizontal" method="post" modelAttribute="jspFormListForm">
        <legend>コレクション型の値を表示できること</legend>
        <div class="form-group">
            <c:forEach var="jspFormList" items="${jspFormListForm.jspFormList}" varStatus="rowStatus">
                <spring:nestedPath path="jspFormList[${rowStatus.index}]">
                    <div class="form-group">
                        <div class="col col-md-2">氏名&nbsp;${rowStatus.count}(姓、名)</div>
                        <div class="col col-md-3">
                            <form:input path="firstName" cssClass="form-control input-sm" />
                        </div>
                        <div class="col col-md-3">
                            <form:input path="lastName" cssClass="form-control input-sm" />
                        </div>
                    </div>
                </spring:nestedPath>
            </c:forEach>
        </div>
        <br />
        <div class="form-group">
            <form:button name="collectionInModel" class="btn btn-default">コレクション型の値を画面表示</form:button>
        </div>
    </form:form>
</div>
