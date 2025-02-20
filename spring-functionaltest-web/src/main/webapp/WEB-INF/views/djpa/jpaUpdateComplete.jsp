<form:form modelAttribute="bookForm" action="${pageContext.request.contextPath}/djpa/book/update">
    <fieldset>
        <legend>書籍更新</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <label>書籍ID</label>
                </div>
                <div class="col-md-4">
                    <form:input path="bookId" class="form-control" value="${f:h(book.bookId)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>カテゴリ</label>
                </div>
                <div class="col-md-4">
                    <form:input path="categoryName" class="form-control" value="${f:h(book.category.categoryName)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>タイトル</label>
                </div>
                <div class="col-md-4">
                    <form:input path="title" class="form-control" value="${f:h(book.title)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>書籍コード</label>
                </div>
                <div class="col-md-4">
                    <form:input path="clobCode" class="form-control" value="${f:h(book.clobCode)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>書籍コード(バイナリ)</label>
                </div>
                <div class="col-md-4">
                    <form:input path="blobCode" class="form-control" value="${f:h(book.blobCodeHex)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>価格</label>
                </div>
                <div class="col-md-4">
                    <form:input path="price" class="form-control" value="${f:h(book.price)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>発売日</label>
                </div>
                <div class="col-md-4">
                    <fmt:formatDate value="${book.releaseDate}" pattern="yyyy/MM/dd" var="releaseDate" />
                    <form:input path="releaseDate" class="form-control" value="${releaseDate}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>作成者</label>
                </div>
                <div class="col-md-4">
                    <form:input path="createdBy" class="form-control" value="${f:h(book.createdBy)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>作成日時</label>
                </div>
                <div class="col-md-4">
                    <joda:format value="${book.createdDate}" pattern="yyyy/MM/dd" var="crDate" />
                    <form:input path="createdDate" class="form-control" value="${crDate}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>最終更新者</label>
                </div>
                <div class="col-md-4">
                    <form:input path="lastModifiedBy" class="form-control" value="${f:h(book.lastModifiedBy)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>最終更新日時</label>
                </div>
                <div class="col-md-4">
                    <joda:format value="${book.lastModifiedDate}" pattern="yyyy/MM/dd" var="modDate" />
                    <form:input path="lastModifiedDate" class="form-control" value="${modDate}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-11"></div>
                    <div class="col-md-1">
                        <c:choose>
                            <c:when test="${empty pageListFlag && null eq pageListFlag }">
                                <form:button name="back" class="button btn btn-default">戻る</form:button>
                            </c:when>
                            <c:otherwise>
                                <form:button name="toPageList" class="button btn btn-default">戻る</form:button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
