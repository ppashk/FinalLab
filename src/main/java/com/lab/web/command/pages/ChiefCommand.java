package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.ReceiptService;
import com.lab.service.ReportService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.lab.constant.PageConstants.CHIEF_CASHIER_PAGE;
import static com.lab.constant.PageConstants.REPORT_PAGE;

public class ChiefCommand extends MultipleMethodCommand {
    private ReceiptService receiptService;
    private ReportService reportService;

    public ChiefCommand() {
        this.receiptService = ServiceFactory.getReceiptService();
        this.reportService = ServiceFactory.getReportService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        request.setAttribute("receipts", receiptService.getAll());
        return new Page(CHIEF_CASHIER_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("cancelReceipt".equals(action)) {
            if (receiptService.deleteReceipt(Integer.parseInt(request.getParameter("receiptId")))) {
                request.setAttribute("message", "chief.money.returned");
                request.setAttribute("receipts", receiptService.getAll());
                return new Page(CHIEF_CASHIER_PAGE);
            }
        } else if ("cancelLine".equals(action)) {
            if (receiptService.deleteLine(request.getParameter("idOrName"))) {
                request.setAttribute("message", "chief.money.returned");
                request.setAttribute("receipts", receiptService.getAll());
                return new Page(CHIEF_CASHIER_PAGE);
            } else {
                request.setAttribute("message", "chief.incorrect.input");
                request.setAttribute("receipts", receiptService.getAll());
                return new Page(CHIEF_CASHIER_PAGE);
            }
        } else if ("xReport".equals(action)) {
            session.setAttribute("reportType", "xReport");
            return new Page(REPORT_PAGE, true);
        } else if ("zReport".equals(action)) {
            session.setAttribute("reportType", "zReport");
            return new Page(REPORT_PAGE, true);
        } else if ("createXReport".equals(action)) {
            if (reportService.createXReport())
                request.setAttribute("receipts", receiptService.getAll());
                return new Page(CHIEF_CASHIER_PAGE);
        }

        request.setAttribute("error", "error");
        return new Page(CHIEF_CASHIER_PAGE);
    }
}
