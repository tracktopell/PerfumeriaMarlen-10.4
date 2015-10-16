package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.model.Constants;
import java.sql.Timestamp;
import java.util.Date;

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
    private Integer caja;
    
    /**
    * usuario email
    */
    private String usuarioEmail;
    
   
    /**
    * saldo Inicial
    */
    private Double saldoInicial;

	/**
    * saldo Final
    */
    private Double saldoFinal;
    
    /**
    * comentarios
    */
    private String comentarios;
	
    /**
    * tipo evento
    */
    private int tipoEvento;

	/**
	 * Usuario Autorizo
	 */
    private String usuarioAutorizo;	

	public CorteCajaDTO() {
	}

	/**
	 * fecha
	 * @return the fecha
	 */
	public long getFecha() {
		return fecha;
	}

	/**
	 * fecha
	 * @param fecha the fecha to set
	 */
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	/**
	 * sucursal id
	 * @return the sucursalId
	 */
	public int getSucursalId() {
		return sucursalId;
	}

	/**
	 * sucursal id
	 * @param sucursalId the sucursalId to set
	 */
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	/**
	 * caja
	 * @return the caja
	 */
	public Integer getCaja() {
		return caja;
	}

	/**
	 * caja
	 * @param caja the caja to set
	 */
	public void setCaja(Integer caja) {
		this.caja = caja;
	}

	/**
	 * usuario email
	 * @return the usuarioEmail
	 */
	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	/**
	 * usuario email
	 * @param usuarioEmail the usuarioEmail to set
	 */
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	/**
	 * saldo Inicial
	 * @return the saldoInicial
	 */
	public Double getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * saldo Inicial
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * saldo Final
	 * @return the saldoFinal
	 */
	public Double getSaldoFinal() {
		return saldoFinal;
	}

	/**
	 * saldo Final
	 * @param saldoFinal the saldoFinal to set
	 */
	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	/**
	 * comentarios
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * comentarios
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * tipo evento
	 * @return the tipoEvento
	 */
	public int getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * tipo evento
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(int tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 * Usuario Autorizo
	 * @return the usuarioAutorizo
	 */
	public String getUsuarioAutorizo() {
		return usuarioAutorizo;
	}

	/**
	 * Usuario Autorizo
	 * @param usuarioAutorizo the usuarioAutorizo to set
	 */
	public void setUsuarioAutorizo(String usuarioAutorizo) {
		this.usuarioAutorizo = usuarioAutorizo;
	}
	
	public CorteCaja reverse(){
		CorteCaja cj= new CorteCaja();
		
		cj.setFecha(new Timestamp(fecha));
		cj.setSucursalId(sucursalId);
		cj.setCaja(caja);
		cj.setUsuarioEmail(usuarioEmail);
		cj.setSaldoFinal(saldoFinal);
		cj.setSaldoInicial(saldoInicial);
		cj.setComentarios(comentarios);
		cj.setTipoEvento(tipoEvento);
		cj.setUsuarioAutorizo(usuarioAutorizo);
		
		return cj;
	}
	
	
	public String toString(){
		
		StringBuilder sb= new StringBuilder("CorteCajaDTO{");
		sb.append("fecha=").append(this.fecha).append("(").append(Constants.sdfLogDate.format(new Date(this.fecha))).append(")");
		sb.append(", sucursalId=").append(this.sucursalId);
		sb.append(", caja=").append(this.caja);
		sb.append(", usuarioEmail=").append(this.usuarioEmail);
		sb.append(", saldoInicial=").append(this.saldoInicial);
		sb.append(", saldoFinal=").append(this.saldoFinal);
		sb.append(", comentarios=").append(this.comentarios);
		sb.append(", tipoEvento=").append(this.tipoEvento);			
		sb.append(", usuarioAutorizo=").append(this.usuarioAutorizo);			
		sb.append("}");
		return sb.toString();
	}
	
}
