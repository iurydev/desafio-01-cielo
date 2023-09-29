package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableWebMvc
public class Desafio01CieloApplication {

	public static void main(String[] args) {
		SpringApplication.run(Desafio01CieloApplication.class, args);
		System.out.println("Running App");
	}

}
