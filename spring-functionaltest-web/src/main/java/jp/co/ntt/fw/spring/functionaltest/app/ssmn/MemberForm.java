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
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(groups = { Personal.class })
    @Size(min = 1, max = 20, groups = { Personal.class })
    private String firstName;

    @NotEmpty(groups = { Personal.class })
    @Size(min = 1, max = 20, groups = { Personal.class })
    private String lastName;

    @NotEmpty(groups = { Personal.class })
    @Size(min = 1, max = 20, groups = { Personal.class })
    private String firstNameKana;

    @NotEmpty(groups = { Personal.class })
    @Size(min = 1, max = 20, groups = { Personal.class })
    private String lastNameKana;

    @NotNull(groups = { Personal.class })
    @Min(value = 0, groups = { Personal.class })
    @Max(value = 200, groups = { Personal.class })
    private Integer age;

    private String gender;

    @NotEmpty(groups = { Address.class })
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}", groups = { Address.class })
    private String zipCode;

    @NotEmpty(groups = { Address.class })
    @Size(min = 1, max = 30, groups = { Address.class })
    private String state;

    @NotEmpty(groups = { Address.class })
    @Size(min = 1, max = 30, groups = { Address.class })
    private String city;

    @NotEmpty(groups = { Address.class })
    @Size(min = 1, max = 30, groups = { Address.class })
    private String address;

    @NotEmpty(groups = { Other.class })
    @Size(min = 1, max = 20, groups = { Other.class })
    private String occupation;

    @NotEmpty(groups = { Other.class })
    @Size(min = 1, max = 50, groups = { Other.class })
    @Email(groups = { Other.class })
    private String mailAddress;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameKana() {
        return firstNameKana;
    }

    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    public String getLastNameKana() {
        return lastNameKana;
    }

    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public static interface Personal {
    }

    public static interface Address {
    }

    public static interface Other {
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
