package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.ReceiptService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.CASHIER_PAGE;
import static com.lab.constant.PageConstants.HOME_REDIRECT;

public class CashierCommand extends MultipleMethodCommand {
    private ReceiptService receiptService;

    public CashierCommand() {
        this.receiptService = ServiceFactory.getReceiptService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        receiptService.createReceipt();
        request.setAttribute("lines", receiptService.getLinesByReceiptId());
        request.setAttribute("receipt", receiptService.getReceiptById());
        return new Page(CASHIER_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String action = request.getParameter("action");

        if ("createLine".equals(action)) {
            receiptService.addLine(request.getParameter("idOrName"), Integer.parseInt(request.getParameter("quantity")));
            request.setAttribute("lines", receiptService.getLinesByReceiptId());
            request.setAttribute("receipt", receiptService.getReceiptById());
            return new Page(CASHIER_PAGE);
        } else if ("closeReceipt".equals(action)) {
            receiptService.closeReceipt();
            return new Page(HOME_REDIRECT, true);
        }

        request.setAttribute("error", "error");
        return new Page(CASHIER_PAGE);
    }
}
