package com.example.NygrydLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NygrydLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NygrydLibraryApplication.class, args);
	}

}
