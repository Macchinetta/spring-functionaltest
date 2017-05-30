/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
/**
 * 
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <ul>
 * <li>JavaBeanのクラス。</li> <br>
 * XML化可能。
 * </ul>
 * @author btishinoyu
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.NONE)
public class UserResource implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = -8223995395918823188L;

    /**
     * 名前
     */
    @XmlElement
    private String name;

    /**
     * 年齢
     */
    @XmlElement
    private int age;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

}
