package com.microservicearch.lastnameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@RequestMapping("/api")
public class LastnameServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(LastnameServiceApplication.class, args);
	}

	@Autowired
	private GreetingClient greetingClient;

	@GetMapping("/add-last-name/{name}/{lastName}")
	public String addLastName(@PathVariable String name, @PathVariable String lastName) {
		System.out.print("In lastname Service going to firstname Service");

		String greeting = greetingClient.greet(name);
		System.out.print("In lastname Service comming from firstname Service");

		return greeting + " " + lastName;
	}
}

@FeignClient(name = "firstNameService")
interface GreetingClient {
	@GetMapping("/api/hlw/{name}")
	String greet(@PathVariable("name") String name);
}
