package com.honsoft.web.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MySessionListener implements HttpSessionListener {
	private static Logger logger = LoggerFactory.getLogger(MySessionListener.class);
	
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	logger.info("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	logger.info("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
	
}

