<form:form modelAttribute="todoForm" action="${pageContext.request.contextPath}/dam3/todo/register">
    <fieldset>
        <legend>Todo Registration</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <label>Todo ID</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoId" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Category Name</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoCategory" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Todo Title</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoTitle" class="form-control" value="" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form:button name="register" class="btn btn-default">Register</form:button>
                    <form:button name="registerRollback" class="btn btn-default">Register: TRN Rollback</form:button>
                    <form:button name="registerForNull" class="btn btn-default">Register : Null Property Check</form:button>
                    <form:button name="registerAndRetBool" class="btn btn-default">Register : Return boolean</form:button>
                    <form:button name="registerAndRetInt" class="btn btn-default">Register : Return int</form:button>
                    <form:button name="autoIdGenRegister" class="btn btn-default">Register : Using Auto Todo ID Generation</form:button>
                    <form:button name="cancel" class="btn btn-default">Cancel</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
