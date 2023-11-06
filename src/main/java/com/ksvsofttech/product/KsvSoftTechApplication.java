package com.ksvsofttech.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EnableCaching
public class KsvSoftTechApplication {
	public static void main(String[] args) {
		SpringApplication.run(KsvSoftTechApplication.class, args);

//		SpringApplication app = new SpringApplication(KsvSoftTechApplication.class);
//		
//		app.setLogStartupInfo(false);
//		app.run(args);
	}
}
