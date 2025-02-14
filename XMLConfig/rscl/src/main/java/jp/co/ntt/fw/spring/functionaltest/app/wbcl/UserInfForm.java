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
/**
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.wbcl;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * <ul>
 * <li>UserInfFormのクラス。</li> <br>
 * </ul>
 */
public class UserInfForm implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = 1L;

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
     * メッセージ
     */
    private String message;

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
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
        return this.age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
