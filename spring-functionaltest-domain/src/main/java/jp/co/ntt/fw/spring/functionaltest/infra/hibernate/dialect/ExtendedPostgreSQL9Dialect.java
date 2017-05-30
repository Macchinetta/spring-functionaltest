/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.hibernate.dialect;

import org.hibernate.dialect.PostgreSQL9Dialect;

public class ExtendedPostgreSQL9Dialect extends PostgreSQL9Dialect {

    public ExtendedPostgreSQL9Dialect() {
        super();
        registerKeyword("true");
        registerKeyword("false");
        registerKeyword("unknown");
    }

}
