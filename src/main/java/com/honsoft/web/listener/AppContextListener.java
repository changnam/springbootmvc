package com.honsoft.web.listener;

import java.util.List;
import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

//import com.honsoft.web.db.DBConnectionManager;


@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	
    	String url = ctx.getInitParameter("DBURL");
    	String u = ctx.getInitParameter("DBUSER");
    	String p = ctx.getInitParameter("DBPWD");
    	
    	//create database connection from init parameters and set it to context
    	//DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
    	//ctx.setAttribute("DBManager", dbManager);
    	System.out.println("Database connection initialized for Application.");
    	
    	Map<String, ? extends ServletRegistration> servletRegistrations = ctx.getServletRegistrations();
		

		System.out.println("------------------ registerd servlets -------------------------");
    	for (String servletName : servletRegistrations.keySet()) {
    		//System.out.println("==> "+);
			System.out.println("name: " + servletName + " , class: " +  servletRegistrations.get(servletName).getClassName() + " , mapping: " + servletRegistrations.get(servletName).getMappings() );
    	}
    	System.out.println("--------------------------------------------------------------");
    	
    	Map<String, ? extends FilterRegistration> filterRegistrations = ctx.getFilterRegistrations();
    	
    	System.out.println("------------------ registerd filters -------------------------");
    	for (String filterName : filterRegistrations.keySet()) {
    		//System.out.println("==> "+);
			System.out.println("name: " + filterName + " , class: " +  filterRegistrations.get(filterName).getClassName() + " , mapping: " + filterRegistrations.get(filterName).getUrlPatternMappings() + filterRegistrations.get(filterName).getServletNameMappings());
    	}
    	
    	//ctx.getServlet("dispatcher").getServletConfig().g
    	System.out.println("--------------------------------------------------------------");
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	//DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
    	//dbManager.closeConnection();
    	System.out.println("Database connection closed for Application.");
    	
    }
	
}

