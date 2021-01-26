package com.commnunication.blog.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commnunication.blog.rest.dto.UserDto;
import com.commnunication.blog.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping()
	public Object saveUser(@RequestBody UserDto userDto) {
		userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@GetMapping()
	public Object getUser(@RequestParam String email) {
		userService.loadUserByUsername(email);
		return ResponseEntity.status(HttpStatus.OK).body(userService.loadUserByUsername(email));
	}
}
