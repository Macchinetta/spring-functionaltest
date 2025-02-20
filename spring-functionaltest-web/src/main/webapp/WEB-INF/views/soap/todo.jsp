<div id="wrapper">
    <h1 id="screenTitle">show todo</h1>

    <c:if test="${not empty todo}">
        <table id="todo" class="table table-striped table-bordered table-condensed">
            <tr>
                <td>todoId</td>
                <td><span id="todoId">${f:h(todo.todoId)}</span></td>
            </tr>
            <tr>
                <td>title</td>
                <td><span id="title">${f:h(todo.title)}</span></td>
            </tr>
            <tr>
                <td>description</td>
                <td><span id="description">${f:h(todo.description)}</span></td>
            </tr>
            <tr>
                <td>finished</td>
                <td><span id="finished">${f:h(todo.finished)}</span></td>
            </tr>
            <tr>
                <td>createdAt</td>
                <td><span id="createdAt">${f:h(todo.createdAt)}</span></td>
            </tr>
        </table>
    </c:if>
</div>
