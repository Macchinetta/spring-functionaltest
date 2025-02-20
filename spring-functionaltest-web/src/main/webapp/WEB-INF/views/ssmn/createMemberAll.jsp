<div id="wrapper">
    <h1 id="screenTitle">会員情報登録</h1>
    <br />
    <!-- actionを指定しないと遷移されたときのURLが使われる -->
    <form:form cssClass="form-horizontal" method="post" modelAttribute="memberForm">
        <fieldset>
            <legend>個人情報</legend>
            <div class="form-group">
                <div class="col col-md-2">氏名（姓）</div>
                <div class="col col-md-3">
                    <form:input path="firstName" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="firstName" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">氏名（名）</div>
                <div class="col col-md-3">
                    <form:input path="lastName" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="lastName" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">氏名カナ（姓）</div>
                <div class="col col-md-3">
                    <form:input path="firstNameKana" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="firstNameKana" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">氏名カナ（名）</div>
                <div class="col col-md-3">
                    <form:input path="lastNameKana" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="lastNameKana" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">年齢</div>
                <div class="col col-md-3">
                    <form:input path="age" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="age" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">性別</div>
                <div class="col col-md-3">
                    <form:select path="gender" items="${CL_GENDER}" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">郵便番号</div>
                <div class="col col-md-3">
                    <form:input path="zipCode" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="zipCode" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">都道府県</div>
                <div class="col col-md-3">
                    <form:input path="state" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="state" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">市区町村</div>
                <div class="col col-md-3">
                    <form:input path="city" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="city" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">番地建物</div>
                <div class="col col-md-3">
                    <form:input path="address" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="address" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">職業</div>
                <div class="col col-md-3">
                    <form:input path="occupation" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="occupation" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">メールアドレス</div>
                <div class="col col-md-3">
                    <form:input path="mailAddress" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-3">
                    <form:errors path="mailAddress" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <a id="menu" href="${pageContext.request.contextPath}/ssmn/" class="btn btn-default">メニューに戻る</a>
                <form:button name="confirm" class="btn btn-default">確認</form:button>
            </div>
        </fieldset>
    </form:form>
</div>
