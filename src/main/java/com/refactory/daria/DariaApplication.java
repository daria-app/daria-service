package com.refactory.daria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class DariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DariaApplication.class, args);
	}

}
