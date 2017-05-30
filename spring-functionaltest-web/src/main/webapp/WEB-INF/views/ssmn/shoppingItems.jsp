<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="container">
    <h1 id="screenTitle">商品一覧</h1>
    <div id="cartLink">
        <a id="confirmCartLink"
            href="./cart"
            class="btn btn-info"><span
            class="glyphicon glyphicon-shopping-cart"></span> カートを確認</a>
    </div>
    <c:choose>
        <c:when test="${page != null && page.totalPages != 0}">

            <table
                class="table table-striped table-bordered table-condensed">

                <thead>
                    <tr>
                        <th class="no">No</th>
                        <th class="itemId">商品番号</th>
                        <th class="itemName">商品名</th>
                        <th class="overviewDescription">商品概要</th>
                        <th class="price">価格</th>
                    </tr>
                </thead>

                <c:forEach var="item" items="${page.content}"
                    varStatus="rowStatus">
                    <tr>
                        <td class="no">${(page.number * page.size)
                            + rowStatus.count}</td>
                        <td class="itemId"><a
                            id="itemLink_${rowStatus.count}"
                            href="./items/${item.itemId}">
                                ${f:h(item.itemId)}</a></td>
                        <td class="itemName">${f:h(item.itemName)}</td>
                        <td class="overviewDescription">${f:h(item.overviewDescription)}</td>
                        <td class="price"><fmt:formatNumber
                                value="${item.price}" pattern="###,###" /></td>
                    </tr>
                </c:forEach>

            </table>

            <div class="paginationPart">
                <t:pagination page="${page}"
                    disabledHref="javascript:void(0);"
                    outerElementClass="pagination" />
            </div>
        </c:when>

        <c:otherwise>
            <spring:message code="label.sf.ssmn.0001" />
        </c:otherwise>

    </c:choose>
</div>
