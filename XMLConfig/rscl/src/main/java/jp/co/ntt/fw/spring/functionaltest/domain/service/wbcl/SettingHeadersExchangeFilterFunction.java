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
package jp.co.ntt.fw.spring.functionaltest.domain.service.wbcl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class SettingHeadersExchangeFilterFunction implements ExchangeFilterFunction {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SettingHeadersExchangeFilterFunction.class);

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {

        LOGGER.info("execute filter SettingHeadersExchangeFilterFunction.");

        ClientRequest newRequest =
                ClientRequest.from(request).header("test_header", "test value").build();

        return next.exchange(newRequest);

    }

}
