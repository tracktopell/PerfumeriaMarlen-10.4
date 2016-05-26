
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Almacen_Producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class AlmacenProducto implements java.io.Serializable {
    private static final long serialVersionUID = 1667420337;
    private Integer almacenId;
    private String productoCodigoBarras;
    private int cantidad;
	private Integer poco;
    private double precio;
    private String ubicacion;

    /** 
     * Default Constructor
     */
    public AlmacenProducto() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public AlmacenProducto( Integer almacenId, String productoCodigoBarras ) {
        this.almacenId 	= 	almacenId;
        this.productoCodigoBarras 	= 	productoCodigoBarras;

    }
    
    /**
     * Getters and Setters
     */
    public Integer getAlmacenId() {
        return this.almacenId;
    }

    public void setAlmacenId(Integer v) {
        this.almacenId = v;
    }

    public String getProductoCodigoBarras() {
        return this.productoCodigoBarras;
    }

    public void setProductoCodigoBarras(String v) {
        this.productoCodigoBarras = v;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double v) {
        this.precio = v;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String v) {
        this.ubicacion = v;
    }

	public void setPoco(Integer poco) {
		this.poco = poco;
	}

	public Integer getPoco() {
		return poco;
	}
	

    @Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (almacenId != null ? almacenId.hashCode() : 0 ) + 
			 (productoCodigoBarras != null ? productoCodigoBarras.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof AlmacenProducto)) {
            return false;
        }

    	AlmacenProducto other = (AlmacenProducto ) o;
        if ( (this.almacenId == null && other.almacenId != null) || (this.almacenId != null && !this.almacenId.equals(other.almacenId)) || 
             (this.productoCodigoBarras == null && other.productoCodigoBarras != null) || (this.productoCodigoBarras != null && !this.productoCodigoBarras.equals(other.productoCodigoBarras))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.AlmacenProducto[almacenId = "+almacenId + ", productoCodigoBarras = "+productoCodigoBarras+ "]";
    }
}
