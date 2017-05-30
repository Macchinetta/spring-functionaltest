/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LargerValue1ThanValue2Validator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FormObjectForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormObjectForm form = (FormObjectForm) target;
        Integer value1 = form.getValue1();
        Integer value2 = form.getValue2();

        if (value1 == null || value2 == null) {
            return;
        }
        if (!(value1.intValue() > value2.intValue())) {
            errors.reject("LargerValue1ThanValue2Validator.formObjectForm",
                    "value1 must be larger than value2.");
        }
    }

}
