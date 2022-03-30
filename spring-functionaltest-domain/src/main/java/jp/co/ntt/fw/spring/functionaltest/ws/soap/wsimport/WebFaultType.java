/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
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
    RESOURCE_NOT_FOUND_FAULT(
            "ResourceNotFoundFault"), @XmlEnumValue("ValidationFault")
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
