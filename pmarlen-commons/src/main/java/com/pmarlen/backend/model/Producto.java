
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class Producto implements java.io.Serializable {
    private static final long serialVersionUID = 973246391;
    
    /**
    * codigo barras
    */
    private String codigoBarras;

	/**
    * proveedor
    */
    private String proveedor;
    
    /**
    * industria
    */
    private String industria;
    
    /**
    * linea
    */
    private String linea;
    
    /**
    * marca
    */
    private String marca;
    
    /**
    * nombre
    */
    private String nombre;
    
    /**
    * presentacion
    */
    private String presentacion;
    
    /**
    * abrebiatura
    */
    private String abrebiatura;
    
    /**
    * unidades x caja
    */
    private int unidadesXCaja;
    
    /**
    * contenido
    */
    private String contenido;
    
    /**
    * unidad medida
    */
    private String unidadMedida;
    
    /**
    * unidad empaque
    */
    private String unidadEmpaque;
    
    /**
    * costo
    */
    private double costo;
    
    /**
    * costo venta
    */
    private double costoVenta;
	
	/**
    * descontinuado
    */
    private Integer descontinuado;

	/**
    * descontinuado
    */
    private Integer poco;
    
    /**
    * unidad 
    */
    private String unidad;    

    /**
    * noIdentificacion 
    */
    private String noIdentificacion;

    /** 
     * Default Constructor
     */
    public Producto() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public Producto( String codigoBarras ) {
        this.codigoBarras 	= 	codigoBarras;

    }
    
    /**
     * Getters and Setters
     */
    public String getCodigoBarras() {
        return this.codigoBarras;
    }

    public void setCodigoBarras(String v) {
        this.codigoBarras = v;
    }

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
    public String getIndustria() {
        return this.industria;
    }

    public void setIndustria(String v) {
        this.industria = v;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String v) {
        this.linea = v;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String v) {
        this.marca = v;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public String getPresentacion() {
        return this.presentacion;
    }

    public void setPresentacion(String v) {
        this.presentacion = v;
    }

    public String getAbrebiatura() {
        return this.abrebiatura;
    }

    public void setAbrebiatura(String v) {
        this.abrebiatura = v;
    }

    public int getUnidadesXCaja() {
        return this.unidadesXCaja;
    }

    public void setUnidadesXCaja(int v) {
        this.unidadesXCaja = v;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String v) {
        this.contenido = v;
    }

    public String getUnidadMedida() {
        return this.unidadMedida;
    }

    public void setUnidadMedida(String v) {
        this.unidadMedida = v;
    }

    public String getUnidadEmpaque() {
        return this.unidadEmpaque;
    }

    public void setUnidadEmpaque(String v) {
        this.unidadEmpaque = v;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double v) {
        this.costo = v;
    }

    public double getCostoVenta() {
        return this.costoVenta;
    }

    public void setCostoVenta(double v) {
        this.costoVenta = v;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
		// bug ?
        hash = ( (codigoBarras != null ? codigoBarras.hashCode() : 0 ) );
        return hash;
    }

    public boolean equals(Object o){

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof Producto)) {
            return false;
        }

    	Producto other = (Producto ) o;
        if ( (this.codigoBarras == null && other.codigoBarras != null) || (this.codigoBarras != null && !this.codigoBarras.equals(other.codigoBarras))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return nombre + ", " + presentacion;
    }

	public Integer getDescontinuado() {
		return descontinuado;
	}

	public void setDescontinuado(Integer descontinuado) {
		this.descontinuado = descontinuado;
	}

	
	public void setPoco(Integer poco) {
		this.poco = poco;
	}

	public Integer getPoco() {
		return poco;
	}
	
}
