/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

public interface CollectionRestClientService {

    List<UserResource> exchangeCollection(String path);

}
