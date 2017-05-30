/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * getTodosResponse complex typeのJavaクラス。
 * <p>
 * 次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="getTodosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="todos" type="{http://functionaltest.spring.fw.ntt.co.jp/todo}todo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTodosResponse", propOrder = { "todos" })
public class GetTodosResponse {

    protected List<Todo> todos;

    /**
     * Gets the value of the todos property.
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the todos
     * property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getTodos().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Todo }
     */
    public List<Todo> getTodos() {
        if (todos == null) {
            todos = new ArrayList<Todo>();
        }
        return this.todos;
    }

}
