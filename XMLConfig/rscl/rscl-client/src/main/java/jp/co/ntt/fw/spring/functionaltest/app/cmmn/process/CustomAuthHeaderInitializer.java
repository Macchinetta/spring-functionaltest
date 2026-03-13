/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.process;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInitializer;

/**
 * 共通認証ヘッダの設定
 */
public class CustomAuthHeaderInitializer implements ClientHttpRequestInitializer {

    private String username;

    private String password;

    public CustomAuthHeaderInitializer(String basicAuthUsername, String basicAuthPassword) {
        this.username = basicAuthUsername;
        this.password = basicAuthPassword;
    }

    @Override
    public void initialize(ClientHttpRequest request) {
        String plainCredentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder()
                .encodeToString(plainCredentials.getBytes(StandardCharsets.UTF_8));
        request.getHeaders().add("Authorization", "Basic " + base64Credentials);
    }
}