/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * webFaultTypeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="webFaultType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AccessDeniedFault"/>
 *     &lt;enumeration value="BusinessFault"/>
 *     &lt;enumeration value="ResourceNotFoundFault"/>
 *     &lt;enumeration value="ValidationFault"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "webFaultType")
@XmlEnum
public enum WebFaultType {

    @XmlEnumValue("AccessDeniedFault")
    ACCESS_DENIED_FAULT("AccessDeniedFault"), @XmlEnumValue("BusinessFault")
    BUSINESS_FAULT("BusinessFault"), @XmlEnumValue("ResourceNotFoundFault")
    RESOURCE_NOT_FOUND_FAULT("ResourceNotFoundFault"), @XmlEnumValue("ValidationFault")
    VALIDATION_FAULT("ValidationFault");
    private final String value;

    WebFaultType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WebFaultType fromValue(String v) {
        for (WebFaultType c : WebFaultType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
