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
package jp.co.ntt.fw.spring.functionaltest.app.vldt.vallidation;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.terasoluna.gfw.common.codelist.ExistInCodeList;

@Documented
@Constraint(validatedBy = {})
@Target(TYPE_USE)
@Retention(RUNTIME)
@ReportAsSingleViolation
@ExistInCodeList(codeListId = "")
public @interface ExistInCodeListForTypeArgument {
    String message() default "{jp.co.ntt.fw.spring.functionaltest.app.vldt.vallidation.ExistInCodeListForTypeArgument.message}";

    @OverridesAttribute(constraint = ExistInCodeList.class, name = "codeListId")
    String codeListId();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(TYPE_USE)
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ExistInCodeListForTypeArgument[] value();
    }
}
