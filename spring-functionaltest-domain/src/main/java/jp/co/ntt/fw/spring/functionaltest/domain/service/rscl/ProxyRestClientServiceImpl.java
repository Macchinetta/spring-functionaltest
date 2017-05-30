/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.net.URI;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class ProxyRestClientServiceImpl implements ProxyRestClientService {

    private static final Logger logger = LoggerFactory
            .getLogger(RestClientServiceImpl.class);

    @Inject
    RestTemplate proxyAuthRestTemplate;

    @Inject
    RestTemplate proxyRestTemplate;

    @Inject
    RestTemplate simpleClientRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public UserResource confirmProxy01(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.proxyAuthRestTemplate
                .getForEntity(targetUri, UserResource.class);

        logger.info(
                "RSCL1401001 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    @Override
    public UserResource confirmProxy02(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.proxyRestTemplate.getForEntity(
                targetUri, UserResource.class);

        logger.info(
                "RSCL1401002 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    @Override
    public UserResource confirmSimpleHttpClientProxy(String path) {

        URI targetUri = this.getUri(this.uri, path);
        ResponseEntity<UserResource> res = this.simpleClientRestTemplate
                .getForEntity(targetUri, UserResource.class);

        logger.info(
                "RSCL1402001 : Headers containsKey 'Pass-Internal-Proxy' is {}",
                res.getHeaders().containsKey("Pass-Internal-Proxy"));

        return res.getBody();
    }

    /**
     * <ul>
     * <li>URIを取得する</li>
     * </ul>
     * @param args URIのパラメータ
     * @return URI
     */
    private URI getUri(String uri, Object... args) {
        return UriComponentsBuilder.fromUriString(uri).buildAndExpand(args)
                .toUri();
    }
}
