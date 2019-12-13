package com.lab.web.command.pages;

import com.lab.factory.ServiceFactory;
import com.lab.service.StorageService;
import com.lab.web.command.MultipleMethodCommand;
import com.lab.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.lab.constant.PageConstants.LOADER_PAGE;

public class LoaderCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(LoaderCommand.class);

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
            LOG.info("Product created" + storageService.createProduct(request.getParameter("name"), Integer.parseInt(request.getParameter("price")), Integer.parseInt(request.getParameter("quantity"))));
            request.setAttribute("products", storageService.getAll());
            return new Page(LOADER_PAGE);
        } else if ("updateProduct".equals(action)) {
            LOG.info("Product updated" + storageService.updateProduct(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("quantity"))));
            request.setAttribute("products", storageService.getAll());
            return new Page(LOADER_PAGE);
        }

        request.setAttribute("error", "error");
        return new Page(LOADER_PAGE);
    }
}
