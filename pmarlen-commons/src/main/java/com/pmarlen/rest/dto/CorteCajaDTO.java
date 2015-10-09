/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.CorteCaja;
import java.sql.Timestamp;

/**
 *
 * @author alfredo
 */
public class CorteCajaDTO {
	    
    /**
    * fecha
    */
    private long fecha;
    
    /**
    * sucursal id
    */
    private int sucursalId;
    
    /**
    * caja
    */
    private int caja;
    
    /**
    * usuario email
    */
    private String usuarioEmail;
    
    /**
    * total
    */
    private double total;
    
    /**
    * comentarios
    */
    private String comentarios;
	
    /**
    * tipo evento
    */
    private Integer tipoEvento;

	public CorteCajaDTO() {
	}

	public CorteCaja reverse(){
		CorteCaja cj= new CorteCaja();
		
		cj.setFecha(new Timestamp(fecha));
		cj.setSucursalId(sucursalId);
		cj.setCaja(caja);
		cj.setUsuarioEmail(usuarioEmail);
		cj.setTotal(total);
		cj.setComentarios(comentarios);
		cj.setTipoEvento(tipoEvento);
		
		return cj;
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
	 * @return the sucursalId
	 */
	public int getSucursalId() {
		return sucursalId;
	}

	/**
	 * @param sucursalId the sucursalId to set
	 */
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	/**
	 * @return the caja
	 */
	public int getCaja() {
		return caja;
	}

	/**
	 * @param caja the caja to set
	 */
	public void setCaja(int caja) {
		this.caja = caja;
	}

	/**
	 * @return the usuarioEmail
	 */
	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	/**
	 * @param usuarioEmail the usuarioEmail to set
	 */
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the tipoEvento
	 */
	public Integer getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
}
