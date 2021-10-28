package com.TestAssignment.Blogging_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class BloggingAppApplication {

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
//		System.out.println(PasswordEncoder.encode("asdf12"));
		SpringApplication.run(BloggingAppApplication.class, args);
	}



}
