package com.pmarlen.rest.dto;

import java.io.Serializable;

/**
 *
 * @author alfredo
 */
public class IAmAliveDTOPackage implements Serializable{
	private int statusCode;

	public IAmAliveDTOPackage() {
	}

	public IAmAliveDTOPackage(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}