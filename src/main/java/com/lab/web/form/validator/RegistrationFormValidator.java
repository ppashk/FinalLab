package com.lab.web.form.validator;

import com.lab.web.form.RegistrationForm;

public class RegistrationFormValidator {

    public static boolean validate(RegistrationForm form) {
        return validatePassword(form)
                && validateLogin(form);
    }

    private static boolean validatePassword(RegistrationForm form) {
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();

        return password != null
                && password.equals(passwordConfirm);
    }

    private static boolean validateLogin(RegistrationForm form){
        String login = form.getUsername();
        return login != null && login.length() > 2;
    }
}
