package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.StorageService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.LOADER_PAGE;

public class LoaderCommand extends MultipleMethodCommand {
    private StorageService storageService;

    public LoaderCommand() {
        this.storageService = ServiceFactory.getStorageService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        request.setAttribute("products", storageService.getAll());
        return new Page(LOADER_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String action = request.getParameter("action");

        if ("createProduct".equals(action)) {
            storageService.createProduct(request.getParameter("name"), Integer.parseInt(request.getParameter("price")), Integer.parseInt(request.getParameter("quantity")));
            return new Page(LOADER_PAGE);
        } else if ("updateProduct".equals(action)) {
            storageService.updateProduct(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("quantity")));
            return new Page(LOADER_PAGE);
        }

        request.setAttribute("error", "error");
        return new Page(LOADER_PAGE);
    }
}
