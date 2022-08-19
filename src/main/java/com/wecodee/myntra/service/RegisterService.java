package com.wecodee.myntra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wecodee.myntra.constant.ResponseMessage;
import com.wecodee.myntra.constant.ResponseStatusCode;
import com.wecodee.myntra.model.Register;
import com.wecodee.myntra.repository.RegisterRepository;
import com.wecodee.myntra.responseDTO.ApiResponse;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	public ApiResponse<Register> create(Register user) {
		this.registerRepository.save(user);
		if (user != null) {
			return ApiResponse.success(ResponseMessage.REGISTER_SUCCESS.getMessage(), user);
		}
		return ApiResponse.failure(ResponseMessage.REGISTER_FAILED.getMessage());
	}

//------------------------------------------------------------------------------------------
	
	public ApiResponse<List<Register>> getAll(int id)
	{
	 List<Register> allUser =	this.registerRepository.findAll();
	 if (allUser != null) {
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), allUser);
		}
		return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
	}

	
//	public String getUsers(int id) {
//		this.registerRepository.findAll();
//		return "Fetched All the records";
//	}
	

//	// Save user
//	public void registerUser(Register register) {
//
//		this.registerRepository.save(register);
//
//	}

}
