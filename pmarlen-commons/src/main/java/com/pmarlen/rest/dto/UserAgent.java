/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.rest.dto;

/**
 *
 * @author alfredo
 */
public class UserAgent {
	private String version;
	private String os;
	private String javaVersion;
	private String userInSession;
	private String workingDirectory;	

	public UserAgent(String version, String os, String javaVersion, String userInSession, String workingDirectory) {
		this.version = version;
		this.os = os;
		this.javaVersion = javaVersion;
		this.userInSession = userInSession;
		this.workingDirectory = workingDirectory;
	}

	public UserAgent() {
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the javaVersion
	 */
	public String getJavaVersion() {
		return javaVersion;
	}

	/**
	 * @param javaVersion the javaVersion to set
	 */
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	/**
	 * @return the userInSession
	 */
	public String getUserInSession() {
		return userInSession;
	}

	/**
	 * @param userInSession the userInSession to set
	 */
	public void setUserInSession(String userInSession) {
		this.userInSession = userInSession;
	}

	/**
	 * @return the workingDirectory
	 */
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * @param workingDirectory the workingDirectory to set
	 */
	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}
}
