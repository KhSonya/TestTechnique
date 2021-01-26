package com.commnunication.blog.exception;

import org.springframework.http.HttpStatus;

public  class BadRequestException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public BadRequestException(String code, String message) {
		super(HttpStatus.BAD_REQUEST, code, message);
	}

}
