
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Usuario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Usuario implements java.io.Serializable {
    private static final long serialVersionUID = 1818292199;
    
    /**
    * email
    */
    private String email;
    
    /**
    * abilitado
    */
    private int abilitado;
    
    /**
    * nombre completo
    */
    private String nombreCompleto;
    
    /**
    * password
    */
    private String password;
	
	/**
	 * clave
	 */
	private Integer clave;

	/**
	 * email alternativo
	 */
	private String emailAlternativo;
	
	private Integer sucursalId;
	
    /** 
     * Default Constructor
     */
    public Usuario() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Usuario( String email ) {
        this.email 	= 	email;

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

    public int getAbilitado() {
        return this.abilitado;
    }

    public void setAbilitado(int v) {
        this.abilitado = v;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String v) {
        this.nombreCompleto = v;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String v) {
        this.password = v;
    }

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public String getEmailAlternativo() {
		return emailAlternativo;
	}

	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}

	public Integer getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
	
    @Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (email != null ? email.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof Usuario)) {
            return false;
        }

    	Usuario other = (Usuario ) o;
        if ( (this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
