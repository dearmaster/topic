package com.master.slf.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class DemoHttpSessionAttributeListener implements HttpSessionAttributeListener {
	
	private static final Logger logger = Logger.getLogger(DemoHttpSessionAttributeListener.class);

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		logger.debug("DemoHttpSessionAttributeListener.attributeAdded() invoked");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		logger.debug("DemoHttpSessionAttributeListener.attributeRemoved() invoked");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		logger.debug("DemoHttpSessionAttributeListener.attributeReplaced() invoked");
	}

}
