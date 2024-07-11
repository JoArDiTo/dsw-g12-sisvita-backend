package com.sisvita_grupo12.vigilance_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VigilanceServiceApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		String vigilance_driver_class_name = dotenv.get("VIGILANCE_DRIVER_CLASS_NAME");
		String vigilance_driver_url = dotenv.get("VIGILANCE_DRIVER_URL");
		String vigilance_driver_username = dotenv.get("VIGILANCE_DRIVER_USERNAME");
		String vigilance_driver_password = dotenv.get("VIGILANCE_DRIVER_PASSWORD");

		String vigilance_mail_host = dotenv.get("VIGILANCE_MAIL_HOST");
		String vigilance_mail_port = dotenv.get("VIGILANCE_MAIL_PORT");
		String vigilance_mail_username = dotenv.get("VIGILANCE_MAIL_USERNAME");
		String vigilance_mail_password = dotenv.get("VIGILANCE_MAIL_PASSWORD");

		System.setProperty("VIGILANCE_DRIVER_CLASS_NAME", vigilance_driver_class_name);
		System.setProperty("VIGILANCE_DRIVER_URL", vigilance_driver_url);
		System.setProperty("VIGILANCE_DRIVER_USERNAME", vigilance_driver_username);
		System.setProperty("VIGILANCE_DRIVER_PASSWORD", vigilance_driver_password);

		System.setProperty("VIGILANCE_MAIL_HOST", vigilance_mail_host);
		System.setProperty("VIGILANCE_MAIL_PORT", vigilance_mail_port);
		System.setProperty("VIGILANCE_MAIL_USERNAME", vigilance_mail_username);
		System.setProperty("VIGILANCE_MAIL_PASSWORD", vigilance_mail_password);

		SpringApplication.run(VigilanceServiceApplication.class, args);
	}

}
