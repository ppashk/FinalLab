package com.lab.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/store/*")
public class StaticResourceFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(StaticResourceFilter.class);
    private static final String RESOURCES_PATH = "/lab/resources/";
    private static final String PAGES_PATH = "/pages/";
    private static final String STATIC_RESOURCES_PATH = "/static/";
    private static final String APP_PATH = "/store";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOG.debug("Do service, URI: " + httpServletRequest.getRequestURI());
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (shouldBeSkipped(path)) {
            LOG.debug("Static resource: " + path);
            chain.doFilter(request, response);
            return;
        }

        String pathToForward = APP_PATH + path;
        LOG.info("Non static resource: " + path + ". New Path: " + pathToForward);
        httpServletRequest.getRequestDispatcher(pathToForward).forward(request, response);
    }

    private boolean shouldBeSkipped(String path) {
        return path.startsWith(RESOURCES_PATH) || path.startsWith(PAGES_PATH)
                || path.startsWith(STATIC_RESOURCES_PATH) || path.startsWith(APP_PATH);
    }

    @Override
    public void destroy() {
    }
}
