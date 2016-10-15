package com.master.demo.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/9.
 */
public class UserAction extends ActionSupport implements ServletRequestAware {

    private static final Logger logger = Logger.getLogger(UserAction.class);

    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String add() {
        if(logger.isDebugEnabled()) {
            logger.debug("add user method invoked.");
        }
        return SUCCESS;
    }

    public String delete() {
        if(logger.isDebugEnabled()) {
            logger.debug("delete user method invoked.");
        }
        return SUCCESS;
    }

}
