package com.userservice.userservice;
import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserserviceApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		String USER_SERVICE_DB_USER = dotenv.get("USER_SERVICE_DB_USER");
		String USER_SERVICE_DB_USERNAME = dotenv.get("USER_SERVICE_DB_USERNAME");
		String USER_SERVICE_DB_PASSWORD = dotenv.get("USER_SERVICE_DB_PASSWORD");

		System.setProperty("USER_SERVICE_DB_USER", USER_SERVICE_DB_USER);
		System.setProperty("USER_SERVICE_DB_USERNAME", USER_SERVICE_DB_USERNAME);
		System.setProperty("USER_SERVICE_DB_PASSWORD", USER_SERVICE_DB_PASSWORD);

		SpringApplication.run(UserserviceApplication.class, args);


	}

}
