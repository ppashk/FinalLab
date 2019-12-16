package com.lab.web.command.pages;

import com.lab.web.command.Command;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.HOME_PAGE;

public class HomeCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(HOME_PAGE);
    }
}
