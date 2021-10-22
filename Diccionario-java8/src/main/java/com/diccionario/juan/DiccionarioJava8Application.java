package com.diccionario.juan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class DiccionarioJava8Application {

	public static void main(String[] args) {
		SpringApplication.run(DiccionarioJava8Application.class, args);
	}

}
