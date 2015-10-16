
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Usuario_Perfil.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class UsuarioPerfil implements java.io.Serializable {
    private static final long serialVersionUID = 1773046069;
    
    /**
    * email
    */
    private String email;
    
    /**
    * perfil
    */
    private String perfil;

    /** 
     * Default Constructor
     */
    public UsuarioPerfil() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public UsuarioPerfil( String email, String perfil ) {
        this.email 	= 	email;
        this.perfil 	= 	perfil;

    }
    
    /**
     * Getters and Setters
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String v) {
        this.email = v;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String v) {
        this.perfil = v;
    }


    @Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (perfil != null ? perfil.hashCode() : 0 ) + 
			 (email != null ? email.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof UsuarioPerfil)) {
            return false;
        }

    	UsuarioPerfil other = (UsuarioPerfil ) o;
        if ( (this.perfil == null && other.perfil != null) || (this.perfil != null && !this.perfil.equals(other.perfil)) || 
             (this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.UsuarioPerfil[perfil = "+perfil + ", email = "+email+ "]";
    }
}
