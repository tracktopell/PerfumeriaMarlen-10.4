/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.EntradaSalida;
import java.sql.Timestamp;

/**
 *
 * @author alfredo
 */
public class ES {
	private int tm;
	private int s;
	private long fc;
	private String u;
	private int c;
	private int fp;
	private int j;
	private double i;
	
	public EntradaSalida reverse(){
		EntradaSalida es = new EntradaSalida();
		es.setTipoMov(tm);
		es.setSucursalId(s);
		es.setFechaCreo(new Timestamp(fc));
		es.setUsuarioEmailCreo(u);
		es.setCfdId(c);
		es.setFormaDePagoId(fp);
		es.setCaja(j);
		es.setFactorIva(i);
		return es;
	}

	public ES() {
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
	 * @return the s
	 */
	public int getS() {
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(int s) {
		this.s = s;
	}

	/**
	 * @return the fc
	 */
	public long getFc() {
		return fc;
	}

	/**
	 * @param fc the fc to set
	 */
	public void setFc(long fc) {
		this.fc = fc;
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
	 * @return the fp
	 */
	public int getFp() {
		return fp;
	}

	/**
	 * @param fp the fp to set
	 */
	public void setFp(int fp) {
		this.fp = fp;
	}

	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}

	/**
	 * @return the i
	 */
	public double getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(double i) {
		this.i = i;
	}
}
