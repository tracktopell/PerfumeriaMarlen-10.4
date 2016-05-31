/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.model;

/**
 *
 * @author alfredo
 */
public class Caja {
	private int id;
	private String nombre;
	private String clave;
	private boolean prohibidoVentaReg;
	private boolean prohibidoVentaOpo;

	public Caja() {
	}

	public Caja(int id) {
		this.id = id;
	}

	public Caja(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Caja)obj).id == this.id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the prohibidoVentaReg
	 */
	public boolean isProhibidoVentaReg() {
		return prohibidoVentaReg;
	}

	/**
	 * @param prohibidoVentaReg the prohibidoVentaReg to set
	 */
	public void setProhibidoVentaReg(boolean prohibidoVentaReg) {
		this.prohibidoVentaReg = prohibidoVentaReg;
	}

	/**
	 * @return the prohibidoVentaOpo
	 */
	public boolean isProhibidoVentaOpo() {
		return prohibidoVentaOpo;
	}

	/**
	 * @param prohibidoVentaOpo the prohibidoVentaOpo to set
	 */
	public void setProhibidoVentaOpo(boolean prohibidoVentaOpo) {
		this.prohibidoVentaOpo = prohibidoVentaOpo;
	}

}
