package com.lab.web.command.common;

import com.lab.web.command.Command;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.NOT_FOUND_PAGE;

public class NotFoundCommand implements Command {


    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(NOT_FOUND_PAGE);
    }
}
