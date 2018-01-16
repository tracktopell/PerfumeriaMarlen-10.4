package com.pmarlen.digifactws2018.production.client;

import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.exception.CFDInvokingWSException;

import com.pmarlen.model.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.tempuri.ArrayOfConceptoCFDI;
import org.tempuri.ArrayOfImpuestoRetenido;
import org.tempuri.ArrayOfImpuestoTrasladado;
import org.tempuri.CFDIRequest;
import org.tempuri.CFDIResponse;
import org.tempuri.ConceptoCFDI;
import org.tempuri.ConceptosCFDI;
import org.tempuri.DatosCFDI;
import org.tempuri.DigiFact;
import org.tempuri.DigiFactSoap;
import org.tempuri.ImpuestoRetenido;
import org.tempuri.ImpuestoTrasladado;
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
            
            System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
            System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold"         , "999999");
            
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
        cfdiRequest.setReceptorCFDI(receptor);
		String xmlAddenda;
        
		datosCFD.setSerie(serie);
		datosCFD.setTipodeComprobante("FA");
        datosCFD.setMoneda("MXN");
		logger.debug("->PorcentajeDescuentoCalculado = "+pedidoVenta.getPorcentajeDescuentoCalculado()+"%");
        logger.debug("->PorcentajeDescuentoExtra     = "+pedidoVenta.getPorcentajeDescuentoExtra()    +"%");
		String metodoDePagoOrig = pedidoVenta.getMetodoDePagoDescripcion();
		if(metodoDePagoOrig.contains("|")){
			String mpOrigCveVal[]=metodoDePagoOrig.split("\\|");
			String mpSHCP = mpOrigCveVal[0];
			logger.debug("->METODO DE PAGO SHCP:metodoDePagoOrig="+metodoDePagoOrig+", mpSHCP="+mpSHCP);
			
            datosCFD.setMetodoPago(mpSHCP);            
		}
        String formaDePagoOrig = pedidoVenta.getFormaDePagoDescripcion();
		if(formaDePagoOrig.contains("|")){
			String fpOrigCveVal[]=formaDePagoOrig.split("\\|");
			String fpSHCP = fpOrigCveVal[0];
			logger.debug("->FORMA DE PAGO SHCP:formaDePagoOrig="+formaDePagoOrig+", fpSHCP="+fpSHCP);			
            datosCFD.setFormadePago(fpSHCP);            
		}
        
		datosCFD.setEmailMensaje("FACTURA PEDIDO:" + pedidoVenta.getId());

		EntradaSalidaFooter esf = new EntradaSalidaFooter();
		esf.calculaParaFacturaTotalesDesde(pedidoVenta, esdList);

		// C.P. Contante
		datosCFD.setLugarDeExpedicion("55740");
        
		receptor.setRFC(pedidoVenta.getClienteRFC());
		receptor.setRazonSocial(pedidoVenta.getClienteRazonSocial());
        
        //final String residenciaFiscal = cliente.getCalle().toUpperCase()+" "+cliente.getNumExterior().toUpperCase()+" "+cliente.getNumInterior().toUpperCase()+", "+cliente.getColonia().toUpperCase()+", C.P. "+cliente.getCp().toUpperCase();
        final String residenciaFiscal = cliente.getDireccionFacturacion();

		//receptor.setResidenciaFiscal(residenciaFiscal);        
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
        
        double subTotal    = 0.0;
        double subTotalIVA = 0.0;
        double total       = 0.0;
        double importeIVA  = 0.0;
		for (EntradaSalidaDetalleQuickView esd : esdList) {
            final ArrayOfImpuestoTrasladado impuestoTrasladadoArray= new  ArrayOfImpuestoTrasladado();
			ConceptoCFDI concepto = new ConceptoCFDI();
                        
			concepto.setCantidad(esd.getCantidad());
			
            concepto.setNoIdentificacion(esd.getProductoCodigoBarras()); // TO-DO
            concepto.setClaveProdServ(esd.getNoIdentificacion()); // TO-DO
            concepto.setClaveUnidad(esd.getUnidad()); // TO-DO
            concepto.setUnidad(esd.getProductoUnidadEmpaque());
			String desc = null;
			desc  = esd.getProductoNombre() + "/" + esd.getProductoPresentacion() ;
			desc += "(" + esd.getProductoContenido() + " " + esd.getProductoUnidadMedida() + ")";
			concepto.setDescripcion(desc );
			double precioPVD_CFD = esd.getPrecioVenta() / (1.0 + pedidoVenta.getFactorIva());
			double importePVD_CFD = precioPVD_CFD * esd.getCantidad();
            subTotal += importePVD_CFD;
			concepto.setValorUnitario(precioPVD_CFD);
			concepto.setImporte(importePVD_CFD);
                        
            ImpuestoTrasladado impuestoTrasladado = new ImpuestoTrasladado();
            
            impuestoTrasladado.setBase(importePVD_CFD);
            impuestoTrasladado.setImpuesto("002"); // IVA
            impuestoTrasladado.setTasaOCuota(Constants.IVA);
            impuestoTrasladado.setTipoFactor("Tasa");
            importeIVA = importePVD_CFD * pedidoVenta.getFactorIva();            
            impuestoTrasladado.setImporte(importeIVA);
            subTotalIVA += importeIVA;
            
            concepto.setTraslados(impuestoTrasladadoArray);
            impuestoTrasladadoArray.getImpuestoTrasladado().add(impuestoTrasladado);
            
			conceptosArr.getConceptoCFDI().add(concepto);
            logger.debug("\t--->> REAL:  CONCEPTO: VU="+esd.getCantidad()+" * "+precioPVD_CFD+" = "+importePVD_CFD+" , IVA="+importeIVA); 
		}
        
        total = subTotal + subTotalIVA;
        
        //datosCFD.setSubtotal (subTotal);
		//datosCFD.setTotal(total);
        
        datosCFD.setSubtotal (esf.getSubTotalNoGrabado());
        datosCFD.setTotal    (esf.getTotal());
        
        logger.debug("--->> REAL:*SUBTOTAL     = "+subTotal); 
        logger.debug("--->> REAL: SUBTOTAL IVA = "+subTotalIVA); 
        logger.debug("--->> REAL:*TOTAL        = "+total); 
        
		xmlAddenda = "";
		//======================================================================

		try {
			logger.debug("-------[PRUEBA 13 , la BUENAAAA? ]------->> Invocacion a SICOFI:digiFactSoap12.generaCFDIV33: pedidoVentaId=" + pedidoVenta.getId());            
            logger.debug("------->>>> digiFactSoap12.generaCFDIV33:");
            final CFDIResponse generaCFDIV33 = digiFactSoap12.generaCFDIV33(cfdiRequest);
            logger.debug("-------<<<< digiFactSoap12.generaCFDIV33:CFDICorrecto?"+generaCFDIV33.isCFDICorrecto());
            
            
            logger.debug("-->> CodigoError="+generaCFDIV33.getCodigoError()+", ErrorCFDI="+generaCFDIV33.getErrorCFDI());
            if(!generaCFDIV33.isCFDICorrecto()){
                throw new WebServiceException("CodigoError="+generaCFDIV33.getCodigoError()+", ErrorCFDI="+generaCFDIV33.getErrorCFDI());
            } else {
                String xml = generaCFDIV33.getXMLCFDI();			
                logger.debug("-->>OK recibido el XML desde digifact: mide " + xml.length() + " bytes");
                saveXML(xml);
                
                String folioXML = Constants.extractXMLAtribute("Folio", xml);
                String serieXML = Constants.extractXMLAtribute("Serie", xml);
                String tipoDeComprobanteXML = Constants.extractXMLAtribute("TipoDeComprobante", xml);

                logger.debug("-->>folioXML " + folioXML + ", serieXML="+serieXML+", tipoDeComprobanteXML="+tipoDeComprobanteXML);

                if (folioXML != null && serieXML != null && tipoDeComprobanteXML != null) {
                    cfdVenta.setNumCfd(serieXML + folioXML);
                    cfdVenta.setTipo(tipoDeComprobanteXML);
                } else{
                    logger.error("No es posible obtener los atributos: folio, serie, tipoDeComprobante; del XML");
                }
                cfdVenta.setContenidoOriginalXml(xml.getBytes());
                cfdVenta.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
                logger.debug("-->>OK invocacion a DIGIFACT para pedidoVentaID=" + pedidoVenta.getId());
            }
                        
			

			
			cfdVenta.setCallingErrorResult(null);
		} catch (WebServiceException sex) {			
			logger.error("-->>SOAP Error en invocacion a DIGIFACT para pedidoVentaID=" + pedidoVenta.getId(), sex);
			
			String fault = sex.getMessage();
			logger.error("-->>WS ERROR  message:"+ReflectionToStringBuilder.toString(fault, ToStringStyle.MULTI_LINE_STYLE));			
			
			cfdVenta.setNumCfd(null);
			cfdVenta.setTipo(null);
			try{
				String localizedMessage = sex.getMessage().trim();
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
    
    private static void saveXML(String xml){
        FileOutputStream fos = null;
        Date today=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        
        File xmlFileOutput=new File("./","DIGIFACT_WS_CALL_"+sdf.format(today)+"_CFDI.xml");
        try {
            fos = new FileOutputStream(xmlFileOutput);
            fos.write(xml.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            logger.fatal("...error at saveXML:", ex);
        }        
    }
}
