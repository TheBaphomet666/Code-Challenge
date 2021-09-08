package com.example.codechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CodeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeChallengeApplication.class, args);
	}

}
