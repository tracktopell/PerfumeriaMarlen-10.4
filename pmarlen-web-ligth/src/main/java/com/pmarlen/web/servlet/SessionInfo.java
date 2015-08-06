/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.web.servlet;

import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alfredo
 */
public class SessionInfo extends AppSessionInfo{
	
	private HttpSession session;
	private String userName;
	private String lastVisitedPage;


	public SessionInfo(HttpSession session, String userName) {
		super("-");
		this.session = session;
		this.userName = userName;
		this.lastVisitedPage = "home";
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

	public String getLastAccessedTimeDiff() {
		long diffInSeconds = (System.currentTimeMillis() - session.getLastAccessedTime()) / 1000;
		return getDiff(diffInSeconds);
	}

	public void setLastVisitedPage(String lastVisitedPage) {
		this.lastVisitedPage = lastVisitedPage;
	}

	public String getLastVisitedPage() {
		return lastVisitedPage;
	}
}
