package com.wecodee.myntra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wecodee.myntra.model.Register;
import com.wecodee.myntra.service.RegisterService;

@RequestMapping("/register")
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping("/save")
	public String registerUser(@RequestBody Register register) {
		this.registerService.registerUser(register);
		return "Register Successfully";
	}
	
//	public Register getAllUsers(@PathVariable int id) {
//		this.registerService.getUsers(id);
//	}

}
