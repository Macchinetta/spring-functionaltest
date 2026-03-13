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
package jp.co.ntt.fw.spring.functionaltest.config.app.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.CustomException;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.LoggingExchangeFilterFunction;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.SettingHeadersExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient statusWebClient() {
        return WebClient.builder()
                .defaultStatusHandler(HttpStatusCode::isError, response -> {
                    logger.error("DefaultStatusHandler StatusCode:{}", response.statusCode().value());
                    return Mono.error(
                            new CustomException("DefaultStatusHandler StatusCode:" + response.statusCode().value(),
                                    response.headers().toString(), response.statusCode().value()));
                }).build();
    }

    @Bean
    public WebClient errorWebClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs().maxInMemorySize(1 * 1024))
                .build();

        return WebClient.builder().exchangeStrategies(strategies).build();
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

    // イベントループスレッドの説明と共に設定する必要があるため、ガイドラインからは削除したためコメントアウト
    // いずれ、WebFluxにてイベントループスレッドの説明とともに設定方法を示す予定

    // @Bean
    // public WebClient connectionWebClient() {

    //     // @formatter:off
    //     ConnectionProvider connectionProvider = ConnectionProvider.builder(this.connectionName)
    //             .maxConnections(this.maxConnections)
    //             .pendingAcquireMaxCount(this.pendingAcquireMaxCount)
    //             .pendingAcquireTimeout(Duration.ofSeconds(this.pendingAcquireTimeout))
    //             .build();
    //     // @formatter:on

    // HttpClient httpClient = HttpClient.create(connectionProvider);

    // ReactorClientHttpConnector httpConnector = new
    // ReactorClientHttpConnector(httpClient);

    //     // @formatter:off
    //     WebClient webClient = WebClient.builder()
    //             .clientConnector(httpConnector)
    //             .build();
    //     // @formatter:on

    // return webClient;
    // }
}
