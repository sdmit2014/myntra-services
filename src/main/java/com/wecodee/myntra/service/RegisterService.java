package com.wecodee.myntra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.myntra.model.Register;
import com.wecodee.myntra.repository.RegisterRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

    //Save user
	public void registerUser(Register register) {

		this.registerRepository.save(register);

	}
	
    //Get user
	public String getUsers(int id) {
	 this.registerRepository.findAll();		
	 return "Fetched All the records";
	}

}
