/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * uploadFile complex typeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="uploadFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFile", propOrder = { "arg0" })
public class UploadFile {

    @XmlMimeType("application/octet-stream")
    protected DataHandler arg0;

    /**
     * arg0プロパティの値を取得します。
     * @return possible object is {@link DataHandler }
     */
    public DataHandler getArg0() {
        return arg0;
    }

    /**
     * arg0プロパティの値を設定します。
     * @param value allowed object is {@link DataHandler }
     */
    public void setArg0(DataHandler value) {
        this.arg0 = value;
    }

}
