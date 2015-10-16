
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Entrada_Salida_Estado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class EntradaSalidaEstado implements java.io.Serializable {
    private static final long serialVersionUID = 2025018324;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * entrada salida id
    */
    private int entradaSalidaId;
    
    /**
    * estado id
    */
    private int estadoId;
    
    /**
    * fecha
    */
    private java.sql.Timestamp fecha;
    
    /**
    * usuario email
    */
    private String usuarioEmail;
    
    /**
    * comentarios
    */
    private String comentarios;

    /** 
     * Default Constructor
     */
    public EntradaSalidaEstado() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public EntradaSalidaEstado( Integer id ) {
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

    public int getEntradaSalidaId() {
        return this.entradaSalidaId;
    }

    public void setEntradaSalidaId(int v) {
        this.entradaSalidaId = v;
    }

    public int getEstadoId() {
        return this.estadoId;
    }

    public void setEstadoId(int v) {
        this.estadoId = v;
    }

    public java.sql.Timestamp getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Timestamp v) {
        this.fecha = v;
    }

    public String getUsuarioEmail() {
        return this.usuarioEmail;
    }

    public void setUsuarioEmail(String v) {
        this.usuarioEmail = v;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String v) {
        this.comentarios = v;
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
        if (!(o instanceof EntradaSalidaEstado)) {
            return false;
        }

    	EntradaSalidaEstado other = (EntradaSalidaEstado ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.EntradaSalidaEstado[id = "+id+ "]";
    }
}
