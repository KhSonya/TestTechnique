package com.commnunication.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.commnunication.blog.exception.BadRequestException;

public class AppAuthProvider extends DaoAuthenticationProvider {

	@Autowired
	UserService userDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String name = auth.getName();
		String password = auth.getCredentials().toString();
		UserDetails user = userDetailsService.loadUserByUsername(name);

		if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		} 
			throw new BadRequestException("INVALID_PASSWORD", "Mot de passe incorrect");


	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
