package com.master.slf.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class DemoHttpSessionListener implements HttpSessionListener {
	
	private static final Logger logger = Logger.getLogger(DemoHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("DemoHttpSessionListener.sessionCreated() invoked");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("DemoHttpSessionListener.sessionDestroyed() invoked");
	}

}
