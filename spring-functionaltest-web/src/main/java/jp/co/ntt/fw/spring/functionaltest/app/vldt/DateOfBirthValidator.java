/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
