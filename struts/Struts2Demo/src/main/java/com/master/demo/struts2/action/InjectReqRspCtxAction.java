package com.master.demo.struts2.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/10/5.
 */
public class InjectReqRspCtxAction implements Action, ServletRequestAware, ServletResponseAware, ServletContextAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext context;

    @Override
    public String execute() throws Exception {
        request.setAttribute("greeting", "Hello World!");
        HttpSession session = request.getSession();
        session.setAttribute("name", "test");

        Integer counter = (Integer) context.getAttribute("counter");
        if(counter == null)
            counter = 0;
        counter++;
        context.setAttribute("counter", counter);
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
