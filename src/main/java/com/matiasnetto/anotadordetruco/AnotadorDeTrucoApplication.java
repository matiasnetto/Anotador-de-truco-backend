package com.matiasnetto.anotadordetruco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class AnotadorDeTrucoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotadorDeTrucoApplication.class, args);
	}

}
