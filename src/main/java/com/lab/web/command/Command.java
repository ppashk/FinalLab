package com.lab.web.command;

import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Page perform(HttpServletRequest request);
}
