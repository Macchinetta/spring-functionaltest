<div id="wrapper">
    <h1 id="screenTitle">show todos</h1>

    <c:if test="${not empty todos}">
        <table id="todos" class="table table-striped table-bordered table-condensed">
            <tr>
                <th>todoId</th>
                <th>title</th>
                <th>description</th>
                <th>finished</th>
                <th>createdAt</th>
            </tr>
            <c:forEach var="todo" items="${todos}" varStatus="status">
                <tr>
                    <td><span id="todoId${status.count}">${todo.todoId}</span></td>
                    <td><span id="title${status.count}">${todo.title}</span></td>
                    <td><span id="description${status.count}">${todo.description}</span></td>
                    <td><span id="finished${status.count}">${todo.finished}</span></td>
                    <td><span id="createdAt${status.count}">${todo.createdAt}</span></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
