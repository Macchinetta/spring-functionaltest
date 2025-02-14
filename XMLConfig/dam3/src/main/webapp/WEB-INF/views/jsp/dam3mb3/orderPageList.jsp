<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.dam3mb3.orderPageList" />
    <c:set var="functionId" value="dam3mb3" />

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <meta name="contextPath" content="${pageContext.request.contextPath}" />
        <sec:csrfMetaTags />
        <title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div class="container">
            <div class="row">
                <div>
                    <a id="registOrderMB3" href="${pageContext.request.contextPath}/jsp/ordermb3/register">登録</a>
                    / <a id="registAllOrderMB3" href="${pageContext.request.contextPath}/jsp/ordermb3/registerExecuteAll">一括登録</a> /
                    <a id="listPageMyBatis3" href="${pageContext.request.contextPath}/jsp/ordermb3/listPageMyBatis3">注文一覧(ページャ付き)</a> /
                    <a id="listPageMyBatis3Scroll" href="${pageContext.request.contextPath}/jsp/ordermb3/listPageMyBatis3Scroll">注文一覧(ページャ付き/SCROLL_INSENSITIVE)</a><br />
                    <a id="historyList" href="${pageContext.request.contextPath}/jsp/orderhistorymb3/list">更新履歴</a><br />
                </div>
                <h1 id="screenTitle">注文一覧</h1>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>注文ID</th>
                            <th>ステータス</th>
                            <th>ステータス名</th>
                            <th>数量</th>
                            <th>商品コード</th>
                            <th>商品名</th>
                            <th>値段</th>
                            <th>カテゴリコード</th>
                            <th>カテゴリ名</th>
                            <th>備考</th>
                        </tr>
                    </thead>
                    <c:forEach items="${page.content}" var="order" varStatus="rowStatus">
                        <tr>
                            <td id="orderId_${f:h(order.id)}"><a id="id${f:h(order.id)}" href="${pageContext.request.contextPath}/jsp/ordermb3/detail/${f:h(order.id)}">${f:h(order.id)}</a></td>
                            <td id="statusCode_${f:h(order.id)}">
                                <a id="idStatus${f:h(order.id)}" href="${pageContext.request.contextPath}/jsp/ordermb3/detailCondSts/${f:h(order.id)}/${f:h(order.orderStatus.code)}"
                                    >${f:h(order.orderStatus.code)}</a
                                >
                            </td>
                            <td id="statusName_${f:h(order.id)}">${f:h(order.orderStatus.name)}</td>

                            <td id="quantity_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item">
                                    <fmt:formatNumber value="${item.quantity}" pattern="###,###" />
                                    <br />
                                </c:forEach>
                            </td>
                            <td id="itemCode_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item"> ${f:h(item.item.code)}<br /> </c:forEach>
                            </td>
                            <td id="itemName_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item"> ${f:h(item.item.name)}<br /> </c:forEach>
                            </td>

                            <td id="itemPrice_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item">
                                    <fmt:formatNumber value="${item.item.price}" pattern="###,###" />
                                    <br />
                                </c:forEach>
                            </td>
                            <td id="categoryCode_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item">
                                    <c:forEach items="${item.item.categories}" var="category"> ${f:h(category.code)}<br /> </c:forEach>
                                </c:forEach>
                            </td>
                            <td id="categoryName_${f:h(order.id)}">
                                <c:forEach items="${order.orderItems}" var="item">
                                    <c:forEach items="${item.item.categories}" var="category"> ${f:h(category.name)}<br /> </c:forEach>
                                </c:forEach>
                            </td>
                            <td id="memo_${f:h(order.id)}">${f:h(order.memo)}</td>
                        </tr>
                    </c:forEach>
                </table>

                <form:form modelAttribute="orderMB3Form" action="${pageContext.request.contextPath}/jsp/ordermb3/showList" method="get">
                    <div class="paginationPart" align="center">
                        <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination" queryTmpl="page={page}&size={size}" criteriaQuery="${f:query(orderMB3Form)}" />
                    </div>
                    <div>
                        <table class="table-striped table-bordered table-condensed">
                            <tr>
                                <th>Choose the Item code</th>
                            </tr>
                            <c:forEach items="${searchItemCode}" var="searchItemCode" varStatus="rowStatus">
                                <tr>
                                    <td><form:checkbox id="checkbox_${searchItemCode.code}" path="searchItemCode" value="${searchItemCode.code}" label="${searchItemCode.code}" /></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><form:button name="itemCodeSearch" class="btn btn-default">Show the orders that contains the item code</form:button></td>
                            </tr>
                        </table>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
