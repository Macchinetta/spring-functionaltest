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

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmForMultiFieldHighlight.List;

@Documented
@Constraint(validatedBy = { ConfirmValidatorForMultiFieldHighlight.class })
@Target({ TYPE, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface ConfirmForMultiFieldHighlight {
    String message() default "{jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmForMultiFieldHighlight.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String confirmField();

    @Target({ TYPE, ANNOTATION_TYPE, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ConfirmForMultiFieldHighlight[] value();
    }
}
