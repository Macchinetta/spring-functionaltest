/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.SystemException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class InterceptorsRestClientServiceImpl implements
                                              InterceptorsRestClientService {

    @Inject
    RestTemplate interceptorsRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public UserResource confirmInterceptor01() {

        URI targetUri = this.getUri(this.uri, "basic");

        UserResource user = this.interceptorsRestTemplate.getForObject(
                targetUri, UserResource.class);

        return user;
    }

    @Override
    public void confirmInterceptor02() {
        URI targetUri = this.getUri(this.uri, "");

        // 閉塞ファイルを作成する。
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("RSCL1104", ".block");
        } catch (IOException e) {
            throw new SystemException("e.sf.rscl.9001", "input/output error.", e);
        }

        try {
            this.interceptorsRestTemplate.getForObject(targetUri,
                    UserResource.class);
            tmpFile.delete();
        } catch (BusinessException e) {
            throw e;
        } finally {
            tmpFile.delete();
        }
    }

    @Override
    public UserResource confirmInterceptor03() {

        URI targetUri = this.getUri(this.uri, "retry");

        RequestEntity<Void> req = RequestEntity.get(targetUri).build();
        ResponseEntity<UserResource> res = this.interceptorsRestTemplate
                .exchange(req, UserResource.class);
        UserResource user = res.getBody();

        return user;
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
