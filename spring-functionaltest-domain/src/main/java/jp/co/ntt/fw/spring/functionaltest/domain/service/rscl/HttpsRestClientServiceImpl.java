/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class HttpsRestClientServiceImpl implements HttpsRestClientService {

    @Inject
    RestTemplate httpsRestTemplate;

    @Value("${rscl.httpsserver.uri}")
    URI httpsUri;

    @Override
    public UserResource connectHttps() {

        RequestEntity<Void> req = RequestEntity.get(this.httpsUri).build();
        ResponseEntity<UserResource> res = this.httpsRestTemplate.exchange(req,
                UserResource.class);

        UserResource user = res.getBody();

        return user;
    }
}
