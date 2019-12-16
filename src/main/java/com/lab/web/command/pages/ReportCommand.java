package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.ReportService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.lab.constant.PageConstants.REPORT_PAGE;

public class ReportCommand extends MultipleMethodCommand {
    private ReportService reportService;

    public ReportCommand() {
        this.reportService = ServiceFactory.getReportService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if ("xReport".equals(String.valueOf(session.getAttribute("reportType")))) {
            request.setAttribute("reports", reportService.getXReport());
            return new Page(REPORT_PAGE);
        } else if ("zReport".equals(String.valueOf(session.getAttribute("reportType")))) {
            request.setAttribute("reports", reportService.getZReport());
            return new Page(REPORT_PAGE);
        }

        request.setAttribute("error", "error");
        return new Page(REPORT_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        return null;
    }
}
