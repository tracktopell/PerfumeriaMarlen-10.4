package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author alfredo
 */
public class IAmAliveDTOPackage implements Serializable{
	private int		statusCode;
	private String  currentPMCajaVersion;

	public IAmAliveDTOPackage() {
	}
		
	public IAmAliveDTOPackage(int statusCode, String currentPMCajaVersion) {
		this.statusCode = statusCode;
		this.currentPMCajaVersion = currentPMCajaVersion;
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

	/**
	 * @return the currentPMCajaVersion
	 */
	public String getCurrentPMCajaVersion() {
		return currentPMCajaVersion;
	}

	/**
	 * @param currentPMCajaVersion the currentPMCajaVersion to set
	 */
	public void setCurrentPMCajaVersion(String currentPMCajaVersion) {
		this.currentPMCajaVersion = currentPMCajaVersion;
	}

	@Override
	public String toString() {
		return "IAmAliveDTOPackage{" + "statusCode=" + statusCode + ", currentPMCajaVersion=" + currentPMCajaVersion + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.statusCode;
		hash = 59 * hash + Objects.hashCode(this.currentPMCajaVersion);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final IAmAliveDTOPackage other = (IAmAliveDTOPackage) obj;
		if (this.statusCode != other.statusCode) {
			return false;
		}
		if (!Objects.equals(this.currentPMCajaVersion, other.currentPMCajaVersion)) {
			return false;
		}
		return true;
	}

	
}