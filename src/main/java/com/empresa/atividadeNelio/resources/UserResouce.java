package com.empresa.atividadeNelio.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.atividadeNelio.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Emerson Gabriel", "emersong730@gmail.com", "545645646", "iftm");
		return ResponseEntity.ok().body(u);
	}
}
