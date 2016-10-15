package com.master.demo.struts2.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/10/5.
 */
public class UseRawServletReqSesAppAction1 implements Action {

    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext context = ServletActionContext.getServletContext();
        HttpSession session = request.getSession();
        if("lily".equals(this.username) && "lily".equals(this.password)) {
            request.setAttribute("greeting", "Hello " + this.username);
            session.setAttribute("password", this.password);

            Integer counter = (Integer) context.getAttribute("counter");
            if(counter == null)
                counter = 0;
            counter++;
            context.setAttribute("counter", counter);
            return SUCCESS;
        }
        return ERROR;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
