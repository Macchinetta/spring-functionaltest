/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.hibernate.dialect;

import org.hibernate.dialect.H2Dialect;

public class ExtendedH2Dialect extends H2Dialect {

    public ExtendedH2Dialect() {
        super();
        registerKeyword("true");
        registerKeyword("false");
        registerKeyword("unknown");
    }

}
