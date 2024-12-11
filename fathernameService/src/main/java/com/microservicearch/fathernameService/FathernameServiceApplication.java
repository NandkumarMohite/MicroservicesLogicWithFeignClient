package com.microservicearch.fathernameService;

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
public class FathernameServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FathernameServiceApplication.class, args);
	}

	@Autowired
	private LastNameClient lastNameClient;

	@GetMapping("/add-father-name/{name}/{lastName}/{fatherName}")
	public String addFatherName(@PathVariable String name, @PathVariable String lastName, @PathVariable String fatherName) {
		System.out.print("In Father Service going to Lastname Service");
		String fullName = lastNameClient.addLastName(name, lastName);
		System.out.print("In Father Service coming from Lastname Service");

		return fullName + " (Father: " + fatherName + ")";
	}
}

@FeignClient(name = "lastnameService")
interface LastNameClient {
	@GetMapping("/api/add-last-name/{name}/{lastName}")
	String addLastName(@PathVariable("name") String name, @PathVariable("lastName") String lastName);
}
