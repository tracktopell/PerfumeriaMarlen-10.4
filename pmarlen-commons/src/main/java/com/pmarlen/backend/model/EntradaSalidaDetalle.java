
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Entrada_Salida_Detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class EntradaSalidaDetalle implements java.io.Serializable {
    private static final long serialVersionUID = 8505448;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * entrada salida id
    */
    private int entradaSalidaId;
    
    /**
    * producto codigo barras
    */
    private String productoCodigoBarras;
    
    /**
    * almacen id
    */
    private int almacenId;
    
    /**
    * cantidad
    */
    private int cantidad;
    
    /**
    * precio venta
    */
    private double precioVenta;

    /** 
     * Default Constructor
     */
    public EntradaSalidaDetalle() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public EntradaSalidaDetalle( Integer id ) {
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

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public double getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(double v) {
        this.precioVenta = v;
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
        if (!(o instanceof EntradaSalidaDetalle)) {
            return false;
        }

    	EntradaSalidaDetalle other = (EntradaSalidaDetalle ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.EntradaSalidaDetalle[id = "+id+ "]";
    }
}
