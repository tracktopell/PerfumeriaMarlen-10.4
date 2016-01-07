
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Corte_Caja.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class CorteCaja implements java.io.Serializable {
    private static final long serialVersionUID = 2090437641;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * fecha
    */
    private java.sql.Timestamp fecha;
    
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
	
    /** 
     * Default Constructor
     */
    public CorteCaja() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public CorteCaja( Integer id ) {
        this.id 	= 	id;

    }

	/**
	 * id
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * id
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * fecha
	 * @return the fecha
	 */
	public java.sql.Timestamp getFecha() {
		return fecha;
	}

	/**
	 * fecha
	 * @param fecha the fecha to set
	 */
	public void setFecha(java.sql.Timestamp fecha) {
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

	@Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (id != null ? id.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof CorteCaja)) {
            return false;
        }

    	CorteCaja other = (CorteCaja ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.CorteCaja[id = "+id+ ", fecha = "+fecha+", sucursalId="+sucursalId+", caja="+caja+" , usuarioEmail="+usuarioEmail+", saldoInicial="+saldoInicial+", saldoFinal="+saldoFinal+" , tipoEvento="+tipoEvento+"]";
    }
	
}
