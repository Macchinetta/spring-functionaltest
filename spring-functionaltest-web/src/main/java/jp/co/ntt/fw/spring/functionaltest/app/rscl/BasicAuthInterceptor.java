/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.codec.Base64;

/**
 * Basic認証用情報設定インターセプタ
 */
public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(BasicAuthInterceptor.class);

    @Value("${rscl.basicAuth.userid}")
    String userid;

    @Value("${rscl.basicAuth.password}")
    String password;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        // BASIC認証用各情報ヘッダを作成。
        String plainCredentials = userid + ":" + password;
        String base64Credentials = new String(Base64.encode(plainCredentials
                .getBytes(StandardCharsets.UTF_8)));
        request.getHeaders().add("Authorization", "Basic " + base64Credentials);

        logger.info("BasicAuthInterceptor Called!");

        // 次の処理を起動
        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }

}
