package com.commnunication.blog.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public AlreadyExistException(String code, String message) {
		super(HttpStatus.CONFLICT, code, message);
	}

}
