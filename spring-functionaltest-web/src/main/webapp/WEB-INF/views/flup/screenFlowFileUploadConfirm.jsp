<div id="wrapper">

    <h1 id="screenTitle">ファイルアップロード(確認)</h1>

    <form:form modelAttribute="screenFlowUploadForm" method="post"
        cssClass="form-horizontal">

        <div class="form-group">
            <label class="col col-md-2 control-label">ファイルID</label>
            <div class="col col-md-10">
                <span id="temporaryFileIdText"
                    class="display-item form-control">${f:h(screenFlowUploadSessionInfo.temporaryFileId)}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col col-md-2 control-label">ファイル名</label>
            <div class="col col-md-10">
                <span id="fileNameText" class="display-item form-control">${f:h(screenFlowUploadForm.fileName)}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col col-md-2 control-label">説明</label>
            <div class="col col-md-10">
                <span id="descriptionText" class="display-item form-control">${f:br(f:h(screenFlowUploadForm.description))}</span>
            </div>
        </div>

        <div class="form-group">
            <div class="col col-md-10 col-md-offset-2">
                <form:hidden path="fileName" />
                <form:hidden path="description" />
                <form:button id="uploadButton" name="upload"
                    class="btn btn-default">
                    <span class="glyphicon glyphicon-upload"></span>アップロード
                </form:button>
                <form:button id="confirmButton" name="redo"
                    class="btn btn-default">
                    <span class="glyphicon glyphicon-arrow-left"></span>戻る</form:button>
            </div>
        </div>

        <form:hidden path="contentType" />

    </form:form>

    <c:if test="${!(empty displayConfirmTemporaryDirectoryButton)}">
        <form
            action="${pageContext.request.contextPath}/flup/temporaryFiles?list"
            method="get" class="form-horizontal">
            <div class="form-group">
                <div class="col col-md-10 col-md-offset-2">
                    <button id="confirmTemporaryDirectory" name="list"
                        class="btn btn-default">
                        <span class="glyphicon glyphicon-folder-open"></span>一時ディレクトリの確認
                    </button>
                </div>
            </div>
        </form>
    </c:if>

</div>
