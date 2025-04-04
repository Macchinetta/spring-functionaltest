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
package jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation;

import java.util.Objects;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {

    private String field;

    private String confirmField;

    private String message;

    @Override
    public void initialize(Confirm constraintAnnotation) {
        field = constraintAnnotation.field();
        confirmField = constraintAnnotation.confirmField();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
        boolean matched = Objects.equals(fieldValue, confirmFieldValue);
        if (matched) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(confirmField)
                    .addConstraintViolation();
            return false;
        }
    }

}
