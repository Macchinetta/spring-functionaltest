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
