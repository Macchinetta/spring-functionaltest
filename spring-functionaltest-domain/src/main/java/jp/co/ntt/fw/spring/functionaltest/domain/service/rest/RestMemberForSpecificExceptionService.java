/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rest;

public interface RestMemberForSpecificExceptionService {

    void callBusinessException(String memberId);

    void callOptimisticFailureException(String memberId);

    void callException(String memberId);

}
