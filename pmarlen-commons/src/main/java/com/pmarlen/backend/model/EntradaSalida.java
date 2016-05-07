
package com.pmarlen.backend.model;


/**
 * Class for mapping DTO Entity of Table Entrada_Salida.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class EntradaSalida implements java.io.Serializable {
    private static final long serialVersionUID = 1021019426;
    
    /**
    * id
    */
    private Integer id;
    
    /**
    * tipo mov
    */
    private int tipoMov;
    
    /**
    * sucursal id
    */
    private int sucursalId;
    
    /**
    * estado id
    */
    private int estadoId;
    
    /**
    * fecha creo
    */
    private java.sql.Timestamp fechaCreo;
    
    /**
    * usuario email creo
    */
    private String usuarioEmailCreo;
    
    /**
    * cliente id
    */
    private Integer clienteId;
    
    /**
    * forma de pago id
    */
    private Integer formaDePagoId;
    
    /**
    * metodo de pago id
    */
    private Integer metodoDePagoId;
    
    /**
    * factor iva
    */
    private double factorIva;
    
    /**
    * comentarios
    */
    private String comentarios;
    
    /**
    * cfd id
    */
    private Integer cfdId;
    
    /**
    * numero ticket
    */
    private String numeroTicket;
    
    /**
    * caja
    */
    private Integer caja;
    
    /**
    * importe recibido
    */
    private Double importeRecibido;
    
    /**
    * aprobacion visa mastercard
    */
    private String aprobacionVisaMastercard;
    
    /**
    * porcentaje descuento calculado
    */
    private Integer porcentajeDescuentoCalculado;
    
    /**
    * porcentaje descuento extra
    */
    private Integer porcentajeDescuentoExtra;
    
    /**
    * condiciones de pago
    */
    private String condicionesDePago;
    
    /**
    * num de cuenta
    */
    private String numDeCuenta;
    
    /**
    * autoriza descuento
    */
    private Integer autorizaDescuento;

	/**
	 * entrada salida dev
	 */
	private Integer   esIdDev;
	
	private Double subTotal1ra;
	
	private Double subTotalOpo;
	
	private Double subTotalReg;

	private Double total;
	
	private Double descRedondeo;
	
	private Double redondeoHab;
	
	private Double totalCobrado;
	
	private Double cambio;
	
	private Integer pedioSucursal;
	
	private Integer elemDet;
	
	private Integer totProds;
	
	
    /** 
     * Default Constructor
     */
    public EntradaSalida() {
    }

    /** 
     * lazy Constructor just with IDs
     */
    public EntradaSalida( Integer id ) {
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

    public int getTipoMov() {
        return this.tipoMov;
    }

    public void setTipoMov(int v) {
        this.tipoMov = v;
    }

    public int getSucursalId() {
        return this.sucursalId;
    }

    public void setSucursalId(int v) {
        this.sucursalId = v;
    }

    public int getEstadoId() {
        return this.estadoId;
    }

    public void setEstadoId(int v) {
        this.estadoId = v;
    }

    public java.sql.Timestamp getFechaCreo() {
        return this.fechaCreo;
    }

    public void setFechaCreo(java.sql.Timestamp v) {
        this.fechaCreo = v;
    }

    public String getUsuarioEmailCreo() {
        return this.usuarioEmailCreo;
    }

    public void setUsuarioEmailCreo(String v) {
        this.usuarioEmailCreo = v;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer v) {
        this.clienteId = v;
    }

    public Integer getFormaDePagoId() {
        return this.formaDePagoId;
    }

    public void setFormaDePagoId(Integer v) {
        this.formaDePagoId = v;
    }

    public Integer getMetodoDePagoId() {
        return this.metodoDePagoId;
    }

    public void setMetodoDePagoId(Integer v) {
        this.metodoDePagoId = v;
    }

    public double getFactorIva() {
        return this.factorIva;
    }

    public void setFactorIva(double v) {
        this.factorIva = v;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String v) {
        this.comentarios = v;
    }

    public Integer getCfdId() {
        return this.cfdId;
    }

    public void setCfdId(Integer v) {
        this.cfdId = v;
    }

    public String getNumeroTicket() {
        return this.numeroTicket;
    }

    public void setNumeroTicket(String v) {
        this.numeroTicket = v;
    }

    public Integer getCaja() {
        return this.caja;
    }

    public void setCaja(Integer v) {
        this.caja = v;
    }

    public Double getImporteRecibido() {
        return this.importeRecibido;
    }

    public void setImporteRecibido(Double v) {
        this.importeRecibido = v;
    }

    public String getAprobacionVisaMastercard() {
        return this.aprobacionVisaMastercard;
    }

    public void setAprobacionVisaMastercard(String v) {
        this.aprobacionVisaMastercard = v;
    }

    public Integer getPorcentajeDescuentoCalculado() {
        return this.porcentajeDescuentoCalculado;
    }

    public void setPorcentajeDescuentoCalculado(Integer v) {
        this.porcentajeDescuentoCalculado = v;
    }

    public Integer getPorcentajeDescuentoExtra() {
        return this.porcentajeDescuentoExtra;
    }

    public void setPorcentajeDescuentoExtra(Integer v) {
        this.porcentajeDescuentoExtra = v;
    }

    public String getCondicionesDePago() {
        return this.condicionesDePago;
    }

    public void setCondicionesDePago(String v) {
        this.condicionesDePago = v;
    }

    public String getNumDeCuenta() {
        return this.numDeCuenta;
    }

    public void setNumDeCuenta(String v) {
        this.numDeCuenta = v;
    }

    public Integer getAutorizaDescuento() {
        return this.autorizaDescuento;
    }

    public void setAutorizaDescuento(Integer v) {
        this.autorizaDescuento = v;
    }

	/**
	 * @return the esIdDev
	 */
	public Integer getEsIdDev() {
		return esIdDev;
	}

	/**
	 * @param esIdDev the esIdDev to set
	 */
	public void setEsIdDev(Integer esIdDev) {
		this.esIdDev = esIdDev;
	}
	
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the subTotal1ra
	 */
	public Double getSubTotal1ra() {
		return subTotal1ra;
	}

	/**
	 * @param subTotal1ra the subTotal1ra to set
	 */
	public void setSubTotal1ra(Double subTotal1ra) {
		this.subTotal1ra = subTotal1ra;
	}

	/**
	 * @return the subTotalOpo
	 */
	public Double getSubTotalOpo() {
		return subTotalOpo;
	}

	/**
	 * @param subTotalOpo the subTotalOpo to set
	 */
	public void setSubTotalOpo(Double subTotalOpo) {
		this.subTotalOpo = subTotalOpo;
	}

	/**
	 * @return the subTotalReg
	 */
	public Double getSubTotalReg() {
		return subTotalReg;
	}

	/**
	 * @param subTotalReg the subTotalReg to set
	 */
	public void setSubTotalReg(Double subTotalReg) {
		this.subTotalReg = subTotalReg;
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
        if (!(o instanceof EntradaSalida)) {
            return false;
        }

    	EntradaSalida other = (EntradaSalida ) o;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }


    	return true;
    }

    @Override
    public String toString() {
        return "com.pmarlen.backend.model.EntradaSalida[id = "+id+ "]";
    }	

	/**
	 * @return the descRedondeo
	 */
	public Double getDescRedondeo() {
		return descRedondeo;
	}

	/**
	 * @param descRedondeo the descRedondeo to set
	 */
	public void setDescRedondeo(Double descRedondeo) {
		this.descRedondeo = descRedondeo;
	}

	/**
	 * @return the redondeoHab
	 */
	public Double getRedondeoHab() {
		return redondeoHab;
	}

	/**
	 * @param redondeoHab the redondeoHab to set
	 */
	public void setRedondeoHab(Double redondeoHab) {
		this.redondeoHab = redondeoHab;
	}

	/**
	 * @return the totalCobrado
	 */
	public Double getTotalCobrado() {
		return totalCobrado;
	}

	/**
	 * @param totalCobrado the totalCobrado to set
	 */
	public void setTotalCobrado(Double totalCobrado) {
		this.totalCobrado = totalCobrado;
	}

	/**
	 * @return the cambio
	 */
	public Double getCambio() {
		return cambio;
	}

	/**
	 * @param cambio the cambio to set
	 */
	public void setCambio(Double cambio) {
		this.cambio = cambio;
	}

	/**
	 * @return the pedioSucursal
	 */
	public Integer getPedioSucursal() {
		return pedioSucursal;
	}

	/**
	 * @param pedioSucursal the pedioSucursal to set
	 */
	public void setPedioSucursal(Integer pedioSucursal) {
		this.pedioSucursal = pedioSucursal;
	}

	/**
	 * @return the elemDet
	 */
	public Integer getElemDet() {
		return elemDet;
	}

	/**
	 * @param elemDet the elemDet to set
	 */
	public void setElemDet(Integer elemDet) {
		this.elemDet = elemDet;
	}

	/**
	 * @return the totProds
	 */
	public Integer getTotProds() {
		return totProds;
	}

	/**
	 * @param totProds the totProds to set
	 */
	public void setTotProds(Integer totProds) {
		this.totProds = totProds;
	}
}
