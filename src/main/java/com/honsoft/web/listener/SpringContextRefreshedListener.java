package com.honsoft.web.listener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
public class SpringContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger logger = LoggerFactory.getLogger(SpringContextRefreshedListener.class);
	
	private ApplicationContext context;
	private HashSet<String> beansSet = new HashSet<>();
	
	 public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("===================> Handling context re-freshed event. ");
		
		this.context = event.getApplicationContext();
		
		int cnt = 1;
		String[] beanNames = context.getBeanDefinitionNames();
		beansSet.addAll(Arrays.asList(beanNames));

		logger.info("== list of beans (" + beanNames.length + ")==");
		for (String beanName : beanNames) {
			logger.info(cnt++ + " , " + beanName + " , " + context.getBean(beanName).getClass().toString());
		}
		logger.info("====================");

		cnt = 1;
		String[] allBeans = printBeans();
		logger.info("=== all beans including beans registered by spring (" + allBeans.length + ")====");

		// List<String> singletonArrays = Arrays.asList(allBeans);

		for (String bean : allBeans) {
			if (!beansSet.contains(bean))
				// allBeans[singletonArrays.indexOf(bean)] = "manual "+singleton; // ignoring
				// error handling
				logger.info(
						cnt++ + " , <== manual ==> " + bean + " , " + context.getBean(bean).getClass().toString());
			else
				logger.info(cnt++ + " , " + bean + " , " + context.getBean(bean).getClass().toString());
		}
	        
		logger.info("====================== Handling context re-freshed event Ended. ====================== ");
	}
	 

	private String[] printBeans() {
		AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
		if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
			String[] singletonNames = ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames();

			for (String singleton : singletonNames) {
				// logger.info(singleton);
			}
			return singletonNames;
		}
		return null;
	}
	
}