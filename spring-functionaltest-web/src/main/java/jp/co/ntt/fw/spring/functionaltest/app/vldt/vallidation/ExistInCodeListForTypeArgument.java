/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
