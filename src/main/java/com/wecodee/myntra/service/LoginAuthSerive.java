package com.wecodee.myntra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.myntra.admin.dto.LoginDTO;
import com.wecodee.myntra.constant.ResponseMessage;
import com.wecodee.myntra.model.Register;
import com.wecodee.myntra.repository.RegisterRepository;
import com.wecodee.myntra.responseDTO.ApiResponse;

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
