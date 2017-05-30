/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public interface UserIdUseService {

    @NotNull
    String convertUserId(@NotNull String userId);

}
