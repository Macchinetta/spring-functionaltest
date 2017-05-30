/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Prmn0202BeanDefine implements Serializable {

    private static final long serialVersionUID = 373806188330487820L;

    private String propertyValue0200;

    public String getPropertyValue0200() {
        return propertyValue0200;
    }

    public void setPropertyValue0200(String propertyValue0200) {
        this.propertyValue0200 = propertyValue0200;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
