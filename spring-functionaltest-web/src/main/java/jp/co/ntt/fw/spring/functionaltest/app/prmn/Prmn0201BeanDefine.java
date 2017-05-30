/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Prmn0201BeanDefine implements Serializable {

    private static final long serialVersionUID = 1839390419023155744L;

    private String propertyValue0101;

    public String getPropertyValue0101() {
        return propertyValue0101;
    }

    public void setPropertyValue0101(String propertyValue0101) {
        this.propertyValue0101 = propertyValue0101;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
