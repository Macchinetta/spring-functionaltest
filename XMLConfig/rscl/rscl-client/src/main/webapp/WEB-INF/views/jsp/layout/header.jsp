<!-- ナビゲーションバー -->
<sec:authentication property="principal" var="principal" />

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a id="springTestTop" class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message code="label.sf.cmmn.systemName" /></a>
        </div>
    </div>
</div>
