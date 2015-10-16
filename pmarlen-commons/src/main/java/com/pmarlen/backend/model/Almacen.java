package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Almacen.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Almacen implements java.io.Serializable {
    private static final long serialVersionUID = 208981104;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * tipo almacen
    */
    private int tipoAlmacen;
    
    /**
    * sucursal id
    */
    private int sucursalId;

    /** 
     * Default Constructor
     */
    public Almacen() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Almacen( Integer id ) {
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

    public int getTipoAlmacen() {
        return this.tipoAlmacen;
    }

    public void setTipoAlmacen(int v) {
        this.tipoAlmacen = v;
    }

    public int getSucursalId() {
        return this.sucursalId;
    }

    public void setSucursalId(int v) {
        this.sucursalId = v;
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
        if (!(o instanceof Almacen)) {
            return false;
        }

    	Almacen other = (Almacen ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.Almacen[id = "+id+ "]";
    }
}
