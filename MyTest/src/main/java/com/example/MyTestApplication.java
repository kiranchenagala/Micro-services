package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTestApplication {

	static Logger log = Logger.getLogger(MyTestApplication.class.getName());
	public static void main(String[] args) {
		log.info("++===========++"+System.getProperty("spring.config.name"));
		SpringApplication.run(MyTestApplication.class, args);
	}
}
