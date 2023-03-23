package com.wecodee.employee.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.employee.application.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
	
	public Register findByUserName(String userName);

}
