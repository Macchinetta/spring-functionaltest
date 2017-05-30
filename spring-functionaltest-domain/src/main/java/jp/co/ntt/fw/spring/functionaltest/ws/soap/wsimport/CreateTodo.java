/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * createTodo complex typeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="createTodo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="todo" type="{http://functionaltest.spring.fw.ntt.co.jp/todo}todo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createTodo", propOrder = { "todo" })
public class CreateTodo {

    protected Todo todo;

    /**
     * todoプロパティの値を取得します。
     * @return possible object is {@link Todo }
     */
    public Todo getTodo() {
        return todo;
    }

    /**
     * todoプロパティの値を設定します。
     * @param value allowed object is {@link Todo }
     */
    public void setTodo(Todo value) {
        this.todo = value;
    }

}
