/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class ES implements Serializable{
	private int sId;
	private int numC;
	private int idCte;
	private long t;
	private double ir;
	private int fpId;
	private String ticket;
	private List<DES> d;	
	private int tm;
	private String u;
	
	/**
	 * @return the sId
	 */
	public int getsId() {
		return sId;
	}

	/**
	 * @param sId the sId to set
	 */
	public void setsId(int sId) {
		this.sId = sId;
	}

	/**
	 * @return the numC
	 */
	public int getNumC() {
		return numC;
	}

	/**
	 * @param numC the numC to set
	 */
	public void setNumC(int numC) {
		this.numC = numC;
	}

	/**
	 * @return the idCte
	 */
	public int getIdCte() {
		return idCte;
	}

	/**
	 * @param idCte the idCte to set
	 */
	public void setIdCte(int idCte) {
		this.idCte = idCte;
	}

	/**
	 * @return the t
	 */
	public long getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(long t) {
		this.t = t;
	}

	/**
	 * @return the ir
	 */
	public double getIr() {
		return ir;
	}

	/**
	 * @param ir the ir to set
	 */
	public void setIr(double ir) {
		this.ir = ir;
	}

	/**
	 * @return the fpId
	 */
	public int getFpId() {
		return fpId;
	}

	/**
	 * @param fpId the fpId to set
	 */
	public void setFpId(int fpId) {
		this.fpId = fpId;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the d
	 */
	public List<DES> getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(List<DES> d) {
		this.d = d;
	}

	/**
	 * @return the tm
	 */
	public int getTm() {
		return tm;
	}

	/**
	 * @param tm the tm to set
	 */
	public void setTm(int tm) {
		this.tm = tm;
	}

	/**
	 * @return the u
	 */
	public String getU() {
		return u;
	}

	/**
	 * @param u the u to set
	 */
	public void setU(String u) {
		this.u = u;
	}
}
