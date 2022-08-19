package com.wecodee.myntra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wecodee.myntra.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public User getByUserId(String userId);

}
