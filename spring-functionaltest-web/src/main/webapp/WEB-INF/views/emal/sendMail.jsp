<div id="wrapper">
    <h1 id="screenTitle">メール送信</h1>
    <br />
    <form:form method="POST" action="${pageContext.request.contextPath}/emal/sendmail" modelAttribute="emailSendingForm" enctype="multipart/form-data" cssClass="form-horizontal">
        <fieldset>
            <legend>メール送信</legend>
            <div class="form-group">
                <div class="col col-md-2">To</div>
                <c:forEach items="${emailSendingForm.to}" var="v" varStatus="s">
                    <div class="col col-md-3">
                        <form:input path="to[${s.index}]" cssClass="quantity form-control input-sm" />
                    </div>
                </c:forEach>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Cc</div>
                <c:forEach items="${emailSendingForm.cc}" var="v" varStatus="s">
                    <div class="col col-md-3">
                        <form:input path="cc[${s.index}]" cssClass="quantity form-control input-sm" />
                    </div>
                </c:forEach>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Bcc</div>
                <c:forEach items="${emailSendingForm.bcc}" var="v" varStatus="s">
                    <div class="col col-md-3">
                        <form:input path="bcc[${s.index}]" cssClass="quantity form-control input-sm" />
                    </div>
                </c:forEach>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Reply-To</div>
                <c:if test="${emailSendingForm.testcase == 'allProperties'}">
                    <div class="col col-md-3">
                        <form:input path="replyTo" cssClass="quantity form-control input-sm" />
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Text</div>
                <div class="col col-md-3">
                    <form:input path="text" cssClass="quantity form-control input-sm" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Filename</div>
                <c:if test="${emailSendingForm.testcase == 'attachmentMimeMessage'}">
                    <div class="col col-md-3">
                        <form:input path="filename" cssClass="quantity form-control input-sm" />
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Content-Id</div>
                <c:if test="${emailSendingForm.testcase == 'inlineMimeMessage'}">
                    <div class="col col-md-3">
                        <form:input path="cid" cssClass="quantity form-control input-sm" />
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col col-md-2">MultipartFile</div>
                <c:if
                    test="${emailSendingForm.testcase == 'attachmentMimeMessage' 
                            || emailSendingForm.testcase == 'inlineMimeMessage'}"
                >
                    <div class="col col-md-3">
                        <form:input type="file" path="multipartFile" />
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col col-md-2">Template</div>
                <c:if
                    test="${emailSendingForm.testcase == 'templatedMessage' 
                            || emailSendingForm.testcase == 'preparationException'}"
                >
                    <div class="col col-md-3">
                        <form:select path="templateName">
                            <form:option value="registration-confirmation">registration-confirmation</form:option>
                            <form:option value="non-existence">non-existence</form:option>
                        </form:select>
                    </div>
                </c:if>
            </div>
            <form:hidden path="testcase" />
            <div class="form-group">
                <button id="sendMail" name="sendMail" class="btn btn-default">メール送信</button>
            </div>
        </fieldset>
    </form:form>
    <br />
</div>
