/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * webFaultBean complex typeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="webFaultBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errors" type="{http://functionaltest.spring.fw.ntt.co.jp/todo}errorBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://functionaltest.spring.fw.ntt.co.jp/todo}webFaultType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webFaultBean", propOrder = { "errors", "type" })
public class WebFaultBean {

    @XmlElement(nillable = true)
    protected List<ErrorBean> errors;

    @XmlSchemaType(name = "string")
    protected WebFaultType type;

    /**
     * Gets the value of the errors property.
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the errors
     * property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getErrors().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link ErrorBean }
     */
    public List<ErrorBean> getErrors() {
        if (errors == null) {
            errors = new ArrayList<ErrorBean>();
        }
        return this.errors;
    }

    /**
     * typeプロパティの値を取得します。
     * @return possible object is {@link WebFaultType }
     */
    public WebFaultType getType() {
        return type;
    }

    /**
     * typeプロパティの値を設定します。
     * @param value allowed object is {@link WebFaultType }
     */
    public void setType(WebFaultType value) {
        this.type = value;
    }

}
