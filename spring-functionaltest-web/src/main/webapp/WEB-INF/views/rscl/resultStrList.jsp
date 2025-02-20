<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">
    <h1 id="screenTitle">RESTクライアント処理結果【文字リスト出力】</h1>
    <c:if test="${!empty f:h(resultDescription)}">
        <h4>
            <span id="resultDescription"> 【&nbsp;${f:h(resultDescription)}&nbsp;】 </span>
        </h4>
    </c:if>
    <c:if test="${f:h(strList) != null && fn:length(strList) != 0}">
        <div>
            ListSize: <span id="strListSize">${fn:length(strList)}</span>
            <hr />
        </div>
        <table class="table table-striped table-bordered table-condensed pgnttable">
            <thead>
                <tr>
                    <th>Content</th>
                </tr>
            </thead>
            <c:forEach var="str" items="${strList}" varStatus="status">
                <tr>
                    <td><span id="str${status.count}">${str}</span></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
