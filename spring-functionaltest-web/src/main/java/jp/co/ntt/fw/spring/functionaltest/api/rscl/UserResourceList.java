/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rscl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <ul>
 * <li>JavaBeanのリストクラス。</li> <br>
 * XML化可能。
 * </ul>
 * @author btishinoyu
 */
@XmlRootElement(name = "userlst")
@XmlAccessorType(XmlAccessType.NONE)
public class UserResourceList implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = 3374333448090317162L;

    /**
     * ユーザ情報リスト
     */
    @XmlElement(name = "user")
    private List<UserResource> list;

    /**
     * @return the list
     */
    public List<UserResource> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<UserResource> list) {
        this.list = list;
    }

}
