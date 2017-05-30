/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

public interface ProxyRestClientService {

    UserResource confirmProxy01(String path);

    UserResource confirmProxy02(String path);

    UserResource confirmSimpleHttpClientProxy(String path);

}
