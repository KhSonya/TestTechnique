package com.commnunication.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commnunication.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


	/**
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

}
