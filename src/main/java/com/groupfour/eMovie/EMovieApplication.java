package com.groupfour.eMovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class EMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(EMovieApplication.class, args);
	}

}
