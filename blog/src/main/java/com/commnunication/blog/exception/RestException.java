package com.commnunication.blog.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
	 * Global Exception.
	 */
	@Getter
	public class RestException extends RuntimeException {

		private static final long serialVersionUID = -8041597068126222870L;

		private final HttpStatus httpStatus;
		private final String code;
		private final String message;
		private final transient Object data;

		public RestException(HttpStatus httpStatus, String code, String message) {
			super(message);
			this.httpStatus = httpStatus;
			this.code = code;
			this.message = message;
			this.data = null;
		}

		public RestException(HttpStatus httpStatus, String code, String message, Object data) {
			super(message);
			this.httpStatus = httpStatus;
			this.code = code;
			this.message = message;
			this.data = data;
		}
}
