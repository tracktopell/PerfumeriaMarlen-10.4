package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class IAmAliveDTORequest implements Serializable{
	private int sucursalId;
	private int cajaId;
	private String sessionId;
	private String loggedIn;
	private UserAgent userAgent;
	private CorteCajaDTO corteCajaDTO;
	private String  devicesInfoUSB;
	private byte[]  currentSnapchotImg;
	private Long    currentSnapshotTime;	

	public IAmAliveDTORequest() {
	}

	/**
	 * @return the sucursalId
	 */
	public int getSucursalId() {
		return sucursalId;
	}

	/**
	 * @param sucursalId the sucursalId to set
	 */
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	/**
	 * @return the cajaId
	 */
	public int getCajaId() {
		return cajaId;
	}

	/**
	 * @param cajaId the cajaId to set
	 */
	public void setCajaId(int cajaId) {
		this.cajaId = cajaId;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the loggedIn
	 */
	public String getLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the userAgent
	 */
	public UserAgent getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	public CorteCajaDTO getCorteCajaDTO() {
		return corteCajaDTO;
	}

	public void setCorteCajaDTO(CorteCajaDTO corteCajaDTO) {
		this.corteCajaDTO = corteCajaDTO;
	}

	public void setCurrentSnapchotImg(byte[] currentSnapchotImg) {
		this.currentSnapchotImg = currentSnapchotImg;
	}

	public byte[] getCurrentSnapchotImg() {
		return currentSnapchotImg;
	}

	public void setCurrentSnapshotTime(Long currentSnapshotTime) {
		this.currentSnapshotTime = currentSnapshotTime;
	}

	public Long getCurrentSnapshotTime() {
		return currentSnapshotTime;
	}

	public String getDevicesInfoUSB() {
		return devicesInfoUSB;
	}

	public void setDevicesInfoUSB(String devicesInfoUSB) {
		this.devicesInfoUSB = devicesInfoUSB;
	}
	
}
