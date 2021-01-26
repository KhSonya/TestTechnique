package com.commnunication.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * Data not found exception. It will return the Not found HTTP status code
 */
public class DataNotFoundException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public DataNotFoundException(String code, String message) {
		super(HttpStatus.NOT_FOUND, code, message);
	}

}
