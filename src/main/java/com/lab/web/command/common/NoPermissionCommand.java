package com.lab.web.command.common;

import com.lab.web.command.Command;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.NO_PERMISSION_PAGE;

public class NoPermissionCommand implements Command {


    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(NO_PERMISSION_PAGE);
    }
}
