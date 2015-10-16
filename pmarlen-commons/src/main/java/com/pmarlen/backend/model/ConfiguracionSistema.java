
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Configuracion_Sistema.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class ConfiguracionSistema implements java.io.Serializable {
    private static final long serialVersionUID = 1122088620;
    
    /**
    * llave
    */
    private String llave;
    
    /**
    * valor
    */
    private String valor;

    /** 
     * Default Constructor
     */
    public ConfiguracionSistema() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public ConfiguracionSistema( String llave ) {
        this.llave 	= 	llave;

    }
    
    /**
     * Getters and Setters
     */
    public String getLlave() {
        return this.llave;
    }

    public void setLlave(String v) {
        this.llave = v;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String v) {
        this.valor = v;
    }


    @Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (llave != null ? llave.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof ConfiguracionSistema)) {
            return false;
        }

    	ConfiguracionSistema other = (ConfiguracionSistema ) o;
        if ( (this.llave == null && other.llave != null) || (this.llave != null && !this.llave.equals(other.llave))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.ConfiguracionSistema[llave = "+llave+ "]";
    }
}
