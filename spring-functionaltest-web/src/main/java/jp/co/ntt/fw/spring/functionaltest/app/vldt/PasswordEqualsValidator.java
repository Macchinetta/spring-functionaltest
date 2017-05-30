/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordEqualsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserForm form = (UserForm) target;
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        if (password == null || confirmPassword == null)
            return;

        if (!password.equals(confirmPassword))
            errors.rejectValue("password", "i.sf.vldt.5001",
                    "password and confirm password must be same.");
    }

}
