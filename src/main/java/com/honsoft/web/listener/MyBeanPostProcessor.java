package com.honsoft.web.listener;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBeanPostProcessor implements BeanPostProcessor {
	private static Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);
	private static int beanCnt = 0;
	
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	public MyBeanPostProcessor(@Qualifier("mysqlDataSource") DataSource dataSource){
		logger.info("********************* instanciating MyBeanPostProcessor ***********************");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info(beanCnt++ + "  ++++++++++ before initialization ++++++++++++   "+ beanName);
		jdbcTemplate.update("insert into beans (beanname,description) values (?,?)",beanName,"beforeinit");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info(beanCnt+ "   ++++++++++ after initialization ++++++++++++   "+ beanName);
		return bean;
	}
}
