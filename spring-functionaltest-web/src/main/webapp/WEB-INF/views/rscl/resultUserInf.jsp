<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">
    <h1 id="screenTitle">RESTクライアント処理結果【ユーザ情報出力】</h1>
    <t:messagesPanel />
    <c:if test="${!empty f:h(resultDescription)}">
        <h4>
            <span id="resultDescription"> 【&nbsp;${f:h(resultDescription)}&nbsp;】 </span>
        </h4>
    </c:if>
    <table class="table table-striped table-bordered table-condensed pgnttable">
        <thead>
            <tr>
                <th>Name</th>
                <th>Age</th>
            </tr>
        </thead>
        <tr>
            <td><span id="userName">${f:h(user.name)}</span></td>
            <td><span id="userAge">${f:h(user.age)}</span></td>
        </tr>
    </table>
</div>
