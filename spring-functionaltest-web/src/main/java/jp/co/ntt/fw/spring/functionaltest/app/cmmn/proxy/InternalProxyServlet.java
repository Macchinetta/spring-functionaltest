/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.proxy.ProxyServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アプリケーション内部ProxyServer用のサーブレット
 *
 * <pre>
 * 認証が必要なURLのパスの場合、プロキシ用の認証ヘッダ("Proxy-Authorization")が必要となる。<br>
 * レスポンスヘッダに"Pass-Internal-Proxy"を追加する。
 * </pre>
 */
public class InternalProxyServlet extends ProxyServlet {

    private static final long serialVersionUID = 4051111343160785339L;

    private static final Logger logger = LoggerFactory.getLogger(InternalProxyServlet.class);

    private static String proxyUseAuthPath;

    private static String proxyUserName;

    private static String proxyPassword;

    public static void setProxyUseAuthPath(String proxyUseAuthPath) {
        InternalProxyServlet.proxyUseAuthPath = proxyUseAuthPath;
    }

    public static void setProxyUserName(String proxyUserName) {
        InternalProxyServlet.proxyUserName = proxyUserName;
    }

    public static void setProxyPassword(String proxyPassword) {
        InternalProxyServlet.proxyPassword = proxyPassword;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jetty.proxy.ProxyServlet#service(javax.servlet.http. HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("Start InternalProxyServlet.service");
        logger.debug("RequestURL:" + request.getRequestURL());

        // 認証処理
        if (!authenticate(request, response)) {
            return;
        }

        // javax.servlet.Servlet.service
        super.service(request, response);

        // レスポンスヘッダに"Pass-Internal-Proxy"を追加
        response.addHeader("Pass-Internal-Proxy", "true");

        logger.debug("End InternalProxyServlet.service");
    }

    /**
     * 認証処理
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return true:認証成功 or 認証不要、false：認証失敗 or 認証情報なし
     * @throws IOException
     */
    protected boolean authenticate(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {

        // URIに認証が必要なURLのパスが含まれない場合、認証不要。
        if (!request.getPathInfo().contains(InternalProxyServlet.proxyUseAuthPath)) {
            return true;
        }

        // プロキシ認証情報取得
        final String header = request.getHeader("Proxy-Authorization");

        // 認証情報が未設定 または Basic認証の情報ではない場合、クライアントにプロキシ認証情報を要求。(ステータスコード：407)
        if (header == null || !header.startsWith("Basic ")) {
            response.addHeader("Proxy-Authenticate",
                    "Basic realm=\"" + getClass().getName() + "\"");
            response.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED,
                    "No Authorization By " + getClass().getName());
            return false;
        }

        // 認証ヘッダから認証情報を取得
        String[] credentials = getCredentialsFromHeader(header);

        // 認証情報の判定
        if (credentials != null
                && Objects.equals(credentials[0], InternalProxyServlet.proxyUserName)
                && Objects.equals(credentials[1], InternalProxyServlet.proxyPassword)) {
            // 認証情報が正しい場合、trueを返す。
            return true;
        } else {
            // 認証情報が不正の場合、falseを返す。(ステータスコード：401)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Bad Credentials By " + getClass().getName());
            return false;
        }
    }

    /**
     * 認証ヘッダから認証情報を取得
     *
     * <pre>
     * Proxy-Authorizationヘッダーの値は、Basic認証の場合「Baseic 認証情報」形式。<br>
     * また、認証情報の値は、「ユーザ:パスワード」形式の値をBase64で変換されている。
     * </pre>
     *
     * @param header HTTPリクエストヘッダのProxy-Authorizationの値
     * @return 認証情報(1要素目：ユーザ、2要素目：パスワード)、ｎｕｌｌ：認証情報不正
     * @throws IOException
     */
    private String[] getCredentialsFromHeader(String header) throws IOException {

        // 先頭の"Basic "を除いた文字列を取得
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            // Base64デコード
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        String token = new String(decoded, "UTF-8");

        // 「ユーザ:パスワード」形式を分割
        String[] credentials = token.split(":");

        if (credentials.length != 2) {
            return null;
        }

        return credentials;
    }
}
