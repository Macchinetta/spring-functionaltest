<div id="wrapper">
    <h1 id="screenTitle">コードリストの読み込み</h1>

    <div>CDLS0101001</div>
    <div>
        <c:if test="${!empty CL_ORDERSTATUS}">
            <form:form modelAttribute="codeListForm">
                <form:select id="cdls0101001" path="code">
                    <form:option value="" label="--Select--" />
                    <form:options items="${CL_ORDERSTATUS}" />
                </form:select>
            </form:form>
        </c:if>
    </div>
    <div>CDLS0101002</div>
    <div>
        <c:if test="${!empty orderStatusCodeList}">
            <form:form modelAttribute="codeListForm">
                <form:select id="cdls0101002" path="code">
                    <form:option value="" label="--Select--" />
                    <form:options items="${orderStatusCodeList.asMap()}" />
                </form:select>
            </form:form>
        </c:if>
    </div>

    <div>CDLS0102001</div>
    <div>
        <c:if test="${!empty CL_MONTH_ASC}">
            <form:select id="cdls0102001" path="codeListForm.code" items="${CL_MONTH_ASC}" />
        </c:if>
    </div>
    <div>CDLS0102002</div>
    <div>
        <c:if test="${!empty monthAscCodeList}">
            <form:select id="cdls0102002" items="${monthAscCodeList.asMap()}" path="codeListForm.code" />
        </c:if>
    </div>

    <div>CDLS0103001</div>
    <div>
        <c:if test="${!empty CL_AUTHORITIES}">
            <form:select id="cdls0103001" items="${CL_AUTHORITIES}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0103002</div>
    <div>
        <c:if test="${!empty authoritiesCodeList}">
            <form:select id="cdls0103002" items="${authoritiesCodeList.asMap()}" path="codeListForm.code" />
        </c:if>
    </div>

    <div>CDLS0104001</div>
    <div>
        <c:if test="${!empty CL_ENUM_ORDERSTATUS}">
            <form:select id="cdls0104001" items="${CL_ENUM_ORDERSTATUS}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0104002</div>
    <div>
        <c:if test="${!empty enumOrderStatusCodeList}">
            <form:select id="cdls0104002" items="${enumOrderStatusCodeList.asMap()}" path="codeListForm.code" />
        </c:if>
    </div>

    <div>CDLS0105001</div>
    <div>
        <c:if test="${!empty CL_I18N_PRICE}">
            <form:select id="cdls0105001" items="${CL_I18N_PRICE}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105002</div>
    <div>
        <c:if test="${!empty CL_I18N_PRICE}">
            <form:select id="cdls0105002" items="${CL_I18N_PRICE}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105003</div>
    <div>
        <c:if test="${!empty i18nPriceCodeList}">
            <form:select id="cdls0105003" items="${i18nPriceCodeList}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105004</div>
    <div>
        <c:if test="${!empty i18nPriceCodeList}">
            <form:select id="cdls0105004" items="${i18nPriceCodeList}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105005</div>
    <div>
        <c:if test="${!empty CL_I18N_DB_PRICE}">
            <form:select id="cdls0105005" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105006</div>
    <div>
        <c:if test="${!empty CL_I18N_DB_PRICE}">
            <form:select id="cdls0105006" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105007</div>
    <div>
        <c:if test="${!empty i18nDBPriceCodeList}">
            <form:select id="cdls0105007" items="${i18nDBPriceCodeList}" path="codeListForm.code" />
        </c:if>
    </div>
    <div>CDLS0105008</div>
    <div>
        <c:if test="${!empty i18nDBPriceCodeList}">
            <form:select id="cdls0105008" items="${i18nDBPriceCodeList}" path="codeListForm.code" />
        </c:if>
    </div>

    <div>CDLS0106001</div>
    <div>
        <c:if test="${!empty CL_ORDERSTATUS}">
            <div id="cdls0106001">${f:h(CL_ORDERSTATUS[orderStatusForm.orderStatus])}</div>
        </c:if>
    </div>

    <div>CDLS0107001</div>
    <div>
        <c:if test="${!empty CL_ORDERSTATUS}">
            <form:form action="${pageContext.request.contextPath}/cdls/0107/001?post" method="post" modelAttribute="orderStatusForm">
                <form:select id="cdls0107001" path="orderStatus">
                    <form:options items="${CL_ORDERSTATUS}" />
                </form:select>
                <form:errors id="errorCDLS0107001" path="orderStatus" />
                <button type="submit" id="submitCDLS0107001">Submit Codelist</button>
            </form:form>
        </c:if>
    </div>
    <div>CDLS0107002</div>
    <div>
        <c:if test="${!empty CL_ORDERSTATUS}">
            <form:form action="${pageContext.request.contextPath}/cdls/0107/002?post" method="post" modelAttribute="orderStatusForm">
                <form:select id="cdls0107002" path="orderStatus">
                    <form:option value="nonExistentValue" label="nonExistentValue" />
                </form:select>
                <form:errors id="errorCDLS0107002" path="orderStatus" />
                <button type="submit" id="submitCDLS0107002">Submit Codelist</button>
            </form:form>
        </c:if>
    </div>
</div>
