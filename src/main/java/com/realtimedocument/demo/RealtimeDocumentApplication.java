package com.realtimedocument.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.realtimedocument.demo.repository.UserRepository;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RealtimeDocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeDocumentApplication.class, args);
	}

}
