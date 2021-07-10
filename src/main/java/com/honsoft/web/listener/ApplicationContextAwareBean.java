package com.honsoft.web.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareBean implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextAwareBean.class);
	
	private ApplicationContext context;
	private HashSet<String> beansSet = new HashSet<>();

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;

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
		logger.info("====================");
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
