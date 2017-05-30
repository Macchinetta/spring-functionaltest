/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Prmn04Defines implements Serializable {

    private static final long serialVersionUID = 2572679991365788905L;

    @Value("${i.sf.prmn.0401001}")
    private String propertyValue0401001;

    public String getPropertyValue0401001() {
        return propertyValue0401001;
    }

    public void setPropertyValue0401001(String propertyValue0401001) {
        this.propertyValue0401001 = propertyValue0401001;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
