package com.pmarlen.digifactws2018.production.client;

import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.exception.CFDInvokingWSException;

import com.pmarlen.model.Constants;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.tempuri.ArrayOfConceptoCFDI;
import org.tempuri.ArrayOfImpuesto;
import org.tempuri.CFDIRequest;
import org.tempuri.CFDIResponse;
import org.tempuri.ConceptoCFDI;
import org.tempuri.ConceptosCFDI;
import org.tempuri.DatosCFDI;
import org.tempuri.DigiFact;
import org.tempuri.DigiFactSoap;
import org.tempuri.Impuesto;
import org.tempuri.ReceptorCFDI;
/**
 *
 * @author alfredo
 */
public class DigifactClient {

	private static Logger logger = Logger.getLogger(DigifactClient.class.getSimpleName());

	public static Cfd invokeWSFactura(Cfd cfdVenta, EntradaSalidaQuickView pedidoVenta, ArrayList<EntradaSalidaDetalleQuickView> esdList, Cliente cliente, String serie, String usuario, String contrasena) throws CFDInvokingWSException{
		
		try{
			logger.debug("Adding trust to certificates");
			System.setProperty ( "javax.net.ssl.trustStore ","com.pmarlen.digifactws20160707.NaiveTrustManager");
            System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
            System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
            System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
			DisableSSLCertificateCheckUtil.disableChecks();
			logger.debug("Setting javax.xml.bind.JAXBContext <- com.sun.xml.internal.bind.v2.ContextFactory");
			System.setProperty("javax.xml.bind.JAXBContext","com.sun.xml.internal.bind.v2.ContextFactory"); 
		}catch(Exception e){
			logger.error("invokeWSFactura:", e);
			throw new CFDInvokingWSException("SSL Error:"+e.getMessage());
		}
		
        final DigiFact df=new DigiFact();
        final DigiFactSoap digiFactSoap12 = df.getDigiFactSoap12();
        final CFDIRequest cfdiRequest = new CFDIRequest();
        final DatosCFDI datosCFD = new DatosCFDI();        
        final ReceptorCFDI receptor = new ReceptorCFDI();
        final ConceptosCFDI conceptos = new ConceptosCFDI();
        
        cfdiRequest.setUsuario(usuario);
        cfdiRequest.setContrasena(contrasena);
        cfdiRequest.setDatosCFDI(datosCFD);
        cfdiRequest.setConceptosCFD(conceptos);
		//ArrayOfAnyType impuestos;
		//Impuesto impuestoWS;
		String xmlAddenda;
        
		//String serie = "PMS";

		datosCFD.setSerie(serie);
		datosCFD.setFormadePago(pedidoVenta.getFormaDePagoDescripcion().toUpperCase());
		datosCFD.setTipodeComprobante("F");
		
		String mpOrig = pedidoVenta.getMetodoDePagoDescripcion().toUpperCase();
		if(mpOrig.contains("|")){
			String mpOrigCveVal[]=mpOrig.split("\\|");
			String mpSHCP = mpOrigCveVal[0];
			logger.debug("->METODO DE PAGO SHCP:mpOrig="+mpOrig+", mpSHCP="+mpSHCP);
			datosCFD.setMetodoPago(mpSHCP);
		} else {
			datosCFD.setMetodoPago(mpOrig);
		}
		datosCFD.setEmailMensaje("FACTURA PEDIDO:" + pedidoVenta.getId());

		EntradaSalidaFooter esf = new EntradaSalidaFooter();
		esf.calculaParaFacturaTotalesDesde(pedidoVenta, esdList);

		datosCFD.setSubtotal(esf.getSubTotalNoGrabado());
		datosCFD.setDescuento(esf.getImporteDescuentoAplicado());
		datosCFD.setTotal(esf.getTotal());
		datosCFD.setLugarDeExpedicion("ESTADO DE MÉXICO");

		receptor.setRFC(pedidoVenta.getClienteRFC());
		receptor.setRazonSocial(pedidoVenta.getClienteRazonSocial());
        
        //final String residenciaFiscal = cliente.getCalle().toUpperCase()+" "+cliente.getNumExterior().toUpperCase()+" "+cliente.getNumInterior().toUpperCase()+", "+cliente.getColonia().toUpperCase()+", C.P. "+cliente.getCp().toUpperCase();
        final String residenciaFiscal = cliente.getDireccionFacturacion();

		receptor.setResidenciaFiscal(residenciaFiscal);
        receptor.setUsoCfdi("G01");
		receptor.setContacto1(cliente.getContacto() != null ? cliente.getContacto() : "ULISES LEÓN RESENDIZ");
		receptor.setContacto2("LUCIANO LEÓN SANCHEZ");
		receptor.setEmail(cliente.getEmail());
        
		receptor.setNoCliente(String.valueOf(pedidoVenta.getClienteId()));
		final String[] telefonos = cliente.getTelefonos().toUpperCase().split(":");
		receptor.setTelefono1(telefonos[0]);
		receptor.setTelefono2(telefonos.length > 1 ? telefonos[1] : "");		

		ArrayOfConceptoCFDI conceptosArr = new ArrayOfConceptoCFDI();
        conceptos.setConceptos(conceptosArr);
        
		for (EntradaSalidaDetalleQuickView esd : esdList) {
			ConceptoCFDI conceptoWS = new ConceptoCFDI();
                        
			conceptoWS.setCantidad(esd.getCantidad());
			conceptoWS.setUnidad(esd.getProductoUnidadEmpaque());
            conceptoWS.setNoIdentificacion(esd.getNoIdentificacion()); // TO-DO
            conceptoWS.setUnidad(esd.getUnidad()); // TO-DO
			String desc = null;
			desc  = esd.getProductoNombre() + "/" + esd.getProductoPresentacion() ;
			desc += "(" + esd.getProductoContenido() + " " + esd.getProductoUnidadMedida() + ")";
			conceptoWS.setDescripcion(desc );
			double precioPVD_CFD = esd.getPrecioVenta() / (1.0 + pedidoVenta.getFactorIva());
			double importePVD_CFD = precioPVD_CFD * esd.getCantidad();
			conceptoWS.setValorUnitario(precioPVD_CFD);
			conceptoWS.setImporte(importePVD_CFD);
            
			conceptosArr.getConceptoCFDI().add(conceptoWS);
		}
        ArrayOfImpuesto impuestos = new ArrayOfImpuesto();
		
		Impuesto impuestoWS = new Impuesto();
		impuestoWS.setTasa(pedidoVenta.getFactorIva() * 100.0);
		impuestoWS.setTipo("IVA");
		impuestoWS.setImporte(esf.getImporteIVA());
        
		impuestos.getImpuesto().add(impuestoWS);

        //impuestoWS = new Impuesto();
		//impuestoWS.setTasa(8);
		//impuestoWS.setTipoImpuesto("IVAR");
		//impuestoWS.setImporte(110.39);
		//impuestos.getAnyType().add(impuestoWS);
		xmlAddenda = "";
		//======================================================================

		try {
			logger.debug("-->> Invocacion a SICOFI: pedidoVentaId=" + pedidoVenta.getId());            
            final CFDIResponse generaCFDIV33 = digiFactSoap12.generaCFDIV33(cfdiRequest);
            
            String xml = generaCFDIV33.getXMLCFDI();
			//String xml = port.generaCFD(usuario, contrasena, datosCFD, receptor, conceptos, impuestos, xmlAddenda);
			logger.debug("-->>OK recibido el XML desde digifact: mide " + xml.length() + " bytes");

			String folioXML = Constants.extractXMLAtribute("folio", xml);
			String serieXML = Constants.extractXMLAtribute("serie", xml);
			String tipoDeComprobanteXML = Constants.extractXMLAtribute("tipoDeComprobante", xml);

			if (folioXML != null && serieXML != null && tipoDeComprobanteXML != null) {
				cfdVenta.setNumCfd(serieXML + folioXML);
				cfdVenta.setTipo(tipoDeComprobanteXML);
			}

			cfdVenta.setContenidoOriginalXml(xml.getBytes());
			cfdVenta.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
			logger.debug("-->>OK invocacion a DIGIFACT para pedidoVentaID=" + pedidoVenta.getId());
			cfdVenta.setCallingErrorResult(null);
		} catch (SOAPFaultException sex) {			
			logger.error("-->>SOAP Error en invocacion a DIGIFACT para pedidoVentaID=" + pedidoVenta.getId(), sex);
			logger.error("-->>WS param:    usuario:"+usuario);
			logger.error("-->>WS param: contrasena:"+contrasena);
			logger.error("-->>WS param:   datosCFD:"+ReflectionToStringBuilder.toString(datosCFD, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:   receptor:"+ReflectionToStringBuilder.toString(receptor, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:  conceptos:"+ReflectionToStringBuilder.toString(conceptos, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:  impuestos:"+ReflectionToStringBuilder.toString(impuestos, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param: xmlAddenda:"+ReflectionToStringBuilder.toString(xmlAddenda, ToStringStyle.MULTI_LINE_STYLE));
			
			javax.xml.soap.SOAPFault fault = sex.getFault(); //<Fault> node
			logger.error("-->>WS ERROR  fault:"+ReflectionToStringBuilder.toString(fault, ToStringStyle.MULTI_LINE_STYLE));
			javax.xml.soap.Detail detail = fault.getDetail(); // <detail> node
			logger.error("-->>WS ERROR  detail:"+ReflectionToStringBuilder.toString(detail, ToStringStyle.MULTI_LINE_STYLE));
			
			logger.error("-->>WS ERROR  detailEntries:");
			
			Iterator detailEntries = detail.getDetailEntries(); //nodes under <detail>
			//application / service-provider-specific XML nodes (type javax.xml.soap.DetailEntry) from here
			while(detailEntries.hasNext()){
				Object detailObject= detailEntries.next();
				logger.error("-->>WS ERROR  \tdetail:"+ReflectionToStringBuilder.toString(detailObject, ToStringStyle.MULTI_LINE_STYLE));
			}
			
			cfdVenta.setNumCfd(null);
			cfdVenta.setTipo(null);
			try{
				String localizedMessage = sex.getMessage().trim()+"|"+sex.getFault().toString().trim();
				if (localizedMessage.length() > 254) {
					cfdVenta.setCallingErrorResult(localizedMessage.substring(0, 254));
				} else {
					cfdVenta.setCallingErrorResult(localizedMessage);
				}
				
				cfdVenta.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
			}catch(Throwable t){
				cfdVenta.setCallingErrorResult("ERROR GRAVE AL FACTURAR, VER LOGS");
			}
			throw new CFDInvokingWSException("SSL Error:"+sex.getMessage());
		} catch (Exception ex) {
			logger.error("-->>Error en invocacion a DIGIFACT para pedidoVentaID=" + pedidoVenta.getId(), ex);
			logger.error("-->>WS param:    usuario:"+usuario);
			logger.error("-->>WS param: contrasena:"+contrasena);
			logger.error("-->>WS param:   datosCFD:"+ReflectionToStringBuilder.toString(datosCFD, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:   receptor:"+ReflectionToStringBuilder.toString(receptor, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:  conceptos:"+ReflectionToStringBuilder.toString(conceptos, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param:  impuestos:"+ReflectionToStringBuilder.toString(impuestos, ToStringStyle.MULTI_LINE_STYLE));
			logger.error("-->>WS param: xmlAddenda:"+ReflectionToStringBuilder.toString(xmlAddenda, ToStringStyle.MULTI_LINE_STYLE));
			
			cfdVenta.setNumCfd(null);
			cfdVenta.setTipo(null);
			try{
				String localizedMessage = ex.getMessage().trim();
				if (localizedMessage.length() > 254) {
					cfdVenta.setCallingErrorResult(localizedMessage.substring(0, 254));
				} else {
					cfdVenta.setCallingErrorResult(localizedMessage);
				}
				
				cfdVenta.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
			}catch(Throwable t){
				cfdVenta.setCallingErrorResult("ERROR GRAVE AL FACTURAR, VER LOGS");
			}
			throw new CFDInvokingWSException("SSL Error:"+ex.getMessage());
		}
		return cfdVenta;
		
	}	
}
