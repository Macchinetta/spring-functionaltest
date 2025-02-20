<form:form modelAttribute="bookForm" action="${pageContext.request.contextPath}/djpa/book/register">
    <fieldset>
        <legend>書籍登録</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <label>書籍ID</label>
                </div>
                <div class="col-md-4">
                    <form:input path="bookId" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>カテゴリ</label>
                </div>
                <div class="col-md-4">
                    <form:input path="categoryName" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>タイトル</label>
                </div>
                <div class="col-md-4">
                    <form:input path="title" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>書籍コード</label>
                </div>
                <div class="col-md-4">
                    <form:input path="clobCode" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>書籍コード(バイナリ)</label>
                </div>
                <div class="col-md-4">
                    <form:input path="blobCode" class="form-control" value="" readonly="true" style="background-color: whitesmoke" />
                </div>
                <div class="col-md-4" style="padding-top: 0.5em">
                    <a>自動生成</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>価格</label>
                </div>
                <div class="col-md-4">
                    <form:input path="price" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>発売日</label>
                </div>
                <div class="col-md-4">
                    <form:input path="releaseDate" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <ul>
                        <li><form:button name="register" class="btn btn-default">登録</form:button></li>
                        <li><form:button name="errorReg" class="btn btn-default">RollBack Demo</form:button></li>
                        <li><form:button name="cancel" class="btn btn-default">キャンセル</form:button></li>
                        <li><form:button name="crudReg" class="btn btn-default">Crude Repository Verification</form:button></li>
                        <li><form:button name="saveAndFlush" class="btn btn-default">Add using Save And Flush</form:button></li>
                        <li><form:button name="flush" class="btn btn-default">Add using Flush</form:button></li>
                        <li><form:button name="customRepoSave" class="btn btn-default">Add Using Custom Repository</form:button></li>
                        <li><form:button name="noIntfInheriRepoSave" class="btn btn-default">Add Using No Interface Inheritance</form:button></li>
                        <li><form:button name="registerDIV" class="btn btn-default">Register For Data Integrity Violation</form:button></li>
                    </ul>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
