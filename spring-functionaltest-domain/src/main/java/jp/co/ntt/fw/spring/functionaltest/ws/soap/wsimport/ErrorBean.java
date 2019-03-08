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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * errorBean complex typeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="errorBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorBean", propOrder = { "code", "message", "path" })
public class ErrorBean {

    protected String code;

    protected String message;

    protected String path;

    /**
     * codeプロパティの値を取得します。
     * @return possible object is {@link String }
     */
    public String getCode() {
        return code;
    }

    /**
     * codeプロパティの値を設定します。
     * @param value allowed object is {@link String }
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * messageプロパティの値を取得します。
     * @return possible object is {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * messageプロパティの値を設定します。
     * @param value allowed object is {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * pathプロパティの値を取得します。
     * @return possible object is {@link String }
     */
    public String getPath() {
        return path;
    }

    /**
     * pathプロパティの値を設定します。
     * @param value allowed object is {@link String }
     */
    public void setPath(String value) {
        this.path = value;
    }

}
