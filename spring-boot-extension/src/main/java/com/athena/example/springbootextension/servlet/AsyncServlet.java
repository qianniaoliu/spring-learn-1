package com.athena.example.springbootextension.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: yusheng
 */
@WebServlet(asyncSupported = true,
            name = "asyncServlet",
            urlPatterns = "/get/syncServlet")
public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext context = req.startAsync();
        if(req.isAsyncSupported()){
            resp.getWriter().println("Hello World Async!");
            context.complete();
        }
    }
}
