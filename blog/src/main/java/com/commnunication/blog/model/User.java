package com.commnunication.blog.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User implements Serializable, UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -285097529955732587L;

	/**
	 * User id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	/**
	 * User login
	 */
	@Column(name = "username")
	private String username;

	/**
	 * User login
	 */
	@Column(name = "email")
	private String email;

	/**
	 * User password
	 */
	@Column(name = "password")
	private String password;

	/*
	 * @OneToMany() public List<Post> posts;
	 * 
	 * @OneToMany() public List<Comment> comments;
	 */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
