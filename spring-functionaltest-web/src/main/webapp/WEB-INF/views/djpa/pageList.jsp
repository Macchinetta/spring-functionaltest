<div class="row">
    <h1 id="screenTitle">書籍一覧</h1>
    <div>
        <form:form modelAttribute="jpaBookListForm" action="${pageContext.request.contextPath}/djpa/book/register">
            <form:button name="form" class="btn btn-default">登録</form:button>
            <form:button name="multiSave" class="btn btn-default">Multi Save</form:button>
        </form:form>
    </div>
    <div>
        <table class="table table-striped table-bordered table-condensed">
            <form:form modelAttribute="jpaBookListForm" action="${pageContext.request.contextPath}/djpa/book/existence" method="get">
                <tr>
                    <th class="title">書籍ID</th>
                    <th align="left"><form:input path="bookIdSrch" class="form-control form-input" value="${f:h(bookListForm.bookIdSrch)}" /></th>
                    <th><form:button name="customRepoSearch" class="btn btn-default btn-right">Search Book Using Custom Repo</form:button></th>
                    <th><form:button name="noIntfInheriRepoSrch" class="btn btn-default btn-right">Search Book Using No Inherited Repository Interface</form:button></th>
                </tr>
                <tr>
                    <th class="title">Sleep Time</th>
                    <th><form:input path="sleepTime" class="form-control form-input" value="${f:h(bookListForm.sleepTime)}" /></th>
                    <th><form:button name="lckTmeOutQHint" class="btn btn-default btn-right">Lock TimeOut : Query Hint</form:button></th>
                    <th><form:button name="lckTmeOutQHintNoExcp" class="btn btn-default btn-right">Lock TimeOut : Query Hint : No Excp</form:button></th>
                </tr>
                <tr>
                    <th class="title">タイトル</th>
                    <th align="left"><form:input path="bookTitle" class="form-control form-input" value="${f:h(jpaBookListForm.bookTitle)}" /></th>
                    <th><form:button name="noPrimaryKeySearch" class="btn btn-default btn-right">Search Book Using value other than Primary Key</form:button></th>
                </tr>
            </form:form>
        </table>
    </div>
    <br />
    <div>
        <fmt:formatNumber value="${page.totalElements}" />
        Books Available!!
    </div>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th class="no">No</th>
                <th class="bookId">書籍ID</th>
                <th class="categoryName">カテゴリ</th>
                <th class="title">タイトル</th>
                <th class="clobCode">書式コード</th>
                <th class="blobCode">書式コード(バイナリ)</th>
                <th class="price">価格</th>
                <th class="releaseDate">発売日</th>
            </tr>
        </thead>

        <c:forEach var="book" items="${page.content}" varStatus="rowStatus">
            <tr>
                <td class="no" id="no">${(page.number * page.size) + rowStatus.count}</td>
                <td class="bookId"><a id="bookLink_${rowStatus.count}" href="${pageContext.request.contextPath}/djpa/book/${book.bookId}/pgUpdate"> ${f:h(book.bookId)}</a></td>
                <td class="categoryName" id="categoryName">${f:h(book.category.categoryName)}</td>
                <td class="title" id="title">${f:h(book.title)}</td>
                <td class="clobCode" id="clobCode">${f:h(book.clobCode)}</td>
                <td class="blobCode" id="blobCode">${f:h(book.blobCodeHex)}</td>
                <td class="price" id="price"><fmt:formatNumber value="${book.price}" pattern="###,###" /></td>
                <td class="releaseDate" id="releaseDate"><fmt:formatDate value="${book.releaseDate}" pattern="yyyy/MM/dd" /></td>
            </tr>
        </c:forEach>
    </table>
    <div class="paginationPart" align="center">
        <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination" queryTmpl="page={page}&size={size}&${f:query(bookListForm)}" />
    </div>

    <hr />
    <a id="djpaLink" href="${pageContext.request.contextPath}/djpa/">Back to [DJPA] JPA Confirmation</a>

    <hr />
</div>
