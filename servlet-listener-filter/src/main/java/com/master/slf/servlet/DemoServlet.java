package com.master.slf.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DemoServlet.class);

    @Override
    public void destroy() {
        logger.debug("destroy demo servlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        logger.debug("init demo servlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("[" + req.getSession().getId() + "] Invoking doGet.......");
		/*logger.debug(req.getQueryString());
		req.getSession().setAttribute("name", "Lily");*///强制生成session并增加一个attr，从而触发DemoHttpSessionListener和DemoHttpSessionAttributeListener
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Invoking doPost.......");
        super.doPost(req, resp);
    }

}
