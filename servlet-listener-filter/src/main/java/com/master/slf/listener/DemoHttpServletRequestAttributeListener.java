package com.master.slf.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class DemoHttpServletRequestAttributeListener implements ServletRequestAttributeListener {
	
	private static final Logger logger = Logger.getLogger(DemoHttpServletRequestAttributeListener.class);

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		logger.debug(stes[2]);
		logger.debug("DemoHttpServletRequestAttributeListener.attributeAdded() invoked [" + srae.getName() + " - " + srae.getValue() + "]");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		logger.debug("DemoHttpServletRequestAttributeListener.attributeRemoved() invoked");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		logger.debug("DemoHttpServletRequestAttributeListener.attributeReplaced() invoked");
	}

}
