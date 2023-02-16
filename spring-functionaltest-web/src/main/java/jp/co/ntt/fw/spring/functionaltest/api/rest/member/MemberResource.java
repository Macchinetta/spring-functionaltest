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
package jp.co.ntt.fw.spring.functionaltest.api.rest.member;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.terasoluna.gfw.common.codelist.ExistInCodeList;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jp.co.ntt.fw.spring.functionaltest.api.rest.common.resource.AbstractLinksSupportedResource;

public class MemberResource extends AbstractLinksSupportedResource implements
                            Serializable {

    private static final long serialVersionUID = 1L;

    interface PostMembers {
    }

    interface PutMember {
    }

    interface Summary {
    }

    interface Detail {
    }

    @JsonView({ Summary.class, Detail.class })
    @Null(groups = PostMembers.class)
    @NotEmpty(groups = PutMember.class)
    @Size(min = 10, max = 10, groups = PutMember.class)
    private String memberId;

    @JsonView({ Summary.class, Detail.class })
    @NotEmpty
    @Size(max = 128)
    private String firstName;

    @JsonView({ Summary.class, Detail.class })
    @NotEmpty
    @Size(max = 128)
    private String lastName;

    @JsonView(Detail.class)
    @NotEmpty
    @ExistInCodeList(codeListId = "CL_EN_GENDER")
    private String genderCode;

    @JsonView(Detail.class)
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate dateOfBirth;

    @JsonView(Detail.class)
    @NotEmpty
    @Size(max = 256)
    @Email
    private String emailAddress;

    @JsonView(Detail.class)
    @Size(max = 20)
    private String telephoneNumber;

    @JsonView(Detail.class)
    @Size(max = 20)
    private String zipCode;

    @JsonView(Detail.class)
    @Size(max = 256)
    private String address;

    @NotNull(groups = PostMembers.class)
    @Null(groups = PutMember.class)
    @Valid
    private MemberCredentialResource credential;

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime lastModifiedAt;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

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

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberCredentialResource getCredential() {
        return credential;
    }

    public void setCredential(MemberCredentialResource credential) {
        this.credential = credential;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    // omitted setter and getter

}
