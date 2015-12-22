package com.pmarlen.businesslogic;

import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ESD;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * LogicaFinaciera
 */
public class LogicaFinaciera {
	protected static transient Logger logger = Logger.getLogger(LogicaFinaciera.class.getName());
	
	public static final int CALCULO_DESC_ALMACEN   = 0;
	public static final int CALCULO_DESC_SUCUSALES = 1;
	
    public static double getImpuestoIVA() {
        return 0.16;
    }
	
	public static TotalesCalculados calculaTotales(ES es, List<ESD> esdList,boolean descunetoHabilitado,boolean redondear,double recibidoTarjeta){
		List<Object[]> esdListObj = new ArrayList<Object[]>();
		
		for(ESD esd: esdList){
			Object[] arr=new Object[]{esd.getTa(),esd.getC(),esd.getP()};
			esdListObj.add(arr);
		}
		
		return calculaTotales(es.getI(), esdListObj, descunetoHabilitado, recibidoTarjeta, redondear, es.getIr(), recibidoTarjeta,CALCULO_DESC_SUCUSALES);
	}
	
	public static TotalesCalculados calculaTotales(EntradaSalidaQuickView es, List<EntradaSalidaDetalleQuickView> esdList,boolean redondear,double recibidoTarjeta,int estrategiaDesc){
		logger.debug("calculaTotales("+redondear+","+recibidoTarjeta+","+estrategiaDesc+")");
		List<Object[]> esdListObj = new ArrayList<Object[]>();
		
		for(EntradaSalidaDetalleQuickView esd: esdList){
			Object[] arr=new Object[]{esd.getApTipoAlmacen(),esd.getCantidad(),esd.getPrecioVenta()};
			esdListObj.add(arr);
		}
		
		double factorIva = es.getFactorIva();
		Integer autorizaDescuento = es.getAutorizaDescuento();
		boolean descuento = autorizaDescuento!=null&&autorizaDescuento.intValue()==1;
		logger.debug("calculaTotales:autorizaDescuento="+autorizaDescuento+", descuento="+descuento);
		Integer porcentajeDescuentoExtra = es.getPorcentajeDescuentoExtra()!=null?es.getPorcentajeDescuentoExtra():0;
		Double importeRecibido = es.getImporteRecibido()!=null?es.getImporteRecibido():0.0;
		
		return calculaTotales(factorIva, esdListObj, descuento, porcentajeDescuentoExtra/100.0, redondear, importeRecibido, recibidoTarjeta,estrategiaDesc);
	}
	public static TotalesCalculados calculaTotales(double factorIva,List<Object[]> esdList,boolean descunetoHabilitado, double factorDescExtra,boolean redondear, double recibidoEfectivo,double recibidoTarjeta,int estrategiaDesc){
		double st1ra=0.0;
		double stOpo=0.0;
		double stReg=0.0;
		int numProd1ra=0;
		for(Object[]  esd: esdList){
			
			int	tipoAlm = (Integer)esd[0];
			int	cant    = (Integer)esd[1];
			double	precio  = (Double) esd[2];

			
			if(tipoAlm == Constants.ALMACEN_PRINCIPAL){
				st1ra += cant * precio;
				numProd1ra += cant;
			} else if(tipoAlm == Constants.ALMACEN_OPORTUNIDAD){
				stOpo += cant * precio;
				
			} else if(tipoAlm == Constants.ALMACEN_REGALIAS){
				stReg += cant * precio;				
			}			
		}
		return calculaTotales(factorIva, numProd1ra,st1ra, stOpo, stReg, descunetoHabilitado, factorDescExtra, redondear, recibidoEfectivo, recibidoTarjeta, estrategiaDesc);
	
	}
	
	public static TotalesCalculados calculaTotales(double factorIva,int numProd1ra,double st1ra,double stOpo,double stReg,boolean descunetoHabilitado, double factorDescExtra,boolean redondear, double recibidoEfectivo,double recibidoTarjeta,int estrategiaDesc){
		TotalesCalculados tc= new TotalesCalculados();
		
		tc.subTotal1ra = st1ra;
		tc.subTotalOpo = stOpo;
		tc.subTotalReg = stReg;		
		
		tc.subTotal1raNG = tc.subTotal1ra/(1.0+factorIva);
		tc.subTotalOpoNG = tc.subTotalOpo/(1.0+factorIva);
		tc.subTotalRegNG = tc.subTotalReg/(1.0+factorIva);
		tc.subTotalNG    = tc.subTotal1raNG + tc.subTotalOpoNG + tc.subTotalRegNG;
		
		
		tc.subTotal     = tc.subTotal1ra + tc.subTotalReg + tc.subTotalOpo;
		
		tc.factorDescCalculado = 0.0;
		tc.descuentoRedondeo   = 0.0;
		tc.descuento           = 0.0;
		
		if(descunetoHabilitado){
			if(estrategiaDesc == CALCULO_DESC_SUCUSALES){
				if((tc.subTotal1ra+tc.subTotalReg)>=200.0 || numProd1ra >= 12){
					tc.factorDescCalculado = 0.1;
					tc.porcentajeCalculado = 10;
				} else if(((tc.subTotal1ra+tc.subTotalReg)>=100.0 && (tc.subTotal1ra+tc.subTotalReg)<200.0) && numProd1ra < 12){
					tc.factorDescCalculado = 0.05;
					tc.porcentajeCalculado = 5;
				}
				tc.descuento = (tc.subTotal1ra+tc.subTotalReg) * tc.factorDescCalculado + (tc.subTotal1ra+tc.subTotalReg) * factorDescExtra;
			} else if(estrategiaDesc == CALCULO_DESC_ALMACEN){
				// PORTAL
				if((tc.subTotal1raNG + tc.subTotalRegNG)>=10000.0){
					tc.factorDescCalculado = 0.1;
					tc.porcentajeCalculado = 10;
				} else if(((tc.subTotal1raNG + tc.subTotalRegNG)>=5000.0 && (tc.subTotal1raNG + tc.subTotalRegNG)<10000.0)){
					tc.factorDescCalculado = 0.05;
					tc.porcentajeCalculado = 5;
				}
				tc.descuento = (tc.subTotal1raNG + tc.subTotalRegNG) * tc.factorDescCalculado + (tc.subTotal1raNG + tc.subTotalRegNG) * factorDescExtra;
			}
			
		}
		if(estrategiaDesc == CALCULO_DESC_SUCUSALES){
			tc.total = tc.subTotal - tc.descuento;
			tc.iva   = 0.0;
		} else if(estrategiaDesc == CALCULO_DESC_ALMACEN){
			tc.total = tc.subTotalNG - tc.descuento;
			tc.iva   = tc.total * factorIva;
		}
		
		tc.totalFinal = tc.total + tc.iva;
		
		double totalRedoendado = tc.totalFinal;
		
		if(redondear){
			totalRedoendado = redondearFavorCliente(tc.totalFinal);			
		}
		
		tc.totalCobrar      = totalRedoendado;
		tc.recibidoEfectivo = recibidoEfectivo;
		tc.cambio = 0.0;
		if(tc.recibidoEfectivo > 0){			
			tc.recibidoTarjeta  = recibidoTarjeta;		
			tc.cambio = tc.recibidoEfectivo - tc.totalCobrar;
		}
		return tc;
	}
	
	public static double redondearFavorCliente(double d){
		
		double centavos = 0.0;
		double totalRedoendado = d;
		
			int totalEntero = (int)d;
			centavos = d - totalEntero;
			if(centavos>0 && centavos<0.50){
				totalRedoendado = (double)totalEntero;
			} else if (centavos>=0.50 ){
				totalRedoendado = (double)totalEntero + 0.5;
			}
		
		return totalRedoendado;
	}
	
	public static void main(String[] args) {
		double factorIva = getImpuestoIVA();
		List<Object[]> esdList = new ArrayList<Object[]>();
		boolean descunetoHabilitado = true;
		double factorDescExtra = 0.0;
		boolean redondear = false;
		double recibidoEfectivo = 0.0;
		double recibidoTarjeta = 0.0;
		
		esdList.add(new Object[]{1,   200,   5.50});
		esdList.add(new Object[]{1,   400,   5.50});
		esdList.add(new Object[]{1,   600,   5.50});
		esdList.add(new Object[]{1,   800,  19.76});
		esdList.add(new Object[]{2,   300,  27.50});
		esdList.add(new Object[]{2,   700,  19.03});
		esdList.add(new Object[]{3,   100,   0.0});
		esdList.add(new Object[]{3,   100,   0.0});
		esdList.add(new Object[]{3,   100,   0.0});
		
		TotalesCalculados tc1 = calculaTotales(factorIva, esdList, descunetoHabilitado, factorDescExtra, false, recibidoEfectivo, recibidoTarjeta, CALCULO_DESC_ALMACEN);
		
		System.out.println(tc1.toString(true));
		System.out.println(tc1.toString(false));
		
		TotalesCalculados tc2 = calculaTotales(factorIva, esdList, descunetoHabilitado, factorDescExtra, true, recibidoEfectivo, recibidoTarjeta, CALCULO_DESC_SUCUSALES);
		
		System.out.println(tc2.toString(true));
		System.out.println(tc2.toString(false));
	}
	

}
