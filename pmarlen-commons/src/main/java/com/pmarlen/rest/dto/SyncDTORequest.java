package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class SyncDTORequest implements Serializable{
	private String sucursalId;
	private String cajaId;
	private String sessionId;
	private String loggedIn;
	private String userAgent;
	private boolean sending;
	private List<ES> esList;		

	public SyncDTORequest() {
		this.sucursalId = "1";
		this.cajaId = "1";
		this.sessionId = "-";
		this.loggedIn = "-";
		this.userAgent = "-";
		this.sending = false;
		this.esList = new ArrayList<>();
	}

	public SyncDTORequest(String sucursalId, String cajaId, String sessionId, String loggedIn, String userAgent, boolean sending) {
		this.sucursalId = sucursalId;
		this.cajaId = cajaId;
		this.sessionId = sessionId;
		this.loggedIn = loggedIn;
		this.userAgent = userAgent;
		this.sending = sending;
		this.esList = new ArrayList<>();
	}
	
	
	/**
	 * @return the sucursalId
	 */
	public String getSucursalId() {
		return sucursalId;
	}

	/**
	 * @param sucursalId the sucursalId to set
	 */
	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}

	/**
	 * @return the cajaId
	 */
	public String getCajaId() {
		return cajaId;
	}

	/**
	 * @param cajaId the cajaId to set
	 */
	public void setCajaId(String cajaId) {
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
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the sending
	 */
	public boolean isSending() {
		return sending;
	}

	/**
	 * @param sending the sending to set
	 */
	public void setSending(boolean sending) {
		this.sending = sending;
	}

	/**
	 * @return the esList
	 */
	public List<ES> getEsList() {
		return esList;
	}

	/**
	 * @param esList the esList to set
	 */
	public void setEsList(List<ES> esList) {
		this.esList = esList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SyncDTORequest{");
		sb.append("sucursalId=").append(sucursalId).
				append(", cajaId=").append(cajaId).
				append(", sessionId=").append(sessionId).
				append(", loggedIn=").append(loggedIn).
				append(", userAgent=").append(userAgent).
				append(", sending=").append(sending).
				append(", esList=").append(esList).append("}");
		return sb.toString();
	}
	
	
}
