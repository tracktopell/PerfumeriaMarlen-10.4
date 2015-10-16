
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Movimiento_operativo_almacen_Detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class MovimientoOperativoAlmacenDetalle implements java.io.Serializable {
    private static final long serialVersionUID = 958544880;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * movimiento operativo almacen id
    */
    private int movimientoOperativoAlmacenId;
    
    /**
    * producto codigo barras
    */
    private String productoCodigoBarras;
    
    /**
    * cantidad
    */
    private int cantidad;
    
    /**
    * cantidad confirmada
    */
    private Integer cantidadConfirmada;

    /** 
     * Default Constructor
     */
    public MovimientoOperativoAlmacenDetalle() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public MovimientoOperativoAlmacenDetalle( Integer id ) {
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

    public int getMovimientoOperativoAlmacenId() {
        return this.movimientoOperativoAlmacenId;
    }

    public void setMovimientoOperativoAlmacenId(int v) {
        this.movimientoOperativoAlmacenId = v;
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

    public Integer getCantidadConfirmada() {
        return this.cantidadConfirmada;
    }

    public void setCantidadConfirmada(Integer v) {
        this.cantidadConfirmada = v;
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
        if (!(o instanceof MovimientoOperativoAlmacenDetalle)) {
            return false;
        }

    	MovimientoOperativoAlmacenDetalle other = (MovimientoOperativoAlmacenDetalle ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.MovimientoOperativoAlmacenDetalle[id = "+id+ "]";
    }
}
