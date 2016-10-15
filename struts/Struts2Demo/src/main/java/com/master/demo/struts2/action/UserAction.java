package com.master.demo.struts2.action;

import com.master.demo.struts2.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/3.
 */
public class UserAction extends ActionSupport {

    private static final Logger logger = Logger.getLogger(UserAction.class);

    private User user;
    private String password;

    public String register() throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug(user + " : " + password);
        }
        return SUCCESS;
    }

    public String login() throws Exception {

        ActionContext context = ActionContext.getContext();
        Map<String, Object> request = (Map) context.get("request");
        Map<String, Object> session = context.getSession();
        Map<String, Object> application = context.getApplication();

        if(logger.isDebugEnabled()) {
            System.out.println("-------------------------------------------------------");
            System.out.println("        request info below          ");
            Set<String> set = request.keySet();
            for(String key : set) {
                System.out.println(key + " -> " + request.get(key));
            }
            System.out.println();
            System.out.println("        session info below          ");
            set = session.keySet();
            for(String key : set) {
                System.out.println(key + " -> " + session.get(key));
            }
            System.out.println();
            System.out.println("        application info below          ");
            set = application.keySet();
            for(String key : set) {
                System.out.println(key + " -> " + application.get(key));
            }
            System.out.println("-------------------------------------------------------");
        }

        if(logger.isDebugEnabled()) {
            logger.debug(user + " : " + password);
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}