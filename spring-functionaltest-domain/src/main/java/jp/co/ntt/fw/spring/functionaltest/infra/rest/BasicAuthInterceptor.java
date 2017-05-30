/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.codec.Base64;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

    public BasicAuthInterceptor(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    private String userid;

    private String password;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        // BASIC認証用各情報ヘッダを作成。
        String plainCredentials = userid + ":" + password;
        String base64Credentials = new String(Base64.encode(plainCredentials
                .getBytes(StandardCharsets.UTF_8)));
        request.getHeaders().add("Authorization", "Basic " + base64Credentials);

        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }
}
