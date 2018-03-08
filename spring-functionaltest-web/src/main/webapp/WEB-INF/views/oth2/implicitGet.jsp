<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/oth2-implicit.js"></script>
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
            redirectUrl : "${oauth2ClientUrl}/01/02/001/get",
            errRedirectUrl : "${oauth2ApplicationContextUrl}/oauth/error",
            authorization : "${oauth2AuthServerUrl}/oauth/authorize"
        }
    });

    if (result) {
        oauth2Func.oajax({
            url : "${oauth2ResourceServerUrl}/principal/123",
            providerId : "todo", 
            scopes : [ "READ" ], 
            dataType : "json",
            type : "GET",
        },false).done(function(data) {
            $("#message").text(JSON.stringify(data));
            var token = oauth2Func.getTokens("todo");
            $("#token").text(token[0].access_token);
        }).fail(function(data) {
            oauth2Func.parseFailureJSON("todo", data);
        });
    }
};

</script>
<div id="wrapper">
  <h1 id="title">implicit grant(scope:READ)</h1>
  <p id="message"></p>
  <p id="token"></p>
</div>