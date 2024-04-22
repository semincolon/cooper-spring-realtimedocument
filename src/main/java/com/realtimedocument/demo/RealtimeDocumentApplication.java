package com.realtimedocument.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.realtimedocument.demo.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class RealtimeDocumentApplication {
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RealtimeDocumentApplication.class, args);
	}

}
