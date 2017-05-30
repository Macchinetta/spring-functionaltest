/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
