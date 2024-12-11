package com.microservicearch.firstNameService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/api")
	public class FirstNameServiceApplication {
		public static void main(String[] args) {
			SpringApplication.run(FirstNameServiceApplication.class, args);
		}

		@GetMapping("/hlw/{name}")
		public String greet(@PathVariable String name) {
			
			System.out.print("In firstname Service going back lastname Service");

			return "Hello " + name;
		}
	}

