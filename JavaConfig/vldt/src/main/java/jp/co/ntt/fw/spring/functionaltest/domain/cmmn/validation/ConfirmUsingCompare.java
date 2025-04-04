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
import org.terasoluna.gfw.common.validator.constraints.Compare;
import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmUsingCompare.List;

@Documented
@Constraint(validatedBy = {})
@Target({TYPE, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
@Compare(left = "", right = "", operator = Compare.Operator.EQUAL, requireBoth = true)
public @interface ConfirmUsingCompare {

    @OverridesAttribute(constraint = Compare.class, name = "message")
    String message() default "{jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmUsingCompare.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute(constraint = Compare.class, name = "right")
    String field();

    @OverridesAttribute(constraint = Compare.class, name = "left")
    String confirmField();

    @Documented
    @Target({TYPE, ANNOTATION_TYPE, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        ConfirmUsingCompare[] value();
    }
}
