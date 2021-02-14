package com.paypal.bfs.test.employeeserv.exception;

import java.io.Serializable;

import lombok.Data;
@Data
public class CustomException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8485815963612299041L;
	int statusCode;
    String message;
	public CustomException(String s) {
		// Call constructor of parent Exception
		super(s);
	}

	public CustomException(String message, Throwable cause, int code) {
		super(message, cause);
		this.statusCode = code;
		this.message = message;
	}

	public CustomException(String message, int code) {
		super(message);
		this.statusCode = code;
		this.message = message;
	}

	public CustomException(Throwable cause, int code) {
		super(cause);
		this.statusCode = code;
	}

}
