
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Movimiento_Historico_Producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class MovimientoHistoricoProducto implements java.io.Serializable {
    private static final long serialVersionUID = 2006133574;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * almacen id
    */
    private int almacenId;
    
    /**
    * fecha
    */
    private java.sql.Timestamp fecha;
    
    /**
    * tipo movimiento
    */
    private int tipoMovimiento;
    
    /**
    * cantidad
    */
    private int cantidad;
    
    /**
    * costo
    */
    private Double costo;
    
    /**
    * precio
    */
    private Double precio;
    
    /**
    * usuario email
    */
    private String usuarioEmail;
    
    /**
    * producto codigo barras
    */
    private String productoCodigoBarras;
    
    /**
    * entrada salida id
    */
    private Integer entradaSalidaId;

    /** 
     * Default Constructor
     */
    public MovimientoHistoricoProducto() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public MovimientoHistoricoProducto( Integer id ) {
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

    public int getAlmacenId() {
        return this.almacenId;
    }

    public void setAlmacenId(int v) {
        this.almacenId = v;
    }

    public java.sql.Timestamp getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Timestamp v) {
        this.fecha = v;
    }

    public int getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    public void setTipoMovimiento(int v) {
        this.tipoMovimiento = v;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public Double getCosto() {
        return this.costo;
    }

    public void setCosto(Double v) {
        this.costo = v;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double v) {
        this.precio = v;
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

    public Integer getEntradaSalidaId() {
        return this.entradaSalidaId;
    }

    public void setEntradaSalidaId(Integer v) {
        this.entradaSalidaId = v;
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
        if (!(o instanceof MovimientoHistoricoProducto)) {
            return false;
        }

    	MovimientoHistoricoProducto other = (MovimientoHistoricoProducto ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.MovimientoHistoricoProducto[id = "+id+ "]";
    }
}
