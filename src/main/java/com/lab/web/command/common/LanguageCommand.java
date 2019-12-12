package com.lab.web.command.common;

import com.lab.web.command.Command;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.HOME_REDIRECT;

public class LanguageCommand implements Command {
    private static final String LOCALE = "locale";

    @Override
    public Page perform(HttpServletRequest request) {
        String locale = request.getParameter(LOCALE);

        if (locale != null) {
            request.getSession().setAttribute(LOCALE, locale);
        }

        return new Page(HOME_REDIRECT, true);
    }
}
