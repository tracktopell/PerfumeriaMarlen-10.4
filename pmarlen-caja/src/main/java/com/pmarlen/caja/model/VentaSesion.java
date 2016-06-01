/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.model;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.ClienteQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.reports.GeneradorImpresionPedidoVenta;
import com.pmarlen.businesslogic.reports.NumeroCastellano;
import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.control.PanelVentaControl;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import com.pmarlen.rest.dto.U;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class VentaSesion {
	private static Logger logger = Logger.getLogger(VentaSesion.class.getName());
	private ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList;
	private ES_ESD venta;
	
	private Cliente cliente;
	private FormaDePago formaDePago;
	private MetodoDePago metodoDePago;
	private double importeRecibido;
	private double subTotal1ra;	
	private double subTotalOpo;
	private double subTotalReg;
	private double totalBruto;
	private double totalBrutoDescontable;
	private double totalBrutoFijo;
	
	private double total;
	private double totalRedondeado2Dec;
	//private boolean descuentoMayoreoHabilitado;
	private double  descuentoCalculado ;
	private double  descuentoFactor;	
	private int		porcentajeDescuentoCalculado;
	private int		porcentajeDescuentoExtra;
	private int		numElemVta;
	private int		nunElemDescontablesVta;
	private int		numElemSinDescVta;

	private String autorizacion;	
	
	public VentaSesion(){
		nuevaSesionVenta();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}
	
	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}

	public MetodoDePago getMetodoDePago() {
		return metodoDePago;
	}
	
	public void setMetodoDePago(MetodoDePago metodoDePago) {
		this.metodoDePago = metodoDePago;
	}
	
	public void nuevaSesionVenta() {
		logger.debug("nuevaSesionVenta:");
		detalleVentaTableItemList = new ArrayList<PedidoVentaDetalleTableItem>();
		cliente			= new ClienteQuickView	();
		formaDePago     = new FormaDePago		(Constants.ID_FDP_1SOLA_E);
		metodoDePago    = new MetodoDePago		(Constants.ID_MDP_EFECTIVO);

		importeRecibido = 0.0;
		totalBruto		= 0.0;
		total			= 0.0;
		subTotal1ra		= 0.0;	
		subTotalOpo		= 0.0;
		subTotalReg		= 0.0;
		porcentajeDescuentoCalculado =0;
		porcentajeDescuentoExtra=0;
	
		autorizacion    = null;
		
		venta			= null;
	}

	public ES_ESD getVenta() {
		if(venta == null) {			
			venta = new ES_ESD();
		}
		ArrayList<ESD> detalleVentaList = new ArrayList<ESD>();
		for (PedidoVentaDetalleTableItem dvil : detalleVentaTableItemList) {
			detalleVentaList.add(dvil.getPvd());
		}
		venta.getEs().setTm(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA);
		venta.getEs().setJ(MemoryDAO.getNumCaja());
		venta.getEs().setC(cliente.getId());
		venta.getEs().setFc(System.currentTimeMillis());
		venta.getEs().setFp(formaDePago.getId());
		venta.getEs().setIr(importeRecibido);
		venta.getEs().setPdc(porcentajeDescuentoCalculado);
		venta.getEs().setPde(porcentajeDescuentoExtra);		
		venta.getEs().setMp(metodoDePago.getId());
		venta.getEs().setAmc(autorizacion);
		venta.getEs().setI(Constants.IVA);		
		venta.getEs().setnElem(numElemVta);
		venta.getEs().setS(MemoryDAO.getSucursalId());
		venta.setEsdList(detalleVentaList);
		venta.getEs().setU(ApplicationLogic.getInstance().getLogged().getE());		

		calcularTotales();
		
		venta.getEs().setSt1(subTotal1ra);
		venta.getEs().setStO(subTotalOpo);
		venta.getEs().setStR(subTotalReg);
		venta.getEs().setDesc(descuentoCalculado);
		venta.getEs().setTot(total);
		venta.getEs().setnElem(numElemVta);

		return venta;
	}

	public ArrayList<PedidoVentaDetalleTableItem> getDetalleVentaTableItemList() {
		return detalleVentaTableItemList;
	}
	
	public void calcularTotales() {
		totalBruto = 0.0;		
		numElemVta=0;
		nunElemDescontablesVta = 0;
		numElemSinDescVta = 0;
		boolean descuentoAplicado  = false;
		descuentoFactor    = 0.0;		
		total = 0.0;
		totalBrutoFijo=0.0;
		totalBrutoDescontable=0.0;
		porcentajeDescuentoCalculado = 0;
		subTotal1ra		= 0.0;	
		subTotalOpo		= 0.0;
		subTotalReg		= 0.0;
		logger.debug("calcularTotales:porcentajeDescuentoCalculado="+porcentajeDescuentoCalculado+", ImporteRecibido="+importeRecibido);
		int max12=0;
		for (PedidoVentaDetalleTableItem dvti : detalleVentaTableItemList) {
			
			if(dvti.getTipoAlmacen() == Constants.ALMACEN_PRINCIPAL){
				totalBrutoDescontable += dvti.getI();
				nunElemDescontablesVta += dvti.getPvd().getC();				
				if(dvti.getPvd().getC()>=max12){
					max12=dvti.getPvd().getC();
				}
				subTotal1ra		   += dvti.getI();
			} else if(dvti.getTipoAlmacen() == Constants.ALMACEN_OPORTUNIDAD){
				totalBrutoFijo     += dvti.getI();
				numElemSinDescVta  += dvti.getPvd().getC();
				subTotalOpo		   += dvti.getI();
			} else if(dvti.getTipoAlmacen() == Constants.ALMACEN_REGALIAS){
				totalBrutoFijo     += dvti.getI();
				numElemSinDescVta  += dvti.getPvd().getC();
				subTotalOpo		   += dvti.getI();
			}
		}
		numElemVta = nunElemDescontablesVta + numElemSinDescVta;
		totalBruto = totalBrutoDescontable + totalBrutoFijo;
		logger.debug("calcularTotales:totalBrutoDescontable="+totalBrutoDescontable+", nunElemDescontablesVta="+nunElemDescontablesVta+", max12="+max12);
		if(		MemoryDAO.getSucursal()!= null &&
				(	MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=null && 
					MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=0)){
			if(totalBrutoDescontable >= Constants.IMPORTE_DES_MAY2_SUC || max12 >= 12){
				descuentoFactor   = Constants.FACTOR_DES_MAY2_SUC;
				porcentajeDescuentoCalculado     = 10;
				descuentoAplicado = true;
				logger.debug("calcularTotales: 1)\tporcentajeDescuentoCalculado="+porcentajeDescuentoCalculado+", descuentoFactor="+descuentoFactor);
			} else if(totalBrutoDescontable >= Constants.IMPORTE_DES_MAY_SUC && totalBrutoDescontable < Constants.IMPORTE_DES_MAY2_SUC){
				descuentoFactor   = Constants.FACTOR_DES_MAYSUC;
				porcentajeDescuentoCalculado     = 5;
				descuentoAplicado = true;
				logger.debug("calcularTotales: 2)\tporcentajeDescuentoCalculado="+porcentajeDescuentoCalculado+", descuentoFactor="+descuentoFactor);
			}
		}
		descuentoCalculado = totalBrutoDescontable * descuentoFactor;
		total = totalBrutoDescontable - descuentoCalculado + totalBrutoFijo;
		try{
			totalRedondeado2Dec = Constants.df2Decimal.parse(Constants.df2Decimal.format(total)).doubleValue();
		}catch(ParseException pe){
			totalRedondeado2Dec = total;
		}
		logger.debug("calcularTotales: totalBruto="+totalBruto+", descuentoCalculado="+descuentoCalculado+", totalRedondeado2Dec="+totalRedondeado2Dec+", total="+total);
	}

	public int getNumElemVta() {
		return numElemVta;
	}

	public int getNumElemSinDescVta() {
		return numElemSinDescVta;
	}

	public int getNunElemDescontablesVta() {
		return nunElemDescontablesVta;
	}

	public double getTotal() {
		return total;
	}

	public double getTotalBruto() {
		return totalBruto;
	}

	public double getTotalBrutoDescontable() {
		return totalBrutoDescontable;
	}

	public double getTotalBrutoFijo() {
		return totalBrutoFijo;
	}
	
	public double getTotalRedondeado2Dec() {
		return totalRedondeado2Dec;
	}

	public double getDescuentoCalculado() {
		return descuentoCalculado;
	}

	public double getDescuentoFactor() {
		return descuentoFactor;
	}

	public int getPorcentajeDescuentoCalculado() {
		return porcentajeDescuentoCalculado;
	}

	public int getPorcentajeDescuentoExtra() {
		return porcentajeDescuentoExtra;
	}

	public void setImporteRecibido(double importeRecibido) {
		this.importeRecibido = importeRecibido;
	}
	
	public static JarpeReportsInfoDTO generaJarpeReportsInfoDTOTicket(ES_ESD ventax){
		logger.debug("->generaJarpeReportsInfoDTOTicket: --------------->> TICKET: "+ventax.getEs().getNt());
		EntradaSalidaQuickView es = ventax.getEs().reverse();
		List<ESD> esd2List = ventax.getEsdList();
		List<EntradaSalidaDetalleQuickView> esdList = new ArrayList<EntradaSalidaDetalleQuickView>();
		for(ESD esd2: esd2List){
			I pp = MemoryDAO.fastSearchProducto(esd2.getCb());
			EntradaSalidaDetalleQuickView rESD = esd2.reverse();
			rESD.setProductoNombre(pp.getN());
			rESD.setProductoPresentacion(pp.getPr());
			esdList.add(rESD);
		}
		 
		EntradaSalidaFooter entradaSalidaFooter;
		entradaSalidaFooter= new EntradaSalidaFooter();
		
		logger.debug("->generaJarpeReportsInfoDTOTicket:-> importeRecibido:"+es.getImporteRecibido());
		
		entradaSalidaFooter.calculaTotalesSucDesde(es, esdList);
		logger.debug("->generaJarpeReportsInfoDTOTicket:-> calculaTotalesSucDesde:entradaSalidaFooter="+entradaSalidaFooter);
		
		es.setImporteDescuento(entradaSalidaFooter.getImporteDescuentoAplicado());
		entradaSalidaFooter.getSubTotalBruto();
		
		com.pmarlen.backend.model.Sucursal suc = MemoryDAO.getSucursal();
		Usuario usuarioLogedin = new Usuario();
		
		usuarioLogedin.setEmail         (ApplicationLogic.getInstance().getLogged().getE());
		usuarioLogedin.setNombreCompleto(ApplicationLogic.getInstance().getLogged().getN());
		usuarioLogedin.setClave         (ApplicationLogic.getInstance().getLogged().getC());
		
		Cliente clienteES= MemoryDAO.getCliente(es.getClienteId());
		
		es.setClienteDirFacturacion(clienteES.getDireccionFacturacion());
		es.setClienteNombreEstablecimiento(clienteES.getNombreEstablecimiento());
		es.setClienteRazonSocial(clienteES.getRazonSocial());
		es.setClienteRFC(clienteES.getRfc());
		
		
		U uc = MemoryDAO.getUsuario(es.getUsuarioEmailCreo());
		es.setUsuarioClave(uc.getC());
		es.setUsuarioNombreCompleto(uc.getN());

		FormaDePago  fp1 = MemoryDAO.getFormaDePago(es.getFormaDePagoId());
		MetodoDePago mp1 = MemoryDAO.getMetodoDePago(es.getMetodoDePagoId());
		
		es.setFormaDePagoDescripcion(fp1.getDescripcion());
		es.setMetodoDePagoDescripcion(mp1.getDescripcion());
		
		return GeneradorImpresionPedidoVenta.generaJarpeReportsInfoDTOTextTicket(es, esdList, suc, usuarioLogedin);		
	}
	
}
