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
