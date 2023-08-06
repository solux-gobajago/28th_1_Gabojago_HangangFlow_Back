package com.hangangFlow.hangangFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EntityScan(basePackages = {"com.hangangFlow.hangangFlow.dto"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hangangFlow.hangangFlow.domain")

public class HangangFlowBackApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HangangFlowBackApplication.class, args);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}

}
