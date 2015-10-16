
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table CFD.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Cfd implements java.io.Serializable {
    private static final long serialVersionUID = 321962844;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * ultima actualizacion
    */
    private java.sql.Timestamp ultimaActualizacion;
    
    /**
    * contenido original xml
    */
    private byte[] contenidoOriginalXml;
    
    /**
    * calling error result
    */
    private String callingErrorResult;
    
    /**
    * num cfd
    */
    private String numCfd;
    
    /**
    * tipo
    */
    private String tipo;

    /** 
     * Default Constructor
     */
    public Cfd() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Cfd( Integer id ) {
        this.id 	= 	id;

    }
    
    /**
     * Getters and Setters
     */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer v) {
        this.id = v;
    }

    public java.sql.Timestamp getUltimaActualizacion() {
        return this.ultimaActualizacion;
    }

    public void setUltimaActualizacion(java.sql.Timestamp v) {
        this.ultimaActualizacion = v;
    }

    public byte[] getContenidoOriginalXml() {
        return this.contenidoOriginalXml;
    }

    public void setContenidoOriginalXml(byte[] v) {
        this.contenidoOriginalXml = v;
    }

    public String getCallingErrorResult() {
        return this.callingErrorResult;
    }

    public void setCallingErrorResult(String v) {
        this.callingErrorResult = v;
    }

    public String getNumCfd() {
        return this.numCfd;
    }

    public void setNumCfd(String v) {
        this.numCfd = v;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String v) {
        this.tipo = v;
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
        if (!(o instanceof Cfd)) {
            return false;
        }

    	Cfd other = (Cfd ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.Cfd[id = "+id+ "]";
    }
}
