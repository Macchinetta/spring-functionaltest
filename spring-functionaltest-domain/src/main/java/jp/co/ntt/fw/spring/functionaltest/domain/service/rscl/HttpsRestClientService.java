/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

public interface HttpsRestClientService {

    UserResource connectHttps();

}
