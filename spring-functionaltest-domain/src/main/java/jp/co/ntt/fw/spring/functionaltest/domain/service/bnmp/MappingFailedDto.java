/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;

import org.joda.time.LocalDate;

public class MappingFailedDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate birthLocalDate;

    public LocalDate getBirthLocalDate() {
        return birthLocalDate;
    }

    public void setBirthLocalDate(LocalDate birthLocalDate) {
        this.birthLocalDate = birthLocalDate;
    }
}
