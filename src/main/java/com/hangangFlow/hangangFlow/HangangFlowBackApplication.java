package com.hangangFlow.hangangFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HangangFlowBackApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(HangangFlowBackApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
