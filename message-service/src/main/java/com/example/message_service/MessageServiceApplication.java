package com.example.message_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
    
    try {
      Thread.sleep(7000);
    } catch (InterruptedException ignored) {
    }
    SpringApplication.run(MessageServiceApplication.class, args);
	}

}
