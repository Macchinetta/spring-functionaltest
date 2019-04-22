<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/oth2-implicit.js"></script>
<script type="text/javascript">
"use strict";

$(document).ready(function() {
    var result = oauth2Func.initialize({
        "todo" : {
            clientId : "${clientId}",
            redirectUrl : "${oauth2ClientUrl}/11/01/006/get",
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
        },true).done(function(data) {
            $("#message").text(JSON.stringify(data));
        }).fail(function(data) {
            oauth2Func.parseFailureJSON("todo", data);
        });
    }
});

</script>
<div id="wrapper">
  <h1 id="title">implicit grant(scope:READ / send after timeout expired)</h1>
  <p id="message"></p>
</div>