
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Estado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Estado implements java.io.Serializable {
    private static final long serialVersionUID = 1868548013;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * descripcion
    */
    private String descripcion;

    /** 
     * Default Constructor
     */
    public Estado() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Estado( Integer id ) {
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String v) {
        this.descripcion = v;
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
        if (!(o instanceof Estado)) {
            return false;
        }

    	Estado other = (Estado ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.Estado[id = "+id+ "]";
    }
}
