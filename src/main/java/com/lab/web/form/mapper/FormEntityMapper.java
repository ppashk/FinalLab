package com.lab.web.form.mapper;

@FunctionalInterface
public interface FormEntityMapper<T, E> {
    T map(E form);
}
