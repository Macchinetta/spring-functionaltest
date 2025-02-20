<div id="wrapper">
    <h1 id="screenTitle">仮アップロード時の一時ファイル一覧</h1>

    <div id="messages">
        <t:messagesPanel />
    </div>

    <c:if test="${not empty temporaryFiles}">
        <div>
            <table class="table table-striped table-bordered table-condensed">
                <thead>
                    <tr>
                        <th class="text-center">No</th>
                        <th class="text-center">ファイルID</th>
                        <th class="text-center">サイズ</th>
                        <th class="text-center">アップロード日時</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${temporaryFiles}" var="temporaryFile" varStatus="rowStatus">
                        <tr>
                            <td class="text-center">${f:h(rowStatus.count)}</td>
                            <td>
                                <a target="uploadedTemporaryFile" href="${pageContext.request.contextPath}/uploadedTemporaryFiles/${f:u(temporaryFile.fileId)}"
                                    ><span class="glyphicon glyphicon-new-window"></span>${f:h(temporaryFile.fileId)}</a
                                >
                            </td>
                            <td class="text-right"><fmt:formatNumber value="${temporaryFile.size/1024}" pattern="###,##0.00 KB" /></td>
                            <td class="text-center"><fmt:formatDate value="${temporaryFile.uploadedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <form:form method="post" class="form-horizontal">
        <a href="${pageContext.request.contextPath}/flup/temporaryFiles?list" id="refreshButton" class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span>最新状態 </a>
        <button id="deleteAllButton" name="delete" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span>全ファイル削除</button>
    </form:form>

    <div class="text-center">
        <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"><span class="glyphicon glyphicon-arrow-left"></span>ファイルアップロードのトップへ</a>
    </div>
</div>
