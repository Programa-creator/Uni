package com.example.uni;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling // Включає розклади завдань
public class UniApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniApplication.class, args);
	}
}
