/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

public interface JPABookInitializerService {

    public void initByBatch();

    public void clear();

}
