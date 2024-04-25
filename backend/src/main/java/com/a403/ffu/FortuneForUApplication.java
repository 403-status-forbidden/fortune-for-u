package com.a403.ffu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FortuneForUApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortuneForUApplication.class, args);
	}
}
