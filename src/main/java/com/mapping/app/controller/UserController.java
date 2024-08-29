package com.mapping.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.app.model.User;
import com.mapping.app.model.UserDto;
import com.mapping.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

	
	@Autowired
	private UserService service;
	
	
	@GetMapping("/users")
	public List<?> getUsers() {
		return service.getAllUser();
	}
	
	@GetMapping("/users/")
	public UserDto getUsers(@RequestParam("id") long id) {
		return service.getUserById(id);
	}
	
	@PostMapping(value = "/addUser",consumes = "application/json", produces = "application/json")
	public String postMethodName(@RequestBody UserDto dto) throws Exception {
		try {
		return service.addUser(dto);
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return null;
	}
	
}
