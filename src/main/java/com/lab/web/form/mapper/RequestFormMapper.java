package com.lab.web.form.mapper;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface RequestFormMapper<T> {

    T map(HttpServletRequest request);
}
