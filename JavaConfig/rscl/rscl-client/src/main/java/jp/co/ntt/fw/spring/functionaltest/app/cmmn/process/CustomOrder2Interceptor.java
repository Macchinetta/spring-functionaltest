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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * <ul>
 * インターセプターの実行順序の挙動テスト
 * </ul>
 */
public class CustomOrder2Interceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        initOrderList(request);

        putMessage(request, "CustomOrder2Interceptor Start!");
        ClientHttpResponse response = execution.execute(request, body);
        putMessage(request, "CustomOrder2Interceptor End!");

        return response;
    }

    private void initOrderList(HttpRequest request) {
        if (request.getAttributes().get("orderList") == null) {
            List<String> orderList = new ArrayList<>();
            request.getAttributes().put("orderList", orderList);
        }
    }

    private void putMessage(HttpRequest request, String message) {
        @SuppressWarnings("unchecked")
        List<String> orderList = (List<String>) request.getAttributes().get("orderList");
        orderList.add(message);
        request.getAttributes().put("orderList", orderList);
    }
}
