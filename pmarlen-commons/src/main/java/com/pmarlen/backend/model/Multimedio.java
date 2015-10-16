
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Multimedio.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Multimedio implements java.io.Serializable {
    private static final long serialVersionUID = 1177988267;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * mime type
    */
    private String mimeType;
    
    /**
    * ruta contenido
    */
    private String rutaContenido;
    
    /**
    * size bytes
    */
    private int sizeBytes;
    
    /**
    * nombre archivo
    */
    private String nombreArchivo;

    /** 
     * Default Constructor
     */
    public Multimedio() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Multimedio( Integer id ) {
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

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String v) {
        this.mimeType = v;
    }

    public String getRutaContenido() {
        return this.rutaContenido;
    }

    public void setRutaContenido(String v) {
        this.rutaContenido = v;
    }

    public int getSizeBytes() {
        return this.sizeBytes;
    }

    public void setSizeBytes(int v) {
        this.sizeBytes = v;
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String v) {
        this.nombreArchivo = v;
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
        if (!(o instanceof Multimedio)) {
            return false;
        }

    	Multimedio other = (Multimedio ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return nombreArchivo;
    }
}
