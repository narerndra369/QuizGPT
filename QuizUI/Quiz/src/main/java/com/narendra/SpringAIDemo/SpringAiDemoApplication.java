package com.narendra.SpringAIDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.narendra.models",
	    "com.narendra.SpringAIDemo",
	    "com.narendra.models.repository"
	})
@EnableMongoRepositories(basePackages = "com.narendra.models.repository")
public class SpringAiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiDemoApplication.class, args);
	}

}
