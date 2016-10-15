package com.master.slf.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class DemoFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(DemoFilter.class);


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("doFilter");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("init demo filter");
	}

	@Override
	public void destroy() {
		logger.debug("destroy demo filter");
	}

}
