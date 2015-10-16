
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Sucursal.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Sucursal implements java.io.Serializable {
    private static final long serialVersionUID = 1273030789;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * id padre
    */
    private Integer idPadre;
    
    /**
    * nombre
    */
    private String nombre;
    
    /**
    * direccion
    */
    private String direccion;
    
    /**
    * telefonos
    */
    private String telefonos;
    
    /**
    * usuario sicofi
    */
    private String usuarioSicofi;
    
    /**
    * password sicofi
    */
    private String passwordSicofi;
    
    /**
    * serie sicofi
    */
    private String serieSicofi;
    
    /**
    * comentarios
    */
    private String comentarios;
    
    /**
    * descuento mayoreo habilitado
    */
    private Integer descuentoMayoreoHabilitado;
	
	/**
	 * clave
	 */
	private String clave;

    /** 
     * Default Constructor
     */
    public Sucursal() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Sucursal( Integer id ) {
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

    public Integer getIdPadre() {
        return this.idPadre;
    }

    public void setIdPadre(Integer v) {
        this.idPadre = v;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String v) {
        this.direccion = v;
    }

    public String getTelefonos() {
        return this.telefonos;
    }

    public void setTelefonos(String v) {
        this.telefonos = v;
    }

    public String getUsuarioSicofi() {
        return this.usuarioSicofi;
    }

    public void setUsuarioSicofi(String v) {
        this.usuarioSicofi = v;
    }

    public String getPasswordSicofi() {
        return this.passwordSicofi;
    }

    public void setPasswordSicofi(String v) {
        this.passwordSicofi = v;
    }

    public String getSerieSicofi() {
        return this.serieSicofi;
    }

    public void setSerieSicofi(String v) {
        this.serieSicofi = v;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String v) {
        this.comentarios = v;
    }

    public Integer getDescuentoMayoreoHabilitado() {
        return this.descuentoMayoreoHabilitado;
    }

    public void setDescuentoMayoreoHabilitado(Integer v) {
        this.descuentoMayoreoHabilitado = v;
    }

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
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
        if (!(o instanceof Sucursal)) {
            return false;
        }

    	Sucursal other = (Sucursal ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
