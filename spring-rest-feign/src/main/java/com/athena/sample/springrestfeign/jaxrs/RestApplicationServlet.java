package com.athena.sample.springrestfeign.jaxrs;



import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  HttpServletDispatcher
 * @auther: yusheng
 */
@WebServlet(name="com.athena.sample.springrestfeign.jaxrs.RestApplication",
        urlPatterns = "/*",
        initParams = {@WebInitParam(name = "javax.ws.rs.core.Application",
                value = "com.athena.sample.springrestfeign.jaxrs.RestApplication"),
        @WebInitParam(name = "resteasy.servlet.mapping.prefix",
        value = "/jaxrs")})
public class RestApplicationServlet extends HttpServletDispatcher {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
