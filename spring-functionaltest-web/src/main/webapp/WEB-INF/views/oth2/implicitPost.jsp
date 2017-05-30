<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/oth2-implicit.js"></script>
<script type="text/javascript">
"use strict";

$(document).ready(function() {
    jQuery.noConflict(true);
    todolist();
});

var todolist = function() {
    var result = oauth2Func.initialize({
        "todo" : {
            clientId : "${clientId}",
            redirectUrl : "${oauth2ClientUrl}/01/02/002/post",
            errRedirectUrl : "${oauth2ApplicationContextUrl}/oauth/error",
            authorization : "${oauth2AuthServerUrl}/oauth/authorize"
        }
    });

    if (result) {
        oauth2Func.oajax({
            url : "${oauth2ResourceServerUrl}/principal/123",
            providerId : "todo", 
            scopes : [ "CREATE" ], 
            dataType : "json",
            type : "POST",
            success : function(data) {
                $("#message").text(JSON.stringify(data));
            },
            error : function() {
                oauth2Func.clearTokens();
            }
        });
    } else {
        oauth2Func.clearTokens();
    }
};
</script>
<div id="wrapper">
    <h1 id="title">implicit grant</h1>
    <p id="message"></p>
</div>