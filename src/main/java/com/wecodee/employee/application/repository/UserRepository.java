package com.wecodee.employee.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.employee.application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public User getByUserId(String userId);

}
