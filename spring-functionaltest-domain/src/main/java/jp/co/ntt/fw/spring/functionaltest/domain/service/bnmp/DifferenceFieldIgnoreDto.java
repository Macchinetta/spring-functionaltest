/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;

public class DifferenceFieldIgnoreDto implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String myoji;

    private String lastName;

    private String age;

    public String getMyoji() {
        return myoji;
    }

    public void setMyoji(String myoji) {
        this.myoji = myoji;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
