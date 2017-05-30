/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
        return "Name [celebrityId=" + celebrityId + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }

}
