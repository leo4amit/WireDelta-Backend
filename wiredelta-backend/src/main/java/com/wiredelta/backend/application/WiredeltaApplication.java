package com.wiredelta.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.wiredelta.backend")
@EnableJpaRepositories("com.wiredelta.backend.repository")
@EntityScan("com.wiredelta.backend.entity")
public class WiredeltaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiredeltaApplication.class, args);
	}

}
