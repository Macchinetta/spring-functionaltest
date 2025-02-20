<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">
    <h1 id="screenTitle">ユーザ情報入力</h1>

    <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">
        <div class="form-group">
            <h4>
                <span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
            </h4>

            <div class="form-group">
                <label for="path" class="col-sm-1 control-label">パス：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="path" name="path" placeholder="Path" />
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-1 control-label">ユーザ名：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" placeholder="UserName" />
                </div>
            </div>
            <div class="form-group">
                <label for="age" class="col-sm-1 control-label">年齢：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="age" name="age" placeholder="UserAge" />
                </div>
            </div>
        </div>
        <button type="submit" id="send" class="btn btn-default">send</button>
    </form:form>
</div>
