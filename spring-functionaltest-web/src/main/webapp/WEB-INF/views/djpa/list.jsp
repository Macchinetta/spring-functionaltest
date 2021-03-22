<div class="row">
  <h1 id="screenTitle">書籍一覧</h1>
  <div>
    <form:form modelAttribute="jpaBookListForm"
      action="${pageContext.request.contextPath}/djpa/book/register">
      <form:button name="form" class="btn btn-default">登録</form:button>
    </form:form>
  </div>

  <div>
    <table class="table table-striped table-bordered table-condensed">
      <form:form modelAttribute="jpaBookListForm"
        action="${pageContext.request.contextPath}/djpa/book/delete" method="delete">

        <tr>
          <th class="title">書籍ID</th>
          <th><form:input path="bookIdDelOpnInput" class="form-control form-input"
              value="${f:h(bookListForm.bookIdDiffOpInput)}" /></th>
          <th><form:button name="deletAllInBatch" class="btn btn-default btn-right">Delete All In Batch</form:button></th>
          <th><form:button name="deletInBatch" class="btn btn-default btn-right">Delete In Batch</form:button></th>
          <th><form:button name="deletOne" class="btn btn-default btn-right">Delete One</form:button></th>
          <th><form:button name="deletIterable" class="btn btn-default btn-right">Delete Iterables</form:button></th>
          <th><form:button name="deleteEntity" class="btn btn-default btn-right">Delete Entity</form:button></th>
      </form:form>

      <form:form modelAttribute="jpaBookListForm"
        action="${pageContext.request.contextPath}/djpa/book/existence" method="get">

        <tr>
          <th class="title">書籍ID</th>
          <th align="left"><form:input path="bookIdSrch" class="form-control form-input"
              value="${f:h(bookListForm.bookIdSrch)}" /></th>
          <th><form:button name="check" class="btn btn-default btn-right">Is Book Present</form:button></th>
          <c:choose>
            <c:when test="${isBookPresent }">
              <th id="existResult" style="background-color: green;">Book Available!!</th>
            </c:when>
            <c:when test="${!empty isBookPresent && !isBookPresent }">
              <th id="existResult" style="background-color: red;">Book Not Available!!</th>
            </c:when>
          </c:choose>
        </tr>
        <tr>
          <th><form:button name="count" class="btn btn-default btn-right">Get Book Count</form:button></th>
          <c:choose>
            <c:when test="${!empty bookCount && null ne bookCount }">
              <th>No.of Books :</th>
              <th id="bookQty" style="background-color: grey;">${bookCount }</th>
            </c:when>
          </c:choose>
        <tr>
      </form:form>
    </table>
  </div>

  <br>
  <fieldset>
    <legend>Sorting</legend>
    <table>
      <form:form modelAttribute="jpaBookListForm"
        action="${pageContext.request.contextPath}/djpa/book/sort" method="get">
        <tr>
          <th>List Books in Sorted Order By : <form:select path="searchOrderBy">
              <form:option id="bookIdASC" value="bookId ASC">Book Id. ASC</form:option>
              <form:option id="bookIdDESC" value="bookId DESC">Book Id. DESC</form:option>
              <form:option id="titleASC" value="title ASC">Title. ASC</form:option>
              <form:option id="titleDESC" value="title DESC">Title. DESC</form:option>
            </form:select>
          </th>
          <th><form:button name="sort" class="btn btn-default">Sort Books</form:button></th>
        </tr>
      </form:form>
    </table>
  </fieldset>
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
    <c:forEach var="book" items="${bookList}" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${rowStatus.count}</td>
        <td class="bookId"><a id="bookLink_${rowStatus.count}"
          href="${pageContext.request.contextPath}/djpa/book/${book.bookId}/update">
            ${f:h(book.bookId)}</a></td>
        <td class="categoryName" id="categoryName">${f:h(book.category.categoryName)}</td>
        <td class="title" id="title">${f:h(book.title)}</td>
        <td class="clobCode" id="clobCode">${f:h(book.clobCode)}</td>
        <td class="blobCode" id="blobCode">${f:h(book.blobCodeHex)}</td>
        <td class="price" id="price"><fmt:formatNumber value="${book.price}" pattern="###,###" /></td>
        <td class="releaseDate" id="releaseDate"><fmt:formatDate value="${book.releaseDate}"
            pattern="yyyy/MM/dd" /></td>
      </tr>
    </c:forEach>

  </table>

  <form:form modelAttribute="jpaBookListForm"
    action="${pageContext.request.contextPath}/djpa/book/interceptSrch">
    <fieldset>
      <legend>Lazy Fetching Using Interceptor</legend>
      <table>
        <tr>
          <th class="title">書籍ID</th>
          <th><form:input path="searchInQueryBookIdIntercept" class="form-control form-input"
              value="${f:h(bookListForm.searchInQueryBookIdIntercept)}" /></th>
          <th><form:button name="lazyLoadIntercept" class="btn btn-default btn-right">Fetch</form:button></th>
      </table>
    </fieldset>
  </form:form>
  <br>
  <form:form modelAttribute="jpaBookListForm"
    action="${pageContext.request.contextPath}/djpa/book/filterSrch">
    <fieldset>
      <legend>Lazy Fetching Using Filter</legend>
      <table>
        <tr>
          <th class="title">書籍ID</th>
          <th><form:input path="searchInQueryBookIdFilter" class="form-control form-input"
              value="${f:h(bookListForm.searchInQueryBookIdFilter)}" /></th>
          <th><form:button name="lazyLoadFilter" class="btn btn-default btn-right">Fetch</form:button></th>
      </table>
    </fieldset>
  </form:form>
  <br>
  <form:form modelAttribute="jpaBookListForm"
    action="${pageContext.request.contextPath}/djpa/book/noLazySetting">
    <fieldset>
      <legend>Lazy Initialization Exception</legend>
      <table>
        <tr>
          <th class="title">書籍ID</th>
          <th><form:input path="searchInQueryBookIdNoLazy" class="form-control form-input"
              value="${f:h(bookListForm.searchInQueryBookIdNoLazy)}" /></th>
          <th><form:button name="noLazy" class="btn btn-default btn-right">Fetch</form:button></th>
      </table>
    </fieldset>
  </form:form>
  <br>
  <form:form modelAttribute="jpaBookListForm"
    action="${pageContext.request.contextPath}/djpa/book/register" method="GET">
    <table>
      <tr>
        <th class="title">書籍ID</th>
        <th><form:input path="bookId" class="form-control form-input"
            value="${f:h(bookListForm.bookId)}" /></th>
        <th><form:button name="egComplete" class="btn btn-default btn-right">CrudRepository Interface Default Fetch</form:button></th>
        <th><form:button name="find" class="btn btn-default btn-right">Find One Using JPARepository</form:button></th>
    </table>
  </form:form>
  <hr>
  <a id="djpaLink" href="${pageContext.request.contextPath}/djpa/">Back to [DJPA] JPA
    Confirmation</a>



  <hr />



</div>
