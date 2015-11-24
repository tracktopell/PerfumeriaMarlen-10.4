/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.web.servlet;

import com.pmarlen.model.Constants;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alfredo
 */
public class SessionInfo {
	private static long applicationContextCreationTime;
	
	private HttpSession session;
	private String userName;
	private String lastVisitedPage;
	private String remoteAddr;
	private String userAgent;

	public SessionInfo(HttpSession session, String userName) {
		this.session = session;
		this.userName = userName;
		this.lastVisitedPage = "home";
		this.remoteAddr		 = "-";
		this.userAgent		 = "-";
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private Date creationDate;

	public Date getCreationDate() {
		if (creationDate == null) {
			creationDate = new Date(session.getCreationTime());
		}
		return creationDate;
	}

	public Date getLastAccessedTime() {
		return new Date(session.getLastAccessedTime());
	}

	public String getCreationTimeDiff() {
		long diffInSeconds = (System.currentTimeMillis() - session.getCreationTime()) / 1000;
		return Constants.getDiff(diffInSeconds);
	}

	public String getLastAccessedTimeDiff() {
		long diffInSeconds = (System.currentTimeMillis() - session.getLastAccessedTime()) / 1000;
		return Constants.getDiff(diffInSeconds);
	}


	public void setLastVisitedPage(String lastVisitedPage) {
		this.lastVisitedPage = lastVisitedPage;
	}

	public String getLastVisitedPage() {
		return lastVisitedPage;
	}

	public static String getLifeTimeUpDiff(){
		long diffInSeconds = (System.currentTimeMillis() - applicationContextCreationTime) / 1000;
		return Constants.getDiff(diffInSeconds);
	}

	public static void setApplicationContextCreationTime(long applicationContextCreationTime) {
		SessionInfo.applicationContextCreationTime = applicationContextCreationTime;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
