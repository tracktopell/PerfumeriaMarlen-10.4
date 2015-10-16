
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Oferta_Producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class OfertaProducto implements java.io.Serializable {
    private static final long serialVersionUID = 2142734953;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * tipo
    */
    private int tipo;
    
    /**
    * usuario email
    */
    private String usuarioEmail;
    
    /**
    * producto codigo barras
    */
    private String productoCodigoBarras;
    
    /**
    * almacen id
    */
    private int almacenId;
    
    /**
    * activa
    */
    private int activa;
    
    /**
    * marca
    */
    private String marca;
    
    /**
    * linea
    */
    private String linea;
    
    /**
    * lema
    */
    private String lema;

    /** 
     * Default Constructor
     */
    public OfertaProducto() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public OfertaProducto( Integer id ) {
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

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int v) {
        this.tipo = v;
    }

    public String getUsuarioEmail() {
        return this.usuarioEmail;
    }

    public void setUsuarioEmail(String v) {
        this.usuarioEmail = v;
    }

    public String getProductoCodigoBarras() {
        return this.productoCodigoBarras;
    }

    public void setProductoCodigoBarras(String v) {
        this.productoCodigoBarras = v;
    }

    public int getAlmacenId() {
        return this.almacenId;
    }

    public void setAlmacenId(int v) {
        this.almacenId = v;
    }

    public int getActiva() {
        return this.activa;
    }

    public void setActiva(int v) {
        this.activa = v;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String v) {
        this.marca = v;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String v) {
        this.linea = v;
    }

    public String getLema() {
        return this.lema;
    }

    public void setLema(String v) {
        this.lema = v;
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
        if (!(o instanceof OfertaProducto)) {
            return false;
        }

    	OfertaProducto other = (OfertaProducto ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.OfertaProducto[id = "+id+ "]";
    }
}
