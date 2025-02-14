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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class Celebrity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int celebrityId;

    private String firstName;

    private String lastName;

    public int getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(int celebrityId) {
        this.celebrityId = celebrityId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + celebrityId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Celebrity other = (Celebrity) obj;
        if (celebrityId != other.celebrityId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Name [celebrityId=" + celebrityId + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }

}
