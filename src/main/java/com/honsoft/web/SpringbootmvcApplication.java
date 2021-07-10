package com.honsoft.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmvcApplication.class, args);
	}

}
