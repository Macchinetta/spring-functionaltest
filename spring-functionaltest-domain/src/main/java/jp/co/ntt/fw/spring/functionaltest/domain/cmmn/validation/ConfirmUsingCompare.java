/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;

import org.terasoluna.gfw.common.validator.constraints.Compare;

@Documented
@Constraint(validatedBy = {})
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Compare(left = "", right = "", operator = Compare.Operator.EQUAL, requireBoth = true)
public @interface ConfirmUsingCompare {

    @OverridesAttribute(constraint = Compare.class, name = "message")
    String message() default "{jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmUsingCompare.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute(constraint = Compare.class, name = "left")
    String field();

    @OverridesAttribute(constraint = Compare.class, name = "right")
    String confirmField();

    @Documented
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    public @interface List {
        Confirm[] value();
    }
}
