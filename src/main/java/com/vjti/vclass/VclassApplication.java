package com.vjti.vclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.vjti")
@EntityScan("com.vjti.*")
@EnableJpaRepositories(basePackages = "com.vjti")
@SpringBootApplication
public class VclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(VclassApplication.class, args);
	}

}
