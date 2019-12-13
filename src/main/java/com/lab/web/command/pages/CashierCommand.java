package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.ReceiptService;
import com.lab.service.ReportService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.CASHIER_PAGE;
import static com.lab.constant.PageConstants.HOME_REDIRECT;

public class CashierCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(CashierCommand.class);

    private ReceiptService receiptService;
    private ReportService reportService;

    public CashierCommand() {
        this.receiptService = ServiceFactory.getReceiptService();
        this.reportService = ServiceFactory.getReportService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        LOG.info("Receipt created " + receiptService.createReceipt());
        request.setAttribute("lines", receiptService.getReceipt().getLines());
        request.setAttribute("receipt", receiptService.getReceipt());
        return new Page(CASHIER_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String action = request.getParameter("action");

        if ("createLine".equals(action)) {
            LOG.info("Report updated " + reportService.updateReports(receiptService.addLine(request.getParameter("idOrName"), Integer.parseInt(request.getParameter("quantity")))));
            request.setAttribute("lines", receiptService.getReceipt().getLines());
            request.setAttribute("receipt", receiptService.getReceipt());
            return new Page(CASHIER_PAGE);
        } else if ("closeReceipt".equals(action)) {
            LOG.info("Receipt closed " + receiptService.closeReceipt());
            return new Page(HOME_REDIRECT, true);
        }

        request.setAttribute("error", "error");
        return new Page(CASHIER_PAGE);
    }
}
