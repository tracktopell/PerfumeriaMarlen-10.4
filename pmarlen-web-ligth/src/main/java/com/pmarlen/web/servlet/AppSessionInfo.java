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
public class AppSessionInfo {
	protected long applicationContextCreationTime;
	protected String remoteAddr;

	public AppSessionInfo(String remoteAddr) {
		this.applicationContextCreationTime = System.currentTimeMillis();
		this.remoteAddr		 = remoteAddr;
	}
	
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public long getApplicationContextCreationTime() {
		return applicationContextCreationTime;
	}
	
	public String getCreationTimeDiff() {
		long diffInSeconds = (System.currentTimeMillis() - applicationContextCreationTime) / 1000;
		return getDiff(diffInSeconds);
	}

	protected static String getDiff(long diffInSeconds) {


		long diff[] = new long[]{0, 0, 0, 0};

		diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		diff[0] = (diffInSeconds = (diffInSeconds / 24));

		StringBuffer sb = new StringBuffer();
		if (diff[0] > 0) {
			sb.append(diff[0]);
			sb.append(" D");
		}
		if (diff[1] > 0) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(diff[1]);
			sb.append(" h");
		}
		if (diff[2] > 0) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(diff[2]);
			sb.append(" m");
		}
		if (diff[3] > 0) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(diff[3]);
			sb.append(" s");
		}
		sb.append(".");

		return sb.toString();
	}

}
