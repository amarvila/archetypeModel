package com.everis.d4i.project_x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

}

