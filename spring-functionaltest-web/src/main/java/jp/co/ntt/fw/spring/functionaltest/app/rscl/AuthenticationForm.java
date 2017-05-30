/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
/**
 * 
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.Serializable;

/**
 * <ul>
 * <li>AuthenticationFormのクラス。</li> <br>
 * </ul>
 */
public class AuthenticationForm implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ユーザID
     */
    private String userid;

    /**
     * パスワード
     */
    private String password;

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
