package com.commnunication.blog.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public ForbiddenException(String code, String message) {
		super(HttpStatus.FORBIDDEN, code, message);
	}
}
