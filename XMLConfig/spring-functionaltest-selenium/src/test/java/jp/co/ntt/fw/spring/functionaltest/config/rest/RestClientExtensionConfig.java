package jp.co.ntt.fw.spring.functionaltest.config.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientExtensionConfig {

    @Value("${selenium.restOperations.connectTimeout:-1}")
    private int connectTimeout;

    @Value("${selenium.restOperations.readTimeout:-1}")
    private int readTimeout;

    @Bean("restOperations")
    public RestTemplate restTemplate(
            @Qualifier("clientHttpRequestFactory") SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(simpleClientHttpRequestFactory);
        return bean;
    }

    @Bean("clientHttpRequestFactory")
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(connectTimeout);
        bean.setReadTimeout(readTimeout);
        // bean.setConnectTimeout(Duration.ofMillis(connectTimeout));
        // bean.setReadTimeout(Duration.ofMillis(readTimeout));
        return bean;
    }

}
