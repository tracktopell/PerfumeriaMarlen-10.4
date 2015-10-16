
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Evento_Sincronizacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class EventoSincronizacion implements java.io.Serializable {
    private static final long serialVersionUID = 1159908271;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * fecha evento
    */
    private java.sql.Timestamp fechaEvento;
    
    /**
    * tipo evento
    */
    private int tipoEvento;
    
    /**
    * afectacion global
    */
    private int afectacionGlobal;
    
    /**
    * sucursal id afectada
    */
    private int sucursalIdAfectada;
    
    /**
    * tabla
    */
    private String tabla;
    
    /**
    * campos llave
    */
    private String camposLlave;
    
    /**
    * valores llave
    */
    private String valoresLlave;

    /** 
     * Default Constructor
     */
    public EventoSincronizacion() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public EventoSincronizacion( Integer id ) {
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

    public java.sql.Timestamp getFechaEvento() {
        return this.fechaEvento;
    }

    public void setFechaEvento(java.sql.Timestamp v) {
        this.fechaEvento = v;
    }

    public int getTipoEvento() {
        return this.tipoEvento;
    }

    public void setTipoEvento(int v) {
        this.tipoEvento = v;
    }

    public int getAfectacionGlobal() {
        return this.afectacionGlobal;
    }

    public void setAfectacionGlobal(int v) {
        this.afectacionGlobal = v;
    }

    public int getSucursalIdAfectada() {
        return this.sucursalIdAfectada;
    }

    public void setSucursalIdAfectada(int v) {
        this.sucursalIdAfectada = v;
    }

    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String v) {
        this.tabla = v;
    }

    public String getCamposLlave() {
        return this.camposLlave;
    }

    public void setCamposLlave(String v) {
        this.camposLlave = v;
    }

    public String getValoresLlave() {
        return this.valoresLlave;
    }

    public void setValoresLlave(String v) {
        this.valoresLlave = v;
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
        if (!(o instanceof EventoSincronizacion)) {
            return false;
        }

    	EventoSincronizacion other = (EventoSincronizacion ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.EventoSincronizacion[id = "+id+ "]";
    }
}
