package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author alfredo
 */
public class CorteCajaDTO implements Serializable{
	    
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

    public CorteCajaDTO( CorteCajaDTO x) {
        this.fecha = x.fecha;
        this.sucursalId = x.sucursalId;
        this.caja = x.caja;
        this.usuarioEmail = x.usuarioEmail;
        this.saldoInicial = x.saldoInicial;
        this.saldoFinal = x.saldoFinal;
        this.comentarios = x.comentarios;
        this.tipoEvento = x.tipoEvento;
        this.usuarioAutorizo = x.usuarioAutorizo;
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
		
		String tipoEventoValue="";
        if(this.tipoEvento==Constants.TIPO_EVENTO_AP_INICIADA){
			tipoEventoValue="TIPO_EVENTO_AP_INICIADA";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_AUTENTICADO){
			tipoEventoValue="TIPO_EVENTO_AUTENTICADO";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_APERTURA){
			tipoEventoValue="TIPO_EVENTO_APERTURA";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_SUSPENDER){
			tipoEventoValue="TIPO_EVENTO_SUSPENDER";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_REANUDAR){
			tipoEventoValue="TIPO_EVENTO_REANUDAR";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_ERRORGRAVE){
			tipoEventoValue="TIPO_EVENTO_ERRORGRAVE";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_CIERRE){
			tipoEventoValue="TIPO_EVENTO_CIERRE";
		} else{
			tipoEventoValue="["+this.tipoEvento+"]";
		}
        sb.append(", tipoEvento=").append(tipoEventoValue);
		
		sb.append(", tipoEvento=").append(this.tipoEvento);			
		sb.append(", usuarioAutorizo=").append(this.usuarioAutorizo);			
		sb.append("}");
		return sb.toString();
	}
    
    public String[] toStringArray(){
        String values[]=new String[6];
        String tipoEventoValue="";
        if(this.tipoEvento==Constants.TIPO_EVENTO_AP_INICIADA){
			tipoEventoValue="AP_INICIADA";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_AUTENTICADO){
			tipoEventoValue="AUTENTICADO";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_APERTURA){
			tipoEventoValue="APERTURA SESIÓN";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_SUSPENDER){
			tipoEventoValue="SUSPENDER";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_REANUDAR){
			tipoEventoValue="REANUDAR";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_ERRORGRAVE){
			tipoEventoValue="ERRORGRAVE";
		} else if(this.tipoEvento==Constants.TIPO_EVENTO_CIERRE){
			tipoEventoValue="CIERRE SESIÓN";
		} else{
			tipoEventoValue="["+this.tipoEvento+"]";
		}
	
        int valIdx=0;
        
        values[valIdx++]=Constants.sdfLogDate.format(new Date(this.fecha));
        values[valIdx++]=tipoEventoValue;        
        values[valIdx++]=this.usuarioEmail;
        if(this.saldoInicial!=null){
            values[valIdx++]=Constants.dfCurrency.format(this.saldoInicial);
        }else{
            values[valIdx++]="N/A";
        }
        if(this.saldoFinal!=null){
            values[valIdx++]=Constants.dfCurrency.format(this.saldoFinal);
        }else{
            values[valIdx++]="N/A";
        }
        if(this.comentarios == null){
            values[valIdx++]="S/C";
        } else {
            values[valIdx++]=this.comentarios;
        }        
        
        return values;
    }
	
}
