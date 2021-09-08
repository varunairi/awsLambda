package com.varuntech.vo;

import java.io.Serializable;

public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3812761073119991284L;
	private String message;
	private String errorCode;
	public Response(String message, String errorCode) {
		super();
		this.message=message;
		this.errorCode=errorCode;
	}
	public final String getMessage() {
		return message;
	}
	public final String getErrorCode() {
		return errorCode;
	}

}
