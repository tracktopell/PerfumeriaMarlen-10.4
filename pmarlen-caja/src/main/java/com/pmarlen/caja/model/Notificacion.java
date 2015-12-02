/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.model;

import com.pmarlen.model.Constants;
import java.util.Currency;

/**
 *
 * @author alfredo
 */
public class Notificacion {
	private String id;
	private long fecha;
	private boolean vista;
	private String mensaje;	

	public Notificacion(String id, String mensaje) {
		this.id = id;
		this.fecha = System.currentTimeMillis();
		this.vista = false;
		this.mensaje = mensaje;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public long getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the vista
	 */
	public boolean isVista() {
		return vista;
	}

	/**
	 * @param vista the vista to set
	 */
	public void setVista(boolean vista) {
		this.vista = vista;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String toString() {
		return "Notificacion{["+(vista?"V":"N")+"] HACE:"+Constants.getDiff(System.currentTimeMillis()-fecha)+", MENSAJE:"+mensaje;
	}
}
