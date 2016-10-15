package com.master.slf.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.log4j.Logger;

public class DemoServletRequestListener implements ServletRequestListener {
	
	private static final Logger logger = Logger.getLogger(DemoServletRequestListener.class);

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.debug("DemoServletRequestListener.requestDestroyed() invoked [" + sre.getServletRequest() + "]");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		logger.debug("DemoServletRequestListener.requestInitialized() invoked [" + sre.getServletRequest() + "]");
	}

}
