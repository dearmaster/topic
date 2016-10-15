package com.master.slf.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class DemoServletContextAttributeListener implements ServletContextAttributeListener {
	
	private static final Logger logger = Logger.getLogger(DemoServletContextAttributeListener.class);

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		logger.debug("DemoServletContextAttributeListener.attributeAdded() invoked [" + event.getName() + " - " + event.getValue() + "]");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		logger.debug("DemoServletContextAttributeListener.attributeRemoved() invoked [" + event.getName() + " - " + event.getValue() + "]");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		logger.debug("DemoServletContextAttributeListener.attributeReplaced() invoked [" + event.getName() + " - " + event.getValue() + "]");
	}

}
