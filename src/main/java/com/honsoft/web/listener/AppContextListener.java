package com.honsoft.web.listener;

import java.util.List;
import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.honsoft.web.db.DBConnectionManager;


@WebListener
public class AppContextListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	
    	String url = ctx.getInitParameter("DBURL");
    	String u = ctx.getInitParameter("DBUSER");
    	String p = ctx.getInitParameter("DBPWD");
 
    	//ctx.getServlet("dispatcher").getServletConfig().g
    	logger.info("--------------------------------------------------------------");
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	//DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
    	//dbManager.closeConnection();
    	logger.info("Database connection closed for Application.");
    	
    }
	
}

