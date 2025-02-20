<div id="wrapper">
    <h3 id="screenTitle">結果画面</h3>

    <br />
    <a id="spsc0101001" href="${pageContext.request.contextPath}/spsc/">go to SPSC index</a> <br />
    <a id="spsc0501005" href="https://${connectIp}:${connectHttpsPort}/spring-functionaltest-web/spsc/0501/005">HTTPS Access</a>

    <br />
    <img src="${pageContext.request.contextPath}/resources/image/Duke.png" width="128" height="128" title="display image with relative path" />

    <br />
    <img src="http://${connectIp}:${connectHttpPort}/spring-functionaltest-web/resources/image/Duke.png" width="128" height="128" title="display image with http" />

    <br />
    <img src="https://${connectIp}:${connectHttpsPort}/spring-functionaltest-web/resources/image/Duke.png" width="128" height="128" title="display image with https" />

    <br />
    <img src="http://${connectIp}:${connectHttpsPort}/spring-functionaltest-web/resources/image/Duke.png" width="128" height="128" title="display image by CSP Header derective" />
</div>
