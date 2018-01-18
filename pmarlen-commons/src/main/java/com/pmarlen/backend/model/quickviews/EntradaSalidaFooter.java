package com.pmarlen.backend.model.quickviews;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class EntradaSalidaFooter implements Serializable{
	private Double subTotalNoGrabado;
	private Double subTotalBruto;
	private Integer descuentoCalculado;
	private Integer descuentoExtra;
	private Integer descuentoAplicado;
	private Double importeDescuentoCalculado;
	private Double importeDescuentoExtra;
	private Double importeDescuentoAplicado;
	private Double importeIVA;
	private Double total;  
    private Double cfd_subTotal;
    private Double cfd_iva;
    private Double cfd_total;
    private Double cfdi_descIncluido;
    
	private Integer totalUnidades;
	
	private static Logger logger= Logger.getLogger(EntradaSalidaFooter.class.getSimpleName());

	public EntradaSalidaFooter() {
		reset();
	}

	public void reset(){
		this.subTotalNoGrabado = 0.0;
		this.subTotalBruto = 0.0;
		this.descuentoCalculado=0;
		this.descuentoExtra=0;
		this.descuentoAplicado=0;
		this.importeDescuentoCalculado = 0.0;
		this.importeDescuentoExtra = 0.0;
		this.importeDescuentoAplicado = 0.0;
		this.importeIVA = 0.0;
		this.total = 0.0;
		this.totalUnidades = 0;
        this.cfd_subTotal = 0.0;
        this.cfd_iva = 0.0;
        this.cfd_total = 0.0;
        this.cfdi_descIncluido = 0.0;
	}
	
	public void calculaTotalesSucDesde(EntradaSalidaQuickView pv,List<? extends EntradaSalidaDetalle> dvpList){
		logger.debug("calculaTotalesSucDesde: Suc:"+pv.getSucursalId()+"<-------------------------------------");
		reset();
		double importeReg = 0.0;
		double importeRegNG = 0.0;
		totalUnidades = 0;
		
		
		double subTotal1ra = 0.0;	
		double subTotalOpo = 0.0;
		double subTotalReg = 0.0;
		int totalUnidadesDescontables = 0;
		for(EntradaSalidaDetalle dvp: dvpList){
			totalUnidades     += dvp.getCantidad();
			if(dvp instanceof EntradaSalidaDetalleQuickView){
				EntradaSalidaDetalleQuickView dvpq = (EntradaSalidaDetalleQuickView)dvp;
				if(dvpq.getApTipoAlmacen() == Constants.ALMACEN_PRINCIPAL){
					totalUnidadesDescontables += dvp.getCantidad();
				}
			}			
			importeReg         = dvp.getCantidad()*dvp.getPrecioVenta();
			importeIVA        += importeReg - (importeReg / Constants.MAS_IVA);
			importeRegNG       = (importeReg / Constants.MAS_IVA);
			subTotalNoGrabado += importeRegNG;
			subTotalBruto     += importeReg;
            
		}
		descuentoCalculado = 0;
		descuentoExtra = 0;
		descuentoAplicado =0;
		importeDescuentoExtra = 0.0;
		importeDescuentoAplicado = 0.0;
		Integer pdc = pv.getPorcentajeDescuentoCalculado();
		Integer pde = pv.getPorcentajeDescuentoExtra();
		logger.debug("calculaTotalesSucDesde: pdc="+pdc+", pde="+pde+", pv.getAutorizaDescuento()="+pv.getAutorizaDescuento()+",subTotalBruto="+subTotalBruto);
		if(pv.getAutorizaDescuento()!=null && pv.getAutorizaDescuento().intValue()==1){			
			
			logger.debug("calculaTotalesSucDesde: Pedido de SUCURSAL, recalculando: totalUnidades="+totalUnidades);
			if(totalUnidadesDescontables>=12 || totalUnidades>=12){
				descuentoCalculado = 10;
			} else {
				if (subTotalBruto >= 100.0 && subTotalBruto < 200.0) {
					descuentoCalculado = 5;

				} else if (subTotalBruto >= 200) {
					descuentoCalculado = 10;					
				}
			}
			descuentoExtra = pv.getPorcentajeDescuentoExtra();
			if(descuentoExtra == null){
				descuentoExtra = 0;
			}				

			descuentoAplicado         = descuentoCalculado + descuentoExtra;
			importeDescuentoExtra     = (subTotalBruto * descuentoExtra)/100.0;						
			importeDescuentoCalculado = (subTotalBruto * descuentoCalculado)/100.0;			
			importeDescuentoAplicado  = importeDescuentoCalculado + importeDescuentoExtra;			
		} 
		
		total = subTotalNoGrabado + importeIVA - importeDescuentoAplicado ;
		logger.debug("calculaTotalesSucDesde: Pedido de SUCURSAL:descuentoAplicado="+descuentoAplicado+", importeDescuentoAplicado="+importeDescuentoAplicado+", total="+total);
	}

	
	public void calculaTotalesDesde(EntradaSalida pv,List<? extends EntradaSalidaDetalle> dvpList){
		logger.debug("calculaTotalesDesde: Suc:"+pv.getSucursalId()+", dvpList.size="+dvpList.size());
		reset();
		double importeReg = 0.0;
		double importeRegNG = 0.0;
		totalUnidades = 0;
		for(EntradaSalidaDetalle dvp: dvpList){
			totalUnidades     += dvp.getCantidad();
			importeReg         = dvp.getCantidad()*dvp.getPrecioVenta();
			importeIVA        += importeReg - (importeReg / Constants.MAS_IVA);
			importeRegNG       = (importeReg / Constants.MAS_IVA);
			subTotalNoGrabado += importeRegNG;
			subTotalBruto     += importeReg;
			
		}
		descuentoCalculado = 0;
		descuentoExtra = 0;
		descuentoAplicado =0;
		importeDescuentoExtra = 0.0;
		importeDescuentoAplicado = 0.0;
		if(pv.getAutorizaDescuento()!=null && pv.getAutorizaDescuento().intValue()==1){
			
			Integer pdc = pv.getPorcentajeDescuentoCalculado();
			Integer pde = pv.getPorcentajeDescuentoExtra();
			int     pddb = 0;
			if(pdc !=null){
				pddb += pdc.intValue();
				descuentoCalculado = pdc.intValue();
			}
			if(pde !=null){
				pddb += pde.intValue();
				descuentoExtra = pde;
			}
			if(pddb == 0){
				logger.debug("calculaTotalesDesde: Pedido de Portal ?");
				if (subTotalBruto >= 5000 && subTotalBruto < 10000) {
					descuentoCalculado = 5;
					
				} else if (subTotalBruto >= 10000) {
					descuentoCalculado = 10;					
				}
				descuentoExtra = pv.getPorcentajeDescuentoExtra();
				if(descuentoExtra == null){
					descuentoExtra = 0;
				}				
			}
			descuentoAplicado         = descuentoCalculado + descuentoExtra;
			importeDescuentoExtra     = (subTotalBruto * descuentoExtra)/100.0;						
			importeDescuentoCalculado = (subTotalBruto * descuentoCalculado)/100.0;			
			importeDescuentoAplicado  = importeDescuentoCalculado + importeDescuentoExtra;			
		} 
		
		total = subTotalNoGrabado + importeIVA - importeDescuentoAplicado ;
	}
	
	public void calculaParaFacturaTotalesDesde(EntradaSalida pv,List<EntradaSalidaDetalleQuickView> esdList){
		reset();
		double importeReg = 0.0;
		double importeRegNG = 0.0;
        
        double factorDescuentos = 0.0;
		totalUnidades = 0;
		descuentoCalculado = 0;
		descuentoExtra = 0;
		descuentoAplicado =0;
		importeDescuentoExtra = 0.0;
		importeDescuentoAplicado = 0.0;
        
        for(EntradaSalidaDetalleQuickView esd: esdList){
            subTotalBruto += esd.getCantidad() * esd.getPrecioVenta();
        }
		if(pv.getAutorizaDescuento()!=null && pv.getAutorizaDescuento().intValue()==1){
			Integer pdc = pv.getPorcentajeDescuentoCalculado();
			Integer pde = pv.getPorcentajeDescuentoExtra();
			int     pddb = 0;
			if(pdc !=null){
				pddb += pdc.intValue();
				descuentoCalculado = pdc.intValue();
			}
			if(pde !=null){
				pddb += pde.intValue();
				descuentoExtra = pde;
			}
			if(pddb == 0){
				if (subTotalBruto >= 5000 && subTotalBruto < 10000) {
					descuentoCalculado = 5;					
				} else if (subTotalBruto >= 10000) {
					descuentoCalculado = 10;
				}
				descuentoExtra = pv.getPorcentajeDescuentoExtra();
				if(descuentoExtra == null){
					descuentoExtra = 0;
				}				
			}
			descuentoAplicado         = descuentoCalculado + descuentoExtra;
			importeDescuentoExtra     = (subTotalNoGrabado * descuentoExtra)/100.0;						
			importeDescuentoCalculado = (subTotalNoGrabado * descuentoCalculado)/100.0;						
			importeDescuentoAplicado  = importeDescuentoCalculado + importeDescuentoExtra;
		}
        subTotalBruto=0.0;
        cfd_subTotal =0.0;
        factorDescuentos = (descuentoAplicado/100.0);
        logger.debug("factorDescuentos="+factorDescuentos+" (DESC?"+pv.getAutorizaDescuento()+") pv.DC="+pv.getPorcentajeDescuentoCalculado()+", pv.DE="+pv.getPorcentajeDescuentoExtra()+"\tDESC_CALC="+descuentoCalculado + ",DESC_EXTRA="+descuentoExtra);
		for(EntradaSalidaDetalleQuickView esd: esdList){
            importeReg         = esd.getPrecioVenta();			
			importeRegNG       = (importeReg / Constants.MAS_IVA);
    
            final double cfd_valorUnitario = importeRegNG * (1.0 - factorDescuentos);
            
            esd.setCfd_valorUnitario (cfd_valorUnitario);
            esd.setCfd_importe       (cfd_valorUnitario * esd.getCantidad());
            esd.setCfd_base          (cfd_valorUnitario);
            esd.setCfd_importeIVA    (cfd_valorUnitario * pv.getFactorIva());
            
            esd.setCfdi_valorOriginal(importeRegNG);
            esd.setCfdi_descuento    (importeRegNG * (1.0 - factorDescuentos));
            esd.setCfdi_iva          (importeRegNG * (1.0 - factorDescuentos) * (1.0+pv.getFactorIva()));
            esd.setCfdi_importeFinal (cfd_valorUnitario * esd.getCantidad());
            
			totalUnidades     += esd.getCantidad();
			
			subTotalNoGrabado += importeRegNG * esd.getCantidad();
			subTotalBruto     += importeReg   * esd.getCantidad();
            
            cfd_subTotal        += esd.getCfd_importe();
            cfd_iva             += esd.getCfd_importeIVA() * esd.getCantidad();
            cfd_total           += esd.getCfd_importe() + ( esd.getCfd_importeIVA() * esd.getCantidad() );
            cfdi_descIncluido   += esd.getCfdi_descuento() * esd.getCantidad();
            
			logger.debug("\t["+
                    esd.getCantidad()+"\t"+
                    esd.getCfd_valorUnitario()  +"\t"+esd.getCfd_importe()      +"\t"+esd.getCfd_base()+"\t"+esd.getCfd_importeIVA()    +"]\t"+
                    "{"+
                    esd.getCfdi_valorOriginal() +"\t"+esd.getCfdi_descuento()   +"\t"+esd.getCfdi_iva()+"\t"+esd.getCfdi_importeFinal() +"}");
		}
		importeIVA = (subTotalNoGrabado - importeDescuentoAplicado) * Constants.IVA;		
		total      = subTotalNoGrabado - importeDescuentoAplicado + importeIVA;		
        logger.debug("----------------------------");
        logger.debug("CFD  SUBTOTAL :"+cfd_subTotal);
        logger.debug("          IVA :"+cfd_iva);
        logger.debug("CFDI DESC.INC.:"+cfdi_descIncluido);
        logger.debug("CFD     TOTAL :"+cfd_total);
	}

	/**
	 * @return the subTotalNoGrabado
	 */
	public Double getSubTotalNoGrabado() {
		return subTotalNoGrabado;
	}

	/**
	 * @return the subTotalBruto
	 */
	public Double getSubTotalBruto() {
		return subTotalBruto;
	}

	/**
	 * @return the descuentoCalculado
	 */
	public Integer getDescuentoCalculado() {
		return descuentoCalculado;
	}

	/**
	 * @return the descuentoExtra
	 */
	public Integer getDescuentoExtra() {
		return descuentoExtra;
	}

	/**
	 * @return the descuentoAplicado
	 */
	public Integer getDescuentoAplicado() {
		return descuentoAplicado;
	}

	/**
	 * @return the importeDescuentoCalculado
	 */
	public Double getImporteDescuentoCalculado() {
		return importeDescuentoCalculado;
	}

	/**
	 * @return the importeDescuentoExtra
	 */
	public Double getImporteDescuentoExtra() {
		return importeDescuentoExtra;
	}

	/**
	 * @return the importeDescuentoAplicado
	 */
	public Double getImporteDescuentoAplicado() {
		return importeDescuentoAplicado;
	}

	/**
	 * @return the importeIVA
	 */
	public Double getImporteIVA() {
		return importeIVA;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @return the totalUnidades
	 */
	public Integer getTotalUnidades() {
		return totalUnidades;
	}

    /**
     * @return the cfd_subTotal
     */
    public Double getCfd_subTotal() {
        return cfd_subTotal;
    }

    /**
     * @return the cfd_iva
     */
    public Double getCfd_iva() {
        return cfd_iva;
    }

    /**
     * @return the cfd_total
     */
    public Double getCfd_total() {
        return cfd_total;
    }

    /**
     * @return the cfdi_descIncluido
     */
    public Double getCfdi_descIncluido() {
        return cfdi_descIncluido;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\t----EntradaSalidaFooter----\n");
		sb.append("\t #UNIDADES:\t#"+totalUnidades+"\n");
		sb.append("\t SUBTOT.NG:\t"+Constants.getImporteMoneda(subTotalNoGrabado)+"\n");
		sb.append("\tSUBTOTAL B:\t"+Constants.getImporteMoneda(subTotalBruto)+" = ("+Constants.getImporteDesglosado(subTotalBruto)+")\n");
		sb.append("\t  SUBTOTAL:\t"+Constants.getImporteMoneda(subTotalNoGrabado)+"\n");
		sb.append("\t-DESCUENTO:\t( "+descuentoCalculado+"% + "+descuentoExtra+"% = "+descuentoAplicado+"%) = "+Constants.getImporteMoneda(importeDescuentoAplicado)+"\n");
		sb.append("\t   +I.V.A.:\t"+Constants.getImporteMoneda(importeIVA)+"\n");
		sb.append("\t T O T A L:\t"+Constants.getImporteMoneda(total)+"\n");
		sb.append("\t===========================\n");
		return sb.toString(); 
	}	
}
