<div id="wrapper">
    <h3 id="screenTitle">プロフィール編集画面</h3>
    
    <br>
    <div id="xMLHttpRequestResult" class="hidden alert">
        <ul>
            <li id="returnStatus"></li>
        </ul>
    </div>
    <div id="resultMessage" class="hidden alert">
    </div>
    
    <form id="committerForm" action="${pageContext.request.contextPath}/cspr/0201">
        <spring:nestedPath path="committerForm">
        <fieldset>
            <legend>プロフィール</legend>
            <sec:authentication property="principal.committer" var="committer" />
            <br>
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="username">ユーザ名</label>
                </div>
                <div class="col col-md-4">
                    <input id="username" readonly="readonly" class="form-control input-sm" value="${f:h(committer.username)}"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col col-md-2">
                    <form:label path="email">Email</form:label>
                </div>
                <div class="col col-md-4">
                    <form:input path="email" cssClass="form-control input-sm" value="${f:h(committer.email)}"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col col-md-2">
                    <form:label path="url">URL</form:label>
                </div>
                <div class="col col-md-4">
                    <form:input path="url" cssClass="form-control input-sm" value="${f:h(committer.url)}"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col col-md-2">
                    <form:label path="company">会社名</form:label>
                </div>
                <div class="col col-md-4">
                    <form:input path="company" class="form-control input-sm" value="${f:h(committer.company)}"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col col-md-2">
                    <form:label path="location">所在地</form:label>
                </div>
                <div class="col col-md-4">
                    <form:input path="location" cssClass="form-control input-sm" value="${f:h(committer.location)}"/>
                </div>
            </div>
            <br><br>
            <div class="form-group col col-md-5">
                <input id="retrunToIndex" name="retrunToIndex" type="submit" class="btn btn-default" value="戻る"/>
                <input id="edit" type="button" name="edit" class="btn btn-default" value="編集" onclick="return cspr.editProfile()"/>
            </div>
        </fieldset>
        </spring:nestedPath>
    </form>
    
    <form:form id="logout" action="${pageContext.request.contextPath}/cspr/logout">
        <div class="text-right">
            <input id="logoutButton" type="submit" name="logout" class="btn btn-default" value="ログアウト"/>
        </div>
    </form:form>
</div>