package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringcloudconfigserverApplication {

	public static void main(String[] args) {
		//http://localhost:8001/configprops
		SpringApplication.run(SpringcloudconfigserverApplication.class, args);
	}
}
