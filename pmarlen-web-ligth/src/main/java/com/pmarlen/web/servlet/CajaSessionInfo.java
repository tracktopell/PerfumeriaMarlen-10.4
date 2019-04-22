/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.web.servlet;

import com.pmarlen.backend.model.InfoCaja;
import java.io.Serializable;

/**
 *
 * @author alfredo
 */
public class CajaSessionInfo extends AppSessionInfo  implements Serializable{
	
	public static final long TIMEOUT = 70000;
	
	private String sessionId;
	private String sucursal;
	private String caja;
	private String loggedIn;
	private boolean requestedResetAll;	
	private long lastAccesedTime;
	private InfoCaja infoCaja;
	
	public CajaSessionInfo() {
		super("-");
		this.sucursal = "?";
		this.caja = "?";
		this.loggedIn = "?";
		this.infoCaja= null;
		requestedResetAll = false;
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
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the caja
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(String caja) {
		this.caja = caja;
	}

	/**
	 * @return the lastAccesedTime
	 */
	public long getLastAccesedTime() {
		return lastAccesedTime;
	}

	/**
	 * @param lastAccesedTime the lastAccesedTime to set
	 */
	public void setLastAccesedTime(long lastAccesedTime) {
		this.lastAccesedTime = lastAccesedTime;
	}
	
	public String getLastAccesedTimeDiff() {		
		long diffInSeconds = (System.currentTimeMillis() - lastAccesedTime) / 1000;
		return getDiff(diffInSeconds);
	}

	public boolean isTimeout() {
		return (System.currentTimeMillis() - lastAccesedTime) >= TIMEOUT;
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
		this.loggedIn = loggedIn.equalsIgnoreCase("null")?"?":loggedIn;
	}
	
	public boolean isRequestedResetAll() {
		return requestedResetAll;
	}

	public void setRequestedResetAll(boolean b) {
		requestedResetAll = b;
	}

	public void setInfoCaja(InfoCaja infoCaja) {
		this.infoCaja = infoCaja;
	}

	public InfoCaja getInfoCaja() {
		return infoCaja;
	}	
}
