<div id="wrapper">
  <div>Login to Keycloak using: demo/demo</div>
  <br/>
  <ul class="list-group">
    <li class="list-group-item">
      <div>InterceptUrl</div>
      <br/>
      <!-- すべてのスコープを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_ALL</div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=CREATE&amp;pattern=intercept">CREATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=DELETE&amp;pattern=intercept">DELETE</a>
        </li>
      </ul>

      <!-- READ及びDELETEのみスコープに設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_PARTIAL - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=CREATE&amp;pattern=intercept">CREATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=DELETE&amp;pattern=intercept">DELETE</a>
        </li>
      </ul>
      
      <!-- READスコープのみを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=GET&amp;pattern=intercept">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=UPDATE&amp;pattern=intercept">UPDATE</a>
        </li>
      </ul>
      
      <!-- 認可サーバに登録されていないクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - WRONG_SCOPE - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_wrong&amp;scope=GET&amp;pattern=intercept">GET</a>
        </li>
      </ul>
    </li>
  </ul>
  <ul class="list-group">
    <li class="list-group-item">
      <div>PreAuthorize</div>
      <br/>
      <!-- すべてのスコープを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_ALL</div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=CREATE&amp;pattern=annotation">CREATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_all&amp;scope=DELETE&amp;pattern=annotation">DELETE</a>
        </li>
      </ul>

      <!-- READ及びDELETEのみスコープに設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_PARTIAL - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"> <a 
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=CREATE&amp;pattern=annotation">CREATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_partial&amp;scope=DELETE&amp;pattern=annotation">DELETE</a>
        </li>
      </ul>
      
      <!-- READスコープのみを設定できるクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=GET&amp;pattern=annotation">GET</a> <a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_read&amp;scope=UPDATE&amp;pattern=annotation">UPDATE</a>
        </li>
      </ul>
      
      <!-- 認可サーバに登録されていないクライアントに対する処理 -->
      <div>Authorization Code Grant - SCOPE_READ - WRONG_SCOPE - InterceptUrl </div>
      <ul class="list-group">
        <li class="list-group-item"><a
          href="${pageContext.request.contextPath}/o2sp/resource/authorize?grant_type=authorization_code&amp;registrationId=registration_wrong&amp;scope=GET&amp;pattern=annotation">GET</a>
        </li>
      </ul>
    </li>
  </ul>

  <p>=======================================================================</p>
  <div>
    <c:if test="${not empty message}">
      <h2>RESULT : ${message}</h2>
      
      <h2>Access Token</h2>
      <p>Access Token Issued : ${accessTokenIssued}</p>
      <p>Access Token Expires : ${accessTokenExpires}</p>
      <p>Access Token Scopes : ${accessTokenScopes}</p>
      <p>Access Token Type : ${accessTokenType}</p>
      <p>Access Token Value : ${accessTokeValue}</p>
      
      <h2>Refresh Token</h2>
      <p>Refresh Token Issued : ${refreshTokenIssued}</p>
      <p>Refresh Token Expires : ${refreshTokenExpires}</p>
      <p>Refresh Token Value : ${refreshTokeValue}</p>
    </c:if>
  </div>
  <p>=======================================================================</p>

</div>