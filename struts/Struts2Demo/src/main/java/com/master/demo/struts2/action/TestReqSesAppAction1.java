package com.master.demo.struts2.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/5.<br><br>
 *
 * Implemented {@link RequestAware}, {@link SessionAware}, {@link ApplicationAware}
 * to ensure the action bean not depends on servlet container
 * it will be easy to test the action bean anyway.
 */
public class TestReqSesAppAction1 implements Action, RequestAware, SessionAware, ApplicationAware {

    private Map<String, Object> request;
    private Map<String, Object> session;
    private Map<String, Object> application;

    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        if("lily".equals(this.username) && "lily".equals(this.password)) {
            request.put("greeting", "Hello " + this.username);
            session.put("password", this.password);

            Integer counter = (Integer) application.get("counter");
            if(counter == null)
                counter = 0;
            counter++;
            application.put("counter", counter);
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
