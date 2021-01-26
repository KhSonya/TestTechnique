package com.commnunication.blog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commnunication.blog.exception.AlreadyExistException;
import com.commnunication.blog.exception.BadRequestException;
import com.commnunication.blog.model.User;
import com.commnunication.blog.repository.UserRepository;
import com.commnunication.blog.rest.dto.UserDto;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public void saveUser(UserDto user) {

		if (user.getEmail() == null || user.getEmail().isEmpty())
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Email is missing");

		if (user.getPassword() == null || user.getPassword().isEmpty())
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Password is missing");

		if (user.getUsername() == null || user.getUsername().isEmpty())
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Username is missing");

		User userToCheck = userRepository.findByEmail(user.getEmail());
		if (userToCheck != null)
			throw new AlreadyExistException("USER_ALREADY_EXIST", "User already exist!");


		User userToSave = new User();

		BeanUtils.copyProperties(user, userToSave);
		userToSave.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(userToSave);

	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		return userRepository.findByEmail(email);

	}

}
