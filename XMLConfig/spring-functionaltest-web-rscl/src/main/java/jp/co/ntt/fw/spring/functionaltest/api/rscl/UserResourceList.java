/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.api.rscl;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
