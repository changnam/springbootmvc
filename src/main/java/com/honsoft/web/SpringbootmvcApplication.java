package com.honsoft.web;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.honsoft.web.entity.Car;
import com.honsoft.web.repository.CarRepository;

@SpringBootApplication
@ServletComponentScan
public class SpringbootmvcApplication implements CommandLineRunner {
	@Autowired
	private CarRepository carRepository;
	
	private static Logger logger = LoggerFactory.getLogger(SpringbootmvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Let's inspect the beans provided by Spring Boot:");

		List<Car> allCars = (List<Car>) carRepository.findAll();
		for (Car car : allCars) {
			logger.info(car.getName());
		}
		
	}

}
