package com.master.slf.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoServletContextListener implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(DemoServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("DemoServletContextListener.contextInitialized() invoked [" + sce.getServletContext() + "]");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("DemoServletContextListener.contextDestroyed() invoked");
	}

}
