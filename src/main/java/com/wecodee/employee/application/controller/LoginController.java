package com.wecodee.employee.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.employee.application.admin.dto.LoginDTO;
import com.wecodee.employee.application.model.Register;
import com.wecodee.employee.application.responseDTO.ApiResponse;
import com.wecodee.employee.application.service.LoginAuthSerive;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginAuthSerive loginAuthSerive;
	
	@PostMapping("/auth")
	public ApiResponse<Register> logiAuthenticationController(@RequestBody LoginDTO loginDTO){
		return this.loginAuthSerive.loginAuthenticationService(loginDTO);	
	}

}
