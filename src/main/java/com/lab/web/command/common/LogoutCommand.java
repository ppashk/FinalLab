package com.lab.web.command.common;

import com.lab.factory.ServiceFactory;
import com.lab.service.ReportService;
import com.lab.web.command.Command;
import com.lab.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.lab.constant.PageConstants.HOME_REDIRECT;

public class LogoutCommand implements Command{
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    private ReportService reportService;

    public LogoutCommand() {
        this.reportService = ServiceFactory.getReportService();
    }

    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOG.debug("Invalidate session with ID: " +  session.getId());
        reportService.closeZReport();
        session.invalidate();
        return new Page(HOME_REDIRECT, true);
    }
}
