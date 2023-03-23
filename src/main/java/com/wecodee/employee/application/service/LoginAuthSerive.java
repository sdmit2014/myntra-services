package com.wecodee.employee.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.employee.application.admin.dto.LoginDTO;
import com.wecodee.employee.application.constant.ResponseMessage;
import com.wecodee.employee.application.model.Register;
import com.wecodee.employee.application.repository.RegisterRepository;
import com.wecodee.employee.application.responseDTO.ApiResponse;

@Service
public class LoginAuthSerive {

	@Autowired
	private RegisterRepository registerRepository;

	public ApiResponse<Register> loginAuthenticationService(LoginDTO loginDTO) {

		Register register = registerRepository.findByUserName(loginDTO.getUserName());

		if (register != null) {
			if (register.getPassword().equals(loginDTO.getPassword())) {
				return ApiResponse.success(ResponseMessage.LOGIN_SUCCESS.getMessage(), register);
			} else {
				return ApiResponse.failure(ResponseMessage.INVALID_PASSWORD.getMessage());
			}
		}
		return ApiResponse.failure(ResponseMessage.USER_NOT_FOUND.getMessage());
	}

}
