<div id="wrapper">
    <h1 id="screenTitle">ファイルアップロード(HTML5のmultiple属性)</h1>

    <t:messagesPanel />

    <form:form modelAttribute="multipleUploadForm" method="post" cssClass="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
            <form:label path="multipartFiles" cssClass="col col-md-2 control-label">コンテンツファイル</form:label>
            <div class="col col-md-10">
                <form:input type="file" path="multipartFiles" cssClass="multipartFile" multiple="multiple" />
                <input type="text" readonly="readonly" id="selectedFiles" class="selectedFile form-control" />
                <button id="browseFiles" type="button" class="btn btn-default"><span class="glyphicon glyphicon-folder-open"></span>参照</button>
                <form:errors path="multipartFiles" cssClass="text-danger" element="div" />
            </div>
        </div>

        <div class="form-group">
            <div class="col col-md-10 col-md-offset-2">
                <form:button id="uploadButton" name="upload" class="btn btn-default"> <span class="glyphicon glyphicon-upload"></span>アップロード</form:button>
                <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"> <span class="glyphicon glyphicon-arrow-left"></span>戻る </a>
            </div>
        </div>
    </form:form>
</div>
