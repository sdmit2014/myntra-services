package com.wecodee.myntra.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.myntra.admin.dto.SearchFilterDTO;
import com.wecodee.myntra.model.MtImages;
import com.wecodee.myntra.model.MtPayment;
import com.wecodee.myntra.model.User;
import com.wecodee.myntra.responseDTO.ApiResponse;
import com.wecodee.myntra.service.UserService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@PostMapping("/create")
	public ApiResponse<User> create(@RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping("/get/{userId}")
	public ApiResponse<User> get(@PathVariable String userId) {
		return userService.get(userId);
	}
	
	@GetMapping("/getAll")
	public ApiResponse<List<User>> getAll(){
		return userService.getAllUsers();
	}

	@DeleteMapping("/delete/{userId}")
	public ApiResponse<User> deleteByUserId(@PathVariable String userId) {
		return userService.deleteByUserId(userId);
	}
	
	@PostMapping("/update")
	public ApiResponse<User> update(@RequestBody User user){
		return userService.update(user);
	}
	
	@PostMapping("/get/approved")
	public ApiResponse<JSONObject> getApprovedRecords(@RequestBody SearchFilterDTO<User> filterDto){
		log.info("approved filterDto :" +filterDto);
		return userService.getApprovedRecords(filterDto);
	}
	
	@PostMapping("/saveimage")
	public ApiResponse<MtImages> saveImages(@RequestBody MtImages mtImages ){
		return userService.saveImages(mtImages);
	}
	
	@PostMapping("/savepayment")
	public ApiResponse<MtPayment> savePayment(@RequestBody MtPayment mtPayment){
		return userService.savePayment(mtPayment);
	}


}
