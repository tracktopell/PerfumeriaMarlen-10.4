package com.pmarlen.backend.model.quickviews;

import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.model.Constants;
import java.text.DecimalFormat;

/**
 *
 * @author alfredo
 */
public class EntradaSalidaDetalleQuickView extends EntradaSalidaDetalle implements Comparable<EntradaSalidaDetalleQuickView>{
	private long rowId;
	
	private String productoNombre;
	
	private String productoProveedor;
	
	private String productoPresentacion;
	
	private String productoContenido;
	
	private String productoUnidadMedida;
	
	private String productoUnidadEmpaque;
    
    private String productoIndustria;
    
    private String productoLinea;
    
    private String productoMarca;
	
	private String productoUnidadesPorCaja;
	
	private String apUbicacion;
	
	private int     apTipoAlmacen;
		    
    private int     apCantidad;
	
    private double  apPrecio;
	
	private int canPendteEnOtrosPV;

    private String unidad;    

    private String noIdentificacion;
    
    private Double cfd_valorUnitario;
    private Double cfd_importe;
    private Double cfd_base;
    private Double cfd_importeIVA;
    private Double cfdi_valorOriginal;
    private Double cfdi_descuento;
    private Double cfdi_iva;
    private Double cfdi_importeFinal;
    
	public EntradaSalidaDetalleQuickView() {
		this.rowId = System.currentTimeMillis();
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	public long getRowId() {
		return rowId;
	}
		
	public String toStringShorten() {
		StringBuilder sb= new StringBuilder(productoNombre);
		
		sb.append("/").
				append(productoPresentacion).
				append(" ").
				append(productoContenido).
				append(productoUnidadMedida).
				append("[").
				append(productoUnidadesPorCaja).
				append("]#").
				append(apCantidad);
		
		return  sb.toString();
	}

	/**
	 * @return the productoNombre
	 */
	public String getProductoNombre() {
		return productoNombre;
	}

	/**
	 * @param productoNombre the productoNombre to set
	 */
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public String getProductoProveedor() {
		return productoProveedor;
	}

	public void setProductoProveedor(String productoProveedor) {
		this.productoProveedor = productoProveedor;
	}
	
	/**
	 * @return the productoPresentacion
	 */
	public String getProductoPresentacion() {
		return productoPresentacion;
	}

	/**
	 * @param productoPresentacion the productoPresentacion to set
	 */
	public void setProductoPresentacion(String productoPresentacion) {
		this.productoPresentacion = productoPresentacion;
	}

	/**
	 * @return the productoContenido
	 */
	public String getProductoContenido() {
		return productoContenido;
	}

	/**
	 * @param productoContenido the productoContenido to set
	 */
	public void setProductoContenido(String productoContenido) {
		this.productoContenido = productoContenido;
	}

	/**
	 * @return the productoIndustria
	 */
	public String getProductoIndustria() {
		return productoIndustria;
	}

	/**
	 * @param productoIndustria the productoIndustria to set
	 */
	public void setProductoIndustria(String productoIndustria) {
		this.productoIndustria = productoIndustria;
	}

	/**
	 * @return the productoLinea
	 */
	public String getProductoLinea() {
		return productoLinea;
	}

	/**
	 * @param productoLinea the productoLinea to set
	 */
	public void setProductoLinea(String productoLinea) {
		this.productoLinea = productoLinea;
	}

	/**
	 * @return the productoMarca
	 */
	public String getProductoMarca() {
		return productoMarca;
	}

	/**
	 * @param productoMarca the productoMarca to set
	 */
	public void setProductoMarca(String productoMarca) {
		this.productoMarca = productoMarca;
	}

	public void setProductoUnidadesPorCaja(String productoUnidadesPorCaja) {
		this.productoUnidadesPorCaja = productoUnidadesPorCaja;
	}

	public String getProductoUnidadesPorCaja() {
		return productoUnidadesPorCaja;
	}

	public String getApUbicacion() {
		return apUbicacion;
	}

	public void setApUbicacion(String apUbicacion) {
		this.apUbicacion = apUbicacion;
	}
	
	/**
	 * @return the apTipoAlmacen
	 */
	public int getApTipoAlmacen() {
		return apTipoAlmacen;
	}

	/**
	 * @param apTipoAlmacen the apTipoAlmacen to set
	 */
	public void setApTipoAlmacen(int apTipoAlmacen) {
		this.apTipoAlmacen = apTipoAlmacen;
	}

	/**
	 * @return the apCantidad
	 */
	public int getApCantidad() {
		return apCantidad;
	}

	/**
	 * @param apCantidad the apCantidad to set
	 */
	public void setApCantidad(int apCantidad) {
		this.apCantidad = apCantidad;
	}

	/**
	 * @return the apPrecio
	 */
	public double getApPrecio() {
		return apPrecio;
	}

	/**
	 * @param apPrecio the apPrecio to set
	 */
	public void setApPrecio(double apPrecio) {
		this.apPrecio = apPrecio;
	}

	/**
	 * @return the productoUnidadMedida
	 */
	public String getProductoUnidadMedida() {
		return productoUnidadMedida;
	}

	/**
	 * @param productoUnidadDeMedida the productoUnidadMedida to set
	 */
	public void setProductoUnidadMedida(String productoUnidadDeMedida) {
		this.productoUnidadMedida = productoUnidadDeMedida;
	}

	public String getProductoUnidadEmpaque() {
		return productoUnidadEmpaque;
	}

	public void setProductoUnidadEmpaque(String productoUnidadEmpaque) {
		this.productoUnidadEmpaque = productoUnidadEmpaque;
	}
	

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the noIdentificacion
     */
    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * @param noIdentificacion the noIdentificacion to set
     */
    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }
		

	public void setCanPendteEnOtrosPV(int canPendteEnOtrosPV) {
		this.canPendteEnOtrosPV = canPendteEnOtrosPV;
	}

	public int getCanPendteEnOtrosPV() {
		return canPendteEnOtrosPV;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	@Override
	public int compareTo(EntradaSalidaDetalleQuickView o) {
		if(		apUbicacion   != null && apUbicacion.trim().length()   > 1 &&
				o.apUbicacion != null && o.apUbicacion.trim().length() > 1 ){
			return apUbicacion.compareTo(o.apUbicacion);
		} else {
			return getId().compareTo(o.getId());
		}		
	}

    /**
     * @return the cfd_valorUnitario
     */
    public Double getCfd_valorUnitario() {
        return cfd_valorUnitario;
    }

    /**
     * @param cfd_valorUnitario the cfd_valorUnitario to set
     */
    public void setCfd_valorUnitario(Double cfd_valorUnitario) {
        this.cfd_valorUnitario = cfd_valorUnitario;
    }

    /**
     * @return the cfd_importe
     */
    public Double getCfd_importe() {
        return cfd_importe;
    }

    /**
     * @param cfd_importe the cfd_importe to set
     */
    public void setCfd_importe(Double cfd_importe) {
        this.cfd_importe = cfd_importe;
    }

    /**
     * @return the cfd_base
     */
    public Double getCfd_base() {
        return cfd_base;
    }

    /**
     * @param cfd_base the cfd_base to set
     */
    public void setCfd_base(Double cfd_base) {
        this.cfd_base = cfd_base;
    }

    /**
     * @return the cfd_importeIVA
     */
    public Double getCfd_importeIVA() {
        return cfd_importeIVA;
    }

    /**
     * @param cfd_importeIVA the cfd_importeIVA to set
     */
    public void setCfd_importeIVA(Double cfd_importeIVA) {
        this.cfd_importeIVA = cfd_importeIVA;
    }

    /**
     * @return the cfdi_valorOriginal
     */
    public Double getCfdi_valorOriginal() {
        return cfdi_valorOriginal;
    }

    /**
     * @param cfdi_valorOriginal the cfdi_valorOriginal to set
     */
    public void setCfdi_valorOriginal(Double cfdi_valorOriginal) {
        this.cfdi_valorOriginal = cfdi_valorOriginal;
    }

    /**
     * @return the cfdi_descuento
     */
    public Double getCfdi_descuento() {
        return cfdi_descuento;
    }

    /**
     * @param cfdi_descuento the cfdi_descuento to set
     */
    public void setCfdi_descuento(Double cfdi_descuento) {
        this.cfdi_descuento = cfdi_descuento;
    }

    /**
     * @return the cfdi_iva
     */
    public Double getCfdi_iva() {
        return cfdi_iva;
    }

    /**
     * @param cfdi_iva the cfdi_iva to set
     */
    public void setCfdi_iva(Double cfdi_iva) {
        this.cfdi_iva = cfdi_iva;
    }

    /**
     * @return the cfdi_importeFinal
     */
    public Double getCfdi_importeFinal() {
        return cfdi_importeFinal;
    }

    /**
     * @param cfdi_importeFinal the cfdi_importeFinal to set
     */
    public void setCfdi_importeFinal(Double cfdi_importeFinal) {
        this.cfdi_importeFinal = cfdi_importeFinal;
    }
}
