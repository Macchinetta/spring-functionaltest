<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.ssmn.shoppingOrderConfirm" />
    <c:set var="functionId" value="ssmn" />

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
            <!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
            <div id="wrapper">
                <h1 id="screenTitle">注文確認</h1>

                <spring:eval var="cart" expression="@cart" />

                <form action="./order" method="post">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th class="no">No</th>
                                <th class="itemId">商品番号</th>
                                <th class="itemName">商品名</th>
                                <th class="quantity">個数</th>
                                <th class="price">価格</th>
                            </tr>
                        </thead>

                        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="rowStatus">
                            <tr>
                                <td class="no">${rowStatus.count}</td>
                                <td class="itemId">${f:h(cartItem.itemId)}</td>
                                <td class="itemName">${f:h(cartItem.item.itemName)}</td>
                                <td class="quantity">${f:h(cartItem.quantity)}</td>
                                <td class="price"><fmt:formatNumber value="${cartItem.subtotalPrice}" pattern="###,###" /></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td class="buttonArea" colspan="4"><a id="confirmCartLink" href="./cart" class="btn btn-default">カートへ戻る</a> <button class="btn btn-default">注文確定</button></td>
                            <td class="totalPrice"><fmt:formatNumber value="${cart.totalPrice}" pattern="###,###" /></td>
                        </tr>
                    </table>
                    <t:transaction />
                    <input type="hidden" name="${f:h(_csrf.parameterName)}" value="${f:h(_csrf.token)}" />
                </form>
                <!-- セッション内Form確認 -->
                <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['scopedTarget.cart'].getClass().getName())}</div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
