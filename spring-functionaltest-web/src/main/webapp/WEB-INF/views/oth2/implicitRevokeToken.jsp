<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/oth2-implicit.js"></script>
<script type="text/javascript">
"use strict";

$(document).ready(function() {
    jQuery.noConflict(true);
    oauth2Func.clearTokens({"todo" : {}});
});

</script>
<div id="wrapper">
  <h1 id="title">Revoke Token from LocalStorage</h1>
</div>