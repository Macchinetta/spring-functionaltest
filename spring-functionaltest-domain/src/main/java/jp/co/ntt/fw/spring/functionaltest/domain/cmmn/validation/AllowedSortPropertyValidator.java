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
package jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AllowedSortPropertyValidator implements
                                          ConstraintValidator<AllowedSortProperty, Pageable> {

    private Set<String> propertiesLowerCase;

    @Override
    public void initialize(AllowedSortProperty constraintAnnotation) {
        String[] properties = constraintAnnotation.value();
        if (properties == null || properties.length == 0) {
            throw new IllegalArgumentException("Must specify at least one allowed sort property.");
        }
        Set<String> propertiesLowerCase = new HashSet<>();
        for (String property : properties) {
            propertiesLowerCase.add(property.toLowerCase());
        }
        this.propertiesLowerCase = propertiesLowerCase;
    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Sort sort = value.getSort();
        if (sort == null || sort.isEmpty()) {
            return true;
        }
        for (Sort.Order order : value.getSort()) {
            if (!propertiesLowerCase.contains(order.getProperty()
                    .toLowerCase())) {
                return false;
            }
        }
        return true;
    }

}
