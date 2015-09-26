/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.rest.dto;

import java.io.Serializable;

/**
 *
 * @author alfredo
 */
public class DES implements Serializable{
	private int c;
	private String cb;
	private double p;
	private int aId;
	/**
	 * @return the c
	 */
	public int getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(int c) {
		this.c = c;
	}

	/**
	 * @return the cb
	 */
	public String getCb() {
		return cb;
	}

	/**
	 * @param cb the cb to set
	 */
	public void setCb(String cb) {
		this.cb = cb;
	}

	/**
	 * @return the p
	 */
	public double getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(double p) {
		this.p = p;
	}

	/**
	 * @return the aId
	 */
	public int getaId() {
		return aId;
	}

	/**
	 * @param aId the aId to set
	 */
	public void setaId(int aId) {
		this.aId = aId;
	}
}
