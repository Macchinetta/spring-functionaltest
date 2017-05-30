/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrmnDefines implements Serializable {

    private static final long serialVersionUID = -7022556273533219566L;

    @Value("${i.sf.prmn.0001}")
    private String propertyValue0001;

    @Value("${i.sf.prmn.0002}")
    private String propertyValue0002;

    @Value("${i.sf.prmn.0007}")
    private String propertyValue0007;

    @Value("${i.sf.prmn.0008}")
    private String propertyValue0008;

    @Value("${i.sf.prmn.0009}")
    private String propertyValue0009;

    @Value("${i.sf.prmn.0010}")
    private String propertyValue0010;

    public String getI_SF_PRMN_0001() {
        return propertyValue0001;
    }

    public String getPropertyValue0001() {
        return propertyValue0001;
    }

    public void setPropertyValue0001(String propertyValue0001) {
        this.propertyValue0001 = propertyValue0001;
    }

    public String getPropertyValue0002() {
        return propertyValue0002;
    }

    public void setPropertyValue0002(String propertyValue0002) {
        this.propertyValue0002 = propertyValue0002;
    }

    public String getPropertyValue0007() {
        return propertyValue0007;
    }

    public void setPropertyValue0007(String propertyValue0007) {
        this.propertyValue0007 = propertyValue0007;
    }

    public String getPropertyValue0008() {
        return propertyValue0008;
    }

    public void setPropertyValue0008(String propertyValue0008) {
        this.propertyValue0008 = propertyValue0008;
    }

    public String getPropertyValue0009() {
        return propertyValue0009;
    }

    public void setPropertyValue0009(String propertyValue0009) {
        this.propertyValue0009 = propertyValue0009;
    }

    public String getPropertyValue0010() {
        return propertyValue0010;
    }

    public void setPropertyValue0010(String propertyValue0010) {
        this.propertyValue0010 = propertyValue0010;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
