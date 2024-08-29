package com.mapping.app;

import java.io.Serial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mapping.app.service.UserService;

@SpringBootApplication
public class MappingApplication {


	public static void main(String[] args) {
		SpringApplication.run(MappingApplication.class, args);
		
		UserService service=new UserService();
		service.testMap();
	}

	
}
