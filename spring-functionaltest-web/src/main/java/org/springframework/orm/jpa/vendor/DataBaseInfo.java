/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package org.springframework.orm.jpa.vendor;

public class DataBaseInfo {

    public static String getDataBaseID(
            HibernateJpaVendorAdapter hibernateJpaVendorAdapter) {
        return hibernateJpaVendorAdapter.getDatabase().toString();
    }

}
