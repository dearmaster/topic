package com.master.demo.struts2.action;

import com.master.demo.struts2.model.Report;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.util.Map;


/**
 * Created by Administrator on 2016/10/4.
 */
public class ReportAction implements Action, ModelDriven<Report> {

    private static final Logger logger = Logger.getLogger(ReportAction.class);

    private Report report = new Report();

    @Override
    public String execute() throws Exception {

        ActionContext context = ActionContext.getContext();
        Map<String, Object> application = context.getApplication();
        Map<String, Object> session = context.getSession();
        Map<String, Object> request = (Map<String, Object>) context.get("request");

        request.put("greeting", "Hello World!");

        ActionContext.getContext().put("slogan", "Day Day Up");

        session.put("report", report);

        Integer counter = (Integer) application.get("counter");
        if(counter == null)
            counter = 0;
        counter++;
        application.put("counter", counter);

        if(logger.isDebugEnabled()) {
            logger.debug("The upload report request has been executed " + counter + " time(s).");
        }

        if(logger.isDebugEnabled()) {
            logger.debug(report);
        }
        return SUCCESS;
    }

    @Override
    public Report getModel() {
        return report;
    }
}