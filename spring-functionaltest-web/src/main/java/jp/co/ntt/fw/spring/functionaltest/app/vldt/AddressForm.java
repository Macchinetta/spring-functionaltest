/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class AddressForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 50)
    private String userName;

    @Size(min = 1, max = 10)
    private String postCode;

    @Size(min = 1, max = 100)
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
