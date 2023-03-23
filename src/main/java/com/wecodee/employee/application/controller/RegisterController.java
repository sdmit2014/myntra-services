package com.wecodee.employee.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.employee.application.model.Register;
import com.wecodee.employee.application.responseDTO.ApiResponse;
import com.wecodee.employee.application.service.RegisterService;

@RequestMapping("/register")
@RestController
public class RegisterController<T> {

	@Autowired
	private RegisterService registerService;

	@PostMapping("/create")
	public ApiResponse<Register> create(@RequestBody Register user) 
	{
		return registerService.create(user);
	}

	@GetMapping("/getAll")
	public ApiResponse<List<Register>> getAll()
	{
		return registerService.getAll(0);
	}

}
