<div id="wrapper">
    <h1 id="screenTitle">Collection型のフィールドをマッピング</h1>
    <br>
    <form:form
        action="${pageContext.request.contextPath}/bnmp/collectionTypeFieldMapping"
        cssClass="form-horizontal" method="post"
        modelAttribute="accountForm">
        <legend>Collection型のフィールドでもコピーができること</legend>
        <div class="row">
            <div class="col col-md-6">
                <c:forEach var="emails" items="${accountForm.emails}"
                    varStatus="rowStatus">
                    <spring:nestedPath path="emails[${rowStatus.index}]">
                        <div class="form-group">
                            <div class="col col-md-4">コピー元email
                                ${rowStatus.count}</div>
                            <div class="col col-md-6">
                                <form:input path="email"
                                    cssClass="form-control input-sm" />
                            </div>
                        </div>
                    </spring:nestedPath>
                </c:forEach>
            </div>
            <div class="col col-md-6">
                <c:forEach var="destinationBean"
                    items="${destinationList}" varStatus="rowStatus">
                    <spring:nestedPath path="emails[${rowStatus.index}]">
                        <div class="form-group">
                            <div class="col col-md-4">コピー先email
                                ${rowStatus.count}</div>
                            <div class="col col-md-6">${f:h(destinationBean)}</div>
                        </div>
                    </spring:nestedPath>
                </c:forEach>
            </div>
        </div>
        <br />
        <div class="form-group">同名フィールド値（cumulative）</div>
        <div class="form-group">
            <form:button
                name="copySameFieldCumulativeListToListSameType"
                class="btn btn-default">List -> List 同じ要素</form:button>
            <form:button
                name="copySameFieldCumulativeListToListDifferenceType"
                class="btn btn-default">List -> List 違う要素</form:button>
            <form:button name="copySameFieldCumulativeListToSet"
                class="btn btn-default">List -> Set</form:button>
        </div>
        <div class="form-group">異名フィールド値（cumulative）</div>
        <div class="form-group">
            <form:button
                name="copyDifferenceFieldCumulativeListToListSameType"
                class="btn btn-default">List -> List 同じ要素</form:button>
            <form:button
                name="copyDifferenceFieldCumulativeListToListDifferenceType"
                class="btn btn-default">List -> List 違う要素</form:button>
            <form:button name="copyDifferenceFieldCumulativeListToSet"
                class="btn btn-default">List -> Set</form:button>
        </div>
        <div class="form-group">同名フィールド値（non-cumulative）</div>
        <div class="form-group">
            <form:button name="copySameFieldNonCumulative"
                class="btn btn-default">non-cumulative</form:button>
            <form:button name="copySameFieldNonCumulativeDeepCopy"
                class="btn btn-default">non-cumulativeかつremove-orphans=true</form:button>
            <form:button name="copySameFieldNonCumulativeShallowCopy"
                class="btn btn-default">copy-by-reference</form:button>
        </div>
        <div class="form-group">異名フィールド値（non-cumulative）</div>
        <div class="form-group">
            <form:button name="copyDifferenceFieldNonCumulative"
                class="btn btn-default">non-cumulative</form:button>
            <form:button name="copyDifferenceFieldNonCumulativeDeepCopy"
                class="btn btn-default">non-cumulativeかつremove-orphans=true</form:button>
            <form:button
                name="copyDifferenceFieldNonCumulativeShallowCopy"
                class="btn btn-default">copy-by-reference</form:button>
        </div>
    </form:form>
</div>