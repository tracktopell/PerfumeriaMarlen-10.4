/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.model;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.quickviews.ClienteQuickView;
import com.pmarlen.businesslogic.reports.NumeroCastellano;
import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import com.pmarlen.rest.dto.U;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alfredo
 */
public class VentaSesion {
	private ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList;
	private ES_ESD venta;
	
	private Cliente cliente;
	private FormaDePago formaDePago;
	private MetodoDePago metodoDePago;
	private double importeRecibido;
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
		detalleVentaTableItemList = new ArrayList<PedidoVentaDetalleTableItem>();
		cliente			= new ClienteQuickView	();
		formaDePago     = new FormaDePago		(Constants.ID_FDP_1SOLA_E);
		metodoDePago    = new MetodoDePago		(Constants.ID_MDP_EFECTIVO);

		importeRecibido = 0.0;
		totalBruto		= 0.0;
		total			= 0.0;
		porcentajeDescuentoCalculado =0;
		porcentajeDescuentoExtra=0;
	
		autorizacion    = null;
		
		venta			= null;
	}

	public ES_ESD getVenta() {		
		if(venta == null) {
			ArrayList<ESD> detalleVentaList = new ArrayList<ESD>();
			for (PedidoVentaDetalleTableItem dvil : detalleVentaTableItemList) {
				detalleVentaList.add(dvil.getPvd());
			}
			venta = new ES_ESD();

			venta.getEs().setTm(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA);
			venta.getEs().setJ(MemoryDAO.getNumCaja());
			venta.getEs().setC(cliente.getId());
			venta.getEs().setFc(System.currentTimeMillis());
			venta.getEs().setFp(formaDePago.getId());
			venta.getEs().setIr(importeRecibido);
			venta.getEs().setPdc(porcentajeDescuentoCalculado);
			venta.getEs().setPde(porcentajeDescuentoExtra);
			venta.getEs().setIr(importeRecibido);
			venta.getEs().setMp(metodoDePago.getId());
			venta.getEs().setAmc(autorizacion);
			venta.getEs().setI(Constants.IVA);
			venta.getEs().setS(MemoryDAO.getSucursalId());
			venta.setEsdList(detalleVentaList);
			venta.getEs().setU(ApplicationLogic.getInstance().getLogged().getE());		
			
			calcularTotales();
			
			venta.getEs().setStot(totalBruto);
			venta.getEs().setDesc(descuentoCalculado);
			venta.getEs().setTot(total);
			venta.getEs().setnElem(numElemVta);
			
		}
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
		for (PedidoVentaDetalleTableItem dvti : detalleVentaTableItemList) {
			if(dvti.getTipoAlmacen() == Constants.ALMACEN_PRINCIPAL){
				totalBrutoDescontable += dvti.getI();
				nunElemDescontablesVta += dvti.getPvd().getC();
			} else {
				totalBrutoFijo        += dvti.getI();
				numElemSinDescVta += dvti.getPvd().getC();
			}
		}
		numElemVta = nunElemDescontablesVta + numElemSinDescVta;
		totalBruto = totalBrutoDescontable + totalBrutoFijo;
		
		if(		MemoryDAO.getSucursal()!= null &&
				(	MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=null && 
					MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=0)){
			if(totalBrutoDescontable >= Constants.IMPORTE_DES_MAY_SUC && totalBrutoDescontable < Constants.IMPORTE_DES_MAY2_SUC && nunElemDescontablesVta< 12){
				descuentoFactor   = Constants.FACTOR_DES_MAYSUC;
				porcentajeDescuentoCalculado     = 5;
				descuentoAplicado = true;
			} else if(totalBrutoDescontable >= Constants.IMPORTE_DES_MAY_SUC || nunElemDescontablesVta>= 12){
				descuentoFactor   = Constants.FACTOR_DES_MAY2_SUC;
				porcentajeDescuentoCalculado     = 10;
				descuentoAplicado = true;
			}
		}
		descuentoCalculado = totalBrutoDescontable * descuentoFactor;
		total = totalBrutoDescontable - descuentoCalculado + totalBrutoFijo;
		try{
			totalRedondeado2Dec = Constants.df2Decimal.parse(Constants.df2Decimal.format(total)).doubleValue();
		}catch(ParseException pe){
			totalRedondeado2Dec = total;
		}
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
	
	
	public static JarpeReportsInfoDTO generaJarpeReportsInfoDTOTicket(ES_ESD ventax){
		HashMap<String, Object> parameters = new HashMap<String, Object> (); 
		List<HashMap<String, String>> records=new ArrayList<HashMap<String, String>>();
		
		parameters.put("L.venta.ticket"    , "NO. TICKET:");
		parameters.put("venta.ticket"      , ventax.getEs().getNt());
		parameters.put("sucursal.nombre"   , MemoryDAO.getSucursal().getNombre());
		
		final String nombre1 = MemoryDAO.getSucursal().getNombre().substring(0, MemoryDAO.getSucursal().getNombre().indexOf("S.A. DE C.V.")+13);
		final String nombre2 = MemoryDAO.getSucursal().getNombre().substring(MemoryDAO.getSucursal().getNombre().indexOf("S.A. DE C.V.")+13);
		
		parameters.put("sucursal.nombre1"  , nombre1);
		parameters.put("sucursal.nombre2"  , nombre2);
		
		List<String> direccionList = TextReporter.justifyText(MemoryDAO.getSucursal().getDireccion(), TextReporter.columns);
		
		parameters.put("sucursal.direccion", MemoryDAO.getSucursal().getDireccion());
		
		for(int i=1;i<=direccionList.size();i++){
			parameters.put("sucursal.direccion"+i, direccionList.get(i-1));
		}
		
		parameters.put("sucursal.tels", "TELS.:"+MemoryDAO.getSucursal().getTelefonos());
		
		
		parameters.put("L.fecha.creado", "FECHA V.:");
		parameters.put("fecha.creado", Constants.sdfShortDateTime.format(new Date(ventax.getEs().getFc())));
		
		parameters.put("L.fecha.actual", "FECHA:");
		final Date today = new Date();
		parameters.put("fecha.actual", Constants.sdfShortDate.format(today));
		parameters.put("hora.actual", Constants.sdfShortTime.format(today));
		
		final U uc = MemoryDAO.getUsuario(ventax.getEs().getU());
		
		parameters.put("usuario.creo.email", uc.getE());
		parameters.put("L.usuario.creo.nombre", "ATENDIO:");
		parameters.put("usuario.creo.nombre", uc.getN());
		
		parameters.put("usuario.imprimio.email" , ApplicationLogic.getInstance().getLogged().getE());
		parameters.put("usuario.imprimio.nombre", ApplicationLogic.getInstance().getLogged().getN());
		parameters.put("sucursal.caja.actual", MemoryDAO.getNumCaja());
		parameters.put("sucursal.caja.creo", ventax.getEs().getJ());
		final Cliente cte = MemoryDAO.getCliente(ventax.getEs().getC());
		parameters.put("cliente.rfc"  , cte.getRfc());
		parameters.put("cliente.estab", cte.getNombreEstablecimiento());
		parameters.put("cliente.racSoc", cte.getRazonSocial());
		List<String> racSocList = TextReporter.justifyText(cte.getRazonSocial(), TextReporter.columns - 11);
		for (int i = 1; i <= racSocList.size(); i++) {
			parameters.put("cliente.racSoc" + i, racSocList.get(i - 1));
		}
		parameters.put("cliente.cirFac", cte.getDireccionFacturacion());
		
		parameters.put("formaDePago.desc",MemoryDAO.getFormaDePago(ventax.getEs().getFp()));
		parameters.put("metodoDePago.desc",MemoryDAO.getMetodoDePago(ventax.getEs().getFp()));
		parameters.put("cliente.cirFac", cte.getDireccionFacturacion());
		
		boolean descuentoAplicadoX  = false;
		double  descuentoFactorX    = 0.0;
		double  descuentoCalculadoX = 0.0;		
		double	totalBrutoDescontableX = 0.0;
		double	totalBrutoFijoX        = 0.0;
		int		numElemX=0;
		int		nunElemDescontablesX=0;
		int		numElemSinDescX=0;
		double	importeBrutoX =0.0;
		
		for (ESD esd: ventax.getEsdList()) {
			HashMap<String, String> r=new HashMap<String, String>();
			
			importeBrutoX = esd.getC()*esd.getP();
			
			if(esd.getA() == Constants.ALMACEN_PRINCIPAL){
				totalBrutoDescontableX  += importeBrutoX;
				nunElemDescontablesX    += esd.getC();
			} else {
				totalBrutoFijoX			+= importeBrutoX;
				numElemSinDescX			+= esd.getC();
			}
			final I px = MemoryDAO.fastSearchProducto(esd.getCb());
			r.put("pvd.producto.cb"    , px.getCb());
			r.put("pvd.producto.nombre", px.getN());
			r.put("pvd.producto.present", px.getPr());
			r.put("pvd.cant"			, String.valueOf(esd.getC()));
			r.put("pvd.precio"			, String.valueOf(esd.getP()));
			r.put("pvd.imp"				, Constants.df2Decimal.format(importeBrutoX));
			
			records.add(r);

		}
		numElemX = nunElemDescontablesX + numElemSinDescX;		
		double totalBrutoX = totalBrutoDescontableX + totalBrutoFijoX;
		
		if(MemoryDAO.getSucursal()!= null && (MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=null && MemoryDAO.getSucursal().getDescuentoMayoreoHabilitado()!=0)){
			if(totalBrutoDescontableX >= 10.00 || nunElemDescontablesX>= 12){
				descuentoFactorX   = Constants.FACTOR_DES_MAYSUC;
				descuentoAplicadoX = true;
			}
		}
		descuentoCalculadoX = totalBrutoDescontableX * descuentoFactorX;
		double totalX = totalBrutoDescontableX - descuentoCalculadoX + totalBrutoFijoX;
				
		parameters.put("fot.neDesc"			, String.valueOf(nunElemDescontablesX));
		parameters.put("fot.neSinDesc"		, String.valueOf(numElemSinDescX));
		parameters.put("fot.neTotElem"		, String.valueOf(numElemX));
		parameters.put("fot.neTotElemL"		, " <-- # PRODS.");
		
		parameters.put("fot.subTotDesc"		, Constants.df2Decimal.format(totalBrutoDescontableX));
		parameters.put("fot.subTotSinDesc"	, Constants.df2Decimal.format(totalBrutoFijoX));
		parameters.put("fot.subTot"			, Constants.df2Decimal.format(totalBrutoX));
		parameters.put("fot.desc"			, Constants.df2Decimal.format(descuentoCalculadoX));
		
		final String totalXval = Constants.df2Decimal.format(totalX);
		
		parameters.put("fot.tot"			, totalXval);
		parameters.put("fot.impRecib"		, Constants.df2Decimal.format(ventax.getEs().getIr()));		
		parameters.put("fot.cambio"		    , Constants.df2Decimal.format(ventax.getEs().getIr()-totalX));		
		
		String intDecParts[] = totalXval.split("\\.");            
		String letrasParteEntera  = NumeroCastellano.numeroACastellano(Long.parseLong(intDecParts[0])).trim();
		parameters.put("fot.leyendaNoFiscal" , "ESTO NO ES UN COMPROBANTE FISCAL");
		
		parameters.put("fot.totLetra"		,"**("+(letrasParteEntera+" Pesos "+intDecParts[1]+"/100 M.N.").toUpperCase()+")**");
		final String importeTotal = "**("+(letrasParteEntera+" Pesos "+intDecParts[1]+" / 100 M.N.").toUpperCase()+")**";
		List<String> totalLetraLineas = TextReporter.splitInLinesText(importeTotal, TextReporter.columns,'*');
		
		//parameters.put("fot.totLetra1" , "-----------------");
		//parameters.put("fot.totLetra2" , "-----------------");
		//parameters.put("fot.totLetra3" , "-----------------");
		for (int i = 1; i <= totalLetraLineas.size(); i++) {
			parameters.put("fot.totLetra" + i, totalLetraLineas.get(i - 1));
		}
		
		parameters.put("fot.leyendaNoFiscal" , "ESTE NO ES UN COMPROBANTE FISCAL");
		parameters.put("fot.leyenda1" , "¡ LE AGRADECEMOS SU COMPRA !");
		parameters.put("fot.leyenda2" , "VUELVA PRONTO");		
		parameters.put("fot.leyenda3" , "http://perfumeriamarlen.com.mx");		
		parameters.put("fot.leyenda4" , "http://facebook.com/PerfumeriaMarlen");

		return new JarpeReportsInfoDTO(parameters, records);
	}

}
