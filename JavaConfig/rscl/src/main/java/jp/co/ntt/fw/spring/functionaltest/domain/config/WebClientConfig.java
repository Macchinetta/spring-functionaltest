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
package jp.co.ntt.fw.spring.functionaltest.domain.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import jp.co.ntt.fw.spring.functionaltest.domain.service.wbcl.LoggingExchangeFilterFunction;
import jp.co.ntt.fw.spring.functionaltest.domain.service.wbcl.SettingHeadersExchangeFilterFunction;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    @Value("${connectionWebClient.connectionName:ConnectionWebClient}")
    String connectionName;

    @Value("${connectionWebClient.maxConnections:10}")
    int maxConnections;

    @Value("${connectionWebClient.pendingAcquireMaxCount:10}")
    int pendingAcquireMaxCount;

    @Value("${connectionWebClient.pendingAcquireTimeout:30}")
    long pendingAcquireTimeout;

    @Bean
    public WebClient defaultWebClient() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient connectionWebClient() {

        // @formatter:off
        ConnectionProvider connectionProvider = ConnectionProvider.builder(this.connectionName)
                .maxConnections(this.maxConnections)
                .pendingAcquireMaxCount(this.pendingAcquireMaxCount)
                .pendingAcquireTimeout(Duration.ofSeconds(this.pendingAcquireTimeout))
                .build();
        // @formatter:on

        HttpClient httpClient = HttpClient.create(connectionProvider);

        ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        // @formatter:off
        WebClient webClient = WebClient.builder()
                .clientConnector(httpConnector)
                .build();
        // @formatter:on

        return webClient;
    }

    @Bean
    public WebClient loggingWebClient() {

        // @formatter:off
        WebClient webClient = WebClient.builder()
                .filter(new LoggingExchangeFilterFunction())
                .build();
        // @formatter:on

        return webClient;
    }

    @Bean
    public WebClient filtersWebClient() {

        // @formatter:off
        WebClient webClient = WebClient.builder().filters(f -> {
            f.add(0, new LoggingExchangeFilterFunction());
            f.add(1, new SettingHeadersExchangeFilterFunction());
        }).build();
        // @formatter:on

        return webClient;
    }

}
