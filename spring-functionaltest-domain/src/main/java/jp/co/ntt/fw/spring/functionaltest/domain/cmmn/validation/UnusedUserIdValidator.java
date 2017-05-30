/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserService;

@Component
public class UnusedUserIdValidator implements
                                  ConstraintValidator<UnusedUserId, String> {

    @Inject
    UserService userService;

    @Override
    public void initialize(UnusedUserId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null)
            return true;

        return userService.isUnusedUserId(value);
    }
}
