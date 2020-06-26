/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * Hibernate Validatorのアノテーションを検証する。
 */
@SuppressWarnings("deprecation")
public class VariousSimpleValidationHVForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreditCardNumber
    private String creditcardnumber;

    @ISBN
    private String isbn;

    @URL
    private String url;

    @NotEmpty
    private String notemptyHV;

    @NotBlank
    private String notblankHV;

    @Email
    private String emailHV;

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotemptyHV() {
        return notemptyHV;
    }

    public void setNotemptyHV(String notemptyHV) {
        this.notemptyHV = notemptyHV;
    }

    public String getNotblankHV() {
        return notblankHV;
    }

    public void setNotblankHV(String notblankHV) {
        this.notblankHV = notblankHV;
    }

    public String getEmailHV() {
        return emailHV;
    }

    public void setEmailHV(String emailHV) {
        this.emailHV = emailHV;
    }

}
