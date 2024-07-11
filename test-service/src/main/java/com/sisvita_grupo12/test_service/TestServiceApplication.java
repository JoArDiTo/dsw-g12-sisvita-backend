package com.sisvita_grupo12.test_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TestServiceApplication {
	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		String test_driver_class_name = dotenv.get("TEST_DRIVER_CLASS_NAME");
		String test_driver_url = dotenv.get("TEST_DRIVER_URL");
		String test_driver_username = dotenv.get("TEST_DRIVER_USERNAME");
		String test_driver_password = dotenv.get("TEST_DRIVER_PASSWORD");

		System.setProperty("TEST_DRIVER_CLASS_NAME", test_driver_class_name);
		System.setProperty("TEST_DRIVER_URL", test_driver_url);
		System.setProperty("TEST_DRIVER_USERNAME", test_driver_username);
		System.setProperty("TEST_DRIVER_PASSWORD", test_driver_password);

		SpringApplication.run(TestServiceApplication.class, args);
	}

}
