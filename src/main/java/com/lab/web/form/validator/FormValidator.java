package com.lab.web.form.validator;

public interface FormValidator<T> {

    boolean validate(T form);
}
