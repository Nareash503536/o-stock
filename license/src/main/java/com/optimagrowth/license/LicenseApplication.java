package com.optimagrowth.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class LicenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}

}
