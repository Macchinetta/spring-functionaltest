<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.djpa.item" />
    <c:set var="functionId" value="djpa" />

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
            <form:form modelAttribute="orderItemForm" action="${pageContext.request.contextPath}/jsp/order/item/modify" method="post">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>OrderId</th>
                            <td><form:input path="orderId" value="${f:h(orderId)}" readonly="true" style="background-color: whitesmoke" /></td>
                        </tr>
                        <tr>
                            <th>Item Number</th>
                            <td><form:input path="itemNumber" value="${f:h(itemNumber)}" readonly="true" style="background-color: whitesmoke" /></td>
                        </tr>
                        <tr>
                            <th>Item Code</th>
                            <td><form:input path="orderItem.itemCode" value="${f:h(orderItem.itemCode)}" readonly="true" style="background-color: whitesmoke" /></td>
                        </tr>
                        <tr>
                            <th>Item Name</th>
                            <td><form:input path="orderItem.itemName" value="${f:h(orderItem.itemName)}" readonly="true" style="background-color: whitesmoke" /></td>
                        </tr>
                        <tr>
                            <th>Item Price</th>
                            <td><form:input path="orderItem.itemPrice" value="${f:h(orderItem.itemPrice)}" readonly="true" style="background-color: whitesmoke" /></td>
                        </tr>
                        <tr>
                            <th>Quantity to purchase</th>
                            <td><form:input path="quantity" value="${f:h(quantity)}" /></td>
                        </tr>
                        <tr>
                            <th>Item Can be removed</th>
                            <c:choose>
                                <c:when test="${orderItemForm.logicalDelete}">
                                    <c:set var="canDelete" value="Yes"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="canDelete" value="No"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <td id="itStat">${f:h(canDelete)}</td>
                        </tr>
                    </thead>
                </table>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <form:button name="update" class="btn btn-default">Update</form:button>
                        <form:button name="dirRelatedEntityUpdate" class="btn btn-default">Directly Update Related Entity</form:button>
                        <form:button name="delete" class="btn btn-default">Delete Related Entity Using Parent</form:button>
                        <form:button name="dirRelatedEntityDelete" class="btn btn-default">Direct Delete Related Entity</form:button>
                        <form:button name="deleteNoSuccess" class="btn btn-default">Unsuccessful Deletion of Related Entity</form:button>
                    </div>
                </div>
            </form:form>
            <a id="back" href="${pageContext.request.contextPath}/jsp/order">Back to Order List</a>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
