package com.lab.web;


import com.lab.web.command.Command;
import com.lab.factory.CommandFactory;
import com.lab.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/store/*")
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }



    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getPath(req);

        Command command = CommandFactory.getCommand(path, req.getMethod());

        Page page = command.perform(req);

        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + "/" + page.getUrl());
        } else if(page.isAjax()){
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(page.getAjaxContent());
            out.flush();
        } else {
            req.getRequestDispatcher(resolvePath(page.getUrl())).forward(req, resp);
        }
    }

    private String resolvePath(String path){
        return "/WEB-INF/pages/" + path + ".jsp";
    }

    private String getPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        LOG.info("Path: " + path);
        return path;
    }
}
