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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DateOfBirthValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDetailsForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDetailsForm form = (UserDetailsForm) target;
        int age = form.getAge();
        LocalDate dateOfBirth = form.getDateOfBirth();

        if (dateOfBirth == null)
            return;

        if (dateOfBirth.plusYears(age).getYear() != (new LocalDate()).getYear())
            errors.rejectValue("age", "i.sf.vldt.5002",
                    "Age and Date of Birth is inconsistent.");
    }

}
