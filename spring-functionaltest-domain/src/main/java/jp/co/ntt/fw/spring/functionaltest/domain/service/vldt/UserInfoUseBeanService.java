/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public interface UserInfoUseBeanService {

    @NotNull
    @Valid
    UserInfoUseBeanOutput convertUserInfo(
            @NotNull @Valid UserInfoUseBeanInput userInfoUseBeanInput);

}
