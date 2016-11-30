package com.pmarlen.businesslogic.reports;

import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.LogicaFinaciera;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.rest.dto.I;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class GeneradorImpresionPedidoVenta {
	private static Logger logger = Logger.getLogger("GeneradorImpresionPedidoVenta");
	private static String reportDesignDir="/reports/";
	private static String workingDir = "./";

	public static void setWorkingDir(String wd){
		workingDir =  wd;
	}
	
	public static byte[] generaPdfPedidoVenta(EntradaSalidaQuickView pedidoVenta,ArrayList<EntradaSalidaDetalleQuickView> esdListOriginal,Cliente clienteVenta,boolean fullPrint,boolean interna,String usuarioImr) {
		return generaPdfPedidoVenta(null,pedidoVenta, esdListOriginal, clienteVenta, fullPrint, interna, usuarioImr);
	}
    
	public static byte[] generaPdfTraspaso(Sucursal suc,Sucursal sucDestino,EntradaSalidaQuickView traspaso,ArrayList<EntradaSalidaDetalleQuickView> esdListOriginal,String usuarioImr,boolean fullPrint) {
		byte[] pdfBytes = null;
		ArrayList<EntradaSalidaDetalleQuickView> esdList=null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath = "";
			String compiledReportClassPath = "";
            
			logger.debug("Default Locale:"+Locale.getDefault());
            
			reportFileName = "traspaso";
			esdList=esdListOriginal;
				
			
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = workingDir + reportFileName + ".jasper";
			
            Collection<Map<String,?>> col = new ArrayList<Map<String,?>>();
            DecimalFormat    df    = new  DecimalFormat("$###,###,###,##0.00");
            DecimalFormat    dfEnt = new  DecimalFormat("###########0.00");
            logger.debug("Ok, jrxml loaded");
			logger.debug("Ok, jrxml loaded:"+traspaso.getAutorizaDescuento()+"?,"+traspaso.getPorcentajeDescuentoCalculado()+"%+"+traspaso.getPorcentajeDescuentoExtra()+"%");
            int n;
			EntradaSalidaFooter esf=new EntradaSalidaFooter();
			esf.calculaTotalesDesde(traspaso, esdList);
			for(EntradaSalidaDetalleQuickView pvd:esdList){
				Map<String,Object> vals = new HashMap<String,Object> ();
                
                n = pvd.getCantidad();
                
                vals.put("clave",pvd.getProductoCodigoBarras());
                vals.put("cantidad",n);
				vals.put("ta",Constants.getDescripcionTipoAlmacen(pvd.getApTipoAlmacen()).substring(0,3));
                vals.put("codigoBarras",pvd.getProductoCodigoBarras());                
                vals.put("descripcion",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion());
				vals.put("descripcionCont",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion()+" ("+pvd.getProductoContenido()+pvd.getProductoUnidadMedida()+")");
				vals.put("precio",df.format(pvd.getPrecioVenta()));
                vals.put("ppc"  ,pvd.getProductoUnidadesPorCaja());
				if(pvd.getApUbicacion() == null){
					vals.put("ubic"  ,"--N/D--");
				} else {
					vals.put("ubic"  ,pvd.getApUbicacion());
				}
				vals.put("ue"  ,pvd.getProductoUnidadEmpaque());
                vals.put("importe",df.format(n*pvd.getPrecioVenta()));
                vals.put("cont",pvd.getProductoContenido()+" "+pvd.getProductoUnidadMedida());
                
                col.add(vals);
			}
			
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.debug("Ok, JRDataSource created");
            
            Map parameters = new HashMap();
            SimpleDateFormat sdf_cdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            
            SimpleDateFormat sdf_f1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat sdf_h1 = new SimpleDateFormat("HH:mm");
            
            Date fechaReporte = new Date();
            
            parameters.put("printImages" ,fullPrint);
            
			parameters.put("usuario",traspaso.getUsuarioNombreCompleto());
			parameters.put("usuarioImpr",usuarioImr);
			parameters.put("pedidoVentaId",traspaso.getId());
			
			parameters.put("sucDestino","["+sucDestino.getClave()+"]"+sucDestino.getNombre());
			
			parameters.put("pedidoVentaEstado",traspaso.getEstadoDescripcion());
			
			//parameters.put("tipoAlmacen", "TA");
			
			parameters.put("fechaHoraImpr",sdf_cdf.format(new Date()));
			String dirSuc = null;
			if(suc != null){
				dirSuc = suc.getDireccion().toUpperCase();
				parameters.put("dirSuc",dirSuc);
				parameters.put("telsSuc","TEL. "+suc.getTelefonos().toUpperCase());
			} else {
				dirSuc = "CALLE FRANCISCO VILLA LT3 MZ 98 NO. 121, Col. SAN MARTÍN AZCATEPEC, TÉCAMAC. EDO. DE MÉX. C.P. 55740";
				parameters.put("dirSuc",dirSuc);				
				parameters.put("telsSuc","TEL. (55)5936-7894");
			}
			
			
            String direccionValue = null;
			
            if(traspaso.getComentarios()!=null && traspaso.getComentarios().trim().length()>1){
				parameters.put("comentarios" ,traspaso.getComentarios());
			} else{
				parameters.put("comentarios" ,null);
			}
			parameters.put("total" ,"");  
            parameters.put("total" ,df.format(esf.getTotal()));  
            JasperReport jasperReport = null;
            
			try {
				File compiledReportPathFile=new File(compiledReportPath);
				if(! compiledReportPathFile.exists()){
					InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
					JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
					JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
					logger.debug("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);				
				} else {
					logger.debug("Ok,JasperReport loadfrom:"+compiledReportPath);
				}
				jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
				logger.debug("Ok,JasperReport compiled:"+jasperReport.getName());
			}catch(Exception e){
				logger.error("JasperReport:"+e.getMessage());
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				logger.debug("Ok,Emergency JasperReport runtime compiled from:"+reportPath+", name:"+jasperReport.getName());
			}
			
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.debug("Ok, JasperPrint created.");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
    
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setMetadataAuthor("PerfumeriaMarlen_SAA_10.3");						
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
			logger.debug("Ok, JasperExportManager executed");
			pdfBytes = baos.toByteArray();            
            logger.debug("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		return pdfBytes;
    }
	
	
	public static byte[] generaPdfPedidoVenta(Sucursal suc,EntradaSalidaQuickView pedidoVenta,ArrayList<EntradaSalidaDetalleQuickView> esdListOriginal,Cliente clienteVenta,boolean fullPrint,boolean interna,String usuarioImr) {
		byte[] pdfBytes = null;
		ArrayList<EntradaSalidaDetalleQuickView> esdList=null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath = "";
			String compiledReportClassPath = "";
            
			logger.debug("Default Locale:"+Locale.getDefault());
            
			if(interna){
				reportFileName = "pedidoVentaDesignInterno2";
				esdList = new ArrayList<EntradaSalidaDetalleQuickView>(esdListOriginal);
				Collections.sort(esdList);
			} else{
				esdList=esdListOriginal;
				reportFileName = "pedidoVentaDesignCliente";
			}
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = workingDir + reportFileName + ".jasper";
			
            Collection<Map<String,?>> col = new ArrayList<Map<String,?>>();
            DecimalFormat    df    = new  DecimalFormat("$###,###,###,##0.00");
            DecimalFormat    dfEnt = new  DecimalFormat("###########0.00");
            logger.debug("Ok, jrxml loaded");
			logger.debug("Ok, jrxml loaded:"+pedidoVenta.getAutorizaDescuento()+"?,"+pedidoVenta.getPorcentajeDescuentoCalculado()+"%+"+pedidoVenta.getPorcentajeDescuentoExtra()+"%");
            int n;
			EntradaSalidaFooter esf=new EntradaSalidaFooter();
			esf.calculaTotalesDesde(pedidoVenta, esdList);
			for(EntradaSalidaDetalleQuickView pvd:esdList){
				Map<String,Object> vals = new HashMap<String,Object> ();
                
                n = pvd.getCantidad();
                
                vals.put("clave",pvd.getProductoCodigoBarras());
                vals.put("cantidad",n);
				vals.put("ta",Constants.getDescripcionTipoAlmacen(pvd.getApTipoAlmacen()).substring(0,3));
                vals.put("codigoBarras",pvd.getProductoCodigoBarras());                
                vals.put("descripcion",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion());
				vals.put("descripcionCont",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion()+" ("+pvd.getProductoContenido()+pvd.getProductoUnidadMedida()+")");
				vals.put("precio",df.format(pvd.getPrecioVenta()));
                vals.put("ppc"  ,pvd.getProductoUnidadesPorCaja());
				if(pvd.getApUbicacion() == null){
					vals.put("ubic"  ,"--N/D--");
				} else {
					vals.put("ubic"  ,pvd.getApUbicacion());
				}
				vals.put("ue"  ,pvd.getProductoUnidadEmpaque());
                vals.put("importe",df.format(n*pvd.getPrecioVenta()));
                vals.put("cont",pvd.getProductoContenido()+" "+pvd.getProductoUnidadMedida());
                
                col.add(vals);
			}
			
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.debug("Ok, JRDataSource created");
            
            Map parameters = new HashMap();
            SimpleDateFormat sdf_cdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            
            SimpleDateFormat sdf_f1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat sdf_h1 = new SimpleDateFormat("HH:mm");
            
            Date fechaReporte = new Date();
            
            parameters.put("printImages" ,fullPrint);
            String lugarExpedicion = "EDO. MÉX.";
			parameters.put("usuario",pedidoVenta.getUsuarioNombreCompleto());
			parameters.put("usuarioImpr",usuarioImr);
			parameters.put("pedidoVentaId",pedidoVenta.getId());
			if(pedidoVenta.getTipoMov() == Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){
				parameters.put("mov.titulo","NOTA DE PEDIDO");
				parameters.put("mov.subtitulo","NO. PEDIDO");
			} else if(pedidoVenta.getTipoMov() == Constants.TIPO_MOV_SALIDA_TRASPASO){
				parameters.put("mov.titulo","TRASPASO");
				parameters.put("mov.subtitulo","NO. TRASPASO");
			}
			parameters.put("pedidoVentaEstado",pedidoVenta.getEstadoDescripcion());
			
			//parameters.put("tipoAlmacen", "TA");
			
			parameters.put("fechaHoraImpr",sdf_cdf.format(new Date()));
			String dirSuc = null;
			if(suc != null){
				dirSuc = suc.getDireccion().toUpperCase();
				parameters.put("dirSuc",dirSuc);
				parameters.put("telsSuc","TEL. "+suc.getTelefonos().toUpperCase());
			} else {
				dirSuc = "CALLE FRANCISCO VILLA LT3 MZ 98 NO. 121, Col. SAN MARTÍN AZCATEPEC, TÉCAMAC. EDO. DE MÉX. C.P. 55740";
				parameters.put("dirSuc",dirSuc);				
				parameters.put("telsSuc","TEL. (55)5936-7894");
			}
			
			
            parameters.put("cliente",clienteVenta.getRazonSocial());
			parameters.put("nombreEstab",clienteVenta.getNombreEstablecimiento());
            parameters.put("rfc",clienteVenta.getRfc());
			String direccionValue = null;
			if(clienteVenta.getId() == Constants.ID_CLIENTE_MOSTRADOR){
				direccionValue = dirSuc;
			} else {
				direccionValue = clienteVenta.getDireccionFacturacion();
				if(direccionValue == null){
					direccionValue = clienteVenta.getCalle()+
							", Num. Ext. "+(clienteVenta.getNumExterior()!=null&&clienteVenta.getNumExterior().length()>0?clienteVenta.getNumExterior():"S/N")+
							", Num. Int. "+(clienteVenta.getNumInterior()!=null&&clienteVenta.getNumInterior().length()>0?clienteVenta.getNumInterior():"S/N")+					
							", "+clienteVenta.getColonia()+
						", "+clienteVenta.getMunicipio()+
							", "+clienteVenta.getEstado()+
							", C.P. "+clienteVenta.getCp();
				}
			}
            parameters.put("direccion" , direccionValue.toUpperCase());
			
            if(pedidoVenta.getComentarios()!=null && pedidoVenta.getComentarios().trim().length()>1){
				parameters.put("comentarios" ,pedidoVenta.getComentarios());
			} else{
				parameters.put("comentarios" ,null);
			}
			if(pedidoVenta.getCondicionesDePago()!=null && pedidoVenta.getCondicionesDePago().trim().length()>1){
				parameters.put("condiciones" ,pedidoVenta.getCondicionesDePago());
			} else{
				parameters.put("condiciones" ,"--NO IDENTIFICADO--");
			}
			if(pedidoVenta.getNumDeCuenta()!=null && pedidoVenta.getNumDeCuenta().trim().length()>1){
				parameters.put("noCuenta" ,pedidoVenta.getNumDeCuenta());
			} else{
				parameters.put("noCuenta" ,"--NO IDENTIFICADO--");
			}
			parameters.put("formaDePago" ,pedidoVenta.getFormaDePagoDescripcion().toUpperCase());
            parameters.put("metodoDePago",pedidoVenta.getMetodoDePagoDescripcion().toUpperCase());
            logger.debug("->descuento:autorizadescuento?"+pedidoVenta.getAutorizaDescuento()+", "+pedidoVenta.getPorcentajeDescuentoCalculado()+"% + "+pedidoVenta.getPorcentajeDescuentoExtra());
            parameters.put("subtotal" , df.format(esf.getSubTotalBruto()));
            parameters.put("iva" ,df.format(esf.getImporteIVA()));
            parameters.put("descuento" ,df.format(esf.getImporteDescuentoAplicado()));
            
            parameters.put("total" ,df.format(esf.getTotal()));  
            JasperReport jasperReport = null;
            //String intDecParts[] = dfEnt.format(esf.getTotal()).split("\\.");
			try {
				File compiledReportPathFile=new File(compiledReportPath);
				if(! compiledReportPathFile.exists()){
					InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
					JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
					JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
					logger.debug("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);				
				} else {
					logger.debug("Ok,JasperReport loadfrom:"+compiledReportPath);
				}
				jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
				logger.debug("Ok,JasperReport compiled:"+jasperReport.getName());
			}catch(Exception e){
				logger.error("JasperReport:"+e.getMessage());
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				logger.debug("Ok,Emergency JasperReport runtime compiled from:"+reportPath+", name:"+jasperReport.getName());
			}
			
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.debug("Ok, JasperPrint created.");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
    
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setMetadataAuthor("PerfumeriaMarlen_SAA_10.3");						
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
			logger.debug("Ok, JasperExportManager executed");
			pdfBytes = baos.toByteArray();            
            logger.debug("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		return pdfBytes;
    }
	
	public static byte[] generaPdfTicketPedidoVenta(EntradaSalidaQuickView pedidoVenta,ArrayList<EntradaSalidaDetalleQuickView> esdList,Cliente clienteVenta,boolean fullPrint,boolean interna,String usuarioImr) {
		byte[] pdfBytes = null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath = "";
			String compiledReportClassPath = "";
            
			logger.debug("Default Locale:"+Locale.getDefault());
            
			reportFileName = "pedidoVentaTicketDesign";
			
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = workingDir + reportFileName + ".jasper";
			
            
            
            Collection<Map<String,?>> col = new ArrayList<Map<String,?>>();
            DecimalFormat    df    = new  DecimalFormat("$###,###,###,##0.00");
            DecimalFormat    dfEnt = new  DecimalFormat("###########0.00");
            logger.debug("Ok, jrxml loaded");
			logger.debug("Ok, jrxml loaded:"+pedidoVenta.getAutorizaDescuento()+"?,"+pedidoVenta.getPorcentajeDescuentoCalculado()+"%+"+pedidoVenta.getPorcentajeDescuentoExtra()+"%");
            int n;
			EntradaSalidaFooter esf=new EntradaSalidaFooter();
			esf.calculaTotalesDesde(pedidoVenta, esdList);
			for(EntradaSalidaDetalleQuickView pvd:esdList){
				Map<String,Object> vals = new HashMap<String,Object> ();
                
                n = pvd.getCantidad();
                
                vals.put("clave",pvd.getProductoCodigoBarras());
                vals.put("cantidad",n);
				vals.put("ta",Constants.getDescripcionTipoAlmacen(pvd.getApTipoAlmacen()).substring(0,3));
                vals.put("codigoBarras",pvd.getProductoCodigoBarras());                
                vals.put("descripcion",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion());
                vals.put("descripcionCont",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion()+"("+pvd.getProductoContenido()+pvd.getProductoUnidadMedida()+")");
				vals.put("precio",df.format(pvd.getPrecioVenta()));
                vals.put("ppc"  ,pvd.getProductoUnidadesPorCaja());
				if(pvd.getApUbicacion() == null){
					vals.put("ubic"  ,"--N/D--");
				} else {
					vals.put("ubic"  ,pvd.getApUbicacion());
				}
				vals.put("ue"  ,pvd.getProductoUnidadEmpaque());
                vals.put("importe",df.format(n*pvd.getPrecioVenta()));
                vals.put("cont",pvd.getProductoContenido()+" "+pvd.getProductoUnidadMedida());
                
                col.add(vals);
			}
			
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.debug("Ok, JRDataSource created");
            
            Map parameters = new HashMap();
            SimpleDateFormat sdf_cdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            
            SimpleDateFormat sdf_f1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat sdf_h1 = new SimpleDateFormat("HH:mm");
            
            Date fechaReporte = new Date();
            
            parameters.put("printImages" ,fullPrint);
            String lugarExpedicion = "EDO. MÉX.";
			parameters.put("usuario",pedidoVenta.getUsuarioNombreCompleto());
			parameters.put("ticket","00000000009");
			parameters.put("usuarioImpr",usuarioImr);
			parameters.put("pedidoVentaId",pedidoVenta.getId());
			parameters.put("pedidoVentaEstado",pedidoVenta.getEstadoDescripcion());
			parameters.put("caja","?1");
			parameters.put("usuario",pedidoVenta.getUsuarioNombreCompleto());
			
			parameters.put("pedidoVentaEstado",pedidoVenta.getEstadoDescripcion());
			
			//parameters.put("tipoAlmacen", "TA");
			
			parameters.put("fechaHoraImpr",sdf_cdf.format(new Date()));
						
            parameters.put("cliente",clienteVenta.getRazonSocial());
            parameters.put("rfc",clienteVenta.getRfc());
			String direccionValue = null;
			direccionValue = clienteVenta.getDireccionFacturacion();
			if(direccionValue == null){
				direccionValue = clienteVenta.getCalle()+
						", Num. Ext. "+(clienteVenta.getNumExterior()!=null&&clienteVenta.getNumExterior().length()>0?clienteVenta.getNumExterior():"S/N")+
						", Num. Int. "+(clienteVenta.getNumInterior()!=null&&clienteVenta.getNumInterior().length()>0?clienteVenta.getNumInterior():"S/N")+					
						", "+clienteVenta.getColonia()+
					", "+clienteVenta.getMunicipio()+
						", "+clienteVenta.getEstado()+
						", C.P. "+clienteVenta.getCp();
			}
            parameters.put("direccion" , direccionValue.toUpperCase());
			
            if(pedidoVenta.getComentarios()!=null && pedidoVenta.getComentarios().trim().length()>1){
				parameters.put("comentarios" ,pedidoVenta.getComentarios());
			} else{
				parameters.put("comentarios" ,null);
			}
			if(pedidoVenta.getCondicionesDePago()!=null && pedidoVenta.getCondicionesDePago().trim().length()>1){
				parameters.put("condiciones" ,pedidoVenta.getCondicionesDePago());
			} else{
				parameters.put("condiciones" ,"--NO IDENTIFICADO--");
			}
			if(pedidoVenta.getNumDeCuenta()!=null && pedidoVenta.getNumDeCuenta().trim().length()>1){
				parameters.put("noCuenta" ,pedidoVenta.getNumDeCuenta());
			} else{
				parameters.put("noCuenta" ,"--NO IDENTIFICADO--");
			}
			parameters.put("formaDePago" ,pedidoVenta.getFormaDePagoDescripcion().toUpperCase());
            parameters.put("metodoDePago",pedidoVenta.getMetodoDePagoDescripcion().toUpperCase());
            logger.debug("->descuento:autorizadescuento?"+pedidoVenta.getAutorizaDescuento()+", "+pedidoVenta.getPorcentajeDescuentoCalculado()+"% + "+pedidoVenta.getPorcentajeDescuentoExtra());
            parameters.put("subtotal" , df.format(esf.getSubTotalBruto()));
            parameters.put("iva" ,df.format(esf.getImporteIVA()));
            parameters.put("descuento" ,df.format(esf.getImporteDescuentoAplicado()));
            parameters.put("recibimos" ,"?2");
			parameters.put("cambio" ,"?3");
			parameters.put("aprobacion" ,"?4");
			parameters.put("LeyendaFotter" ,"?5");
			
			
            parameters.put("total" ,df.format(esf.getTotal()));  
            JasperReport jasperReport = null;
            //String intDecParts[] = dfEnt.format(esf.getTotal()).split("\\.");
			File compiledReportPathFile=new File(compiledReportPath);
			/*
            if(! compiledReportPathFile.exists()){
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
				logger.debug("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);				
			}
			jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
			*/
			InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);			
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			logger.debug("Ok, JasperReport loaded from:"+compiledReportPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.debug("Ok, JasperPrint created.");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
    
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setMetadataAuthor("PerfumeriaMarlen_SAA_10.3");						
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
			logger.debug("Ok, JasperExportManager executed");
			pdfBytes = baos.toByteArray();            
            logger.debug("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		return pdfBytes;
    }


    public static byte[] generaPdfPfacturaPedidoVenta(EntradaSalidaQuickView pedidoVenta,Cfd cfdFactura,ArrayList<EntradaSalidaDetalleQuickView> esdList,Cliente clienteVenta,boolean fullPrint,String usuarioImr) {
		byte[] pdfBytes = null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath = "";
			String compiledReportClassPath = "";
            
			logger.debug("Default Locale:"+Locale.getDefault());
            
			reportFileName = "facturaDesignNueva";
			
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = workingDir + reportFileName + ".jasper";

            Collection<Map<String,?>> col = new ArrayList<Map<String,?>>();
            DecimalFormat    df    = new  DecimalFormat("$###,###,###,##0.00");
            DecimalFormat    dfEnt = new  DecimalFormat("###########0.00");
            DecimalFormat    dfBC  = new  DecimalFormat("0000000000000");
            logger.debug("Ok, jrxml loaded:"+pedidoVenta.getAutorizaDescuento()+"?,"+pedidoVenta.getPorcentajeDescuentoCalculado()+"%+"+pedidoVenta.getPorcentajeDescuentoExtra()+"%");
            int n;
            EntradaSalidaFooter esf=new EntradaSalidaFooter();
			esf.calculaParaFacturaTotalesDesde(pedidoVenta, esdList);
			
            InputStream isXMLCFD = new ByteArrayInputStream(cfdFactura.getContenidoOriginalXml());
			HashMap cfdMap = ParseCFDXML.parseCFDXML(isXMLCFD);
			byte[] qrImage = QRImageGenerator.getQRImage(cfdMap.get("QR").toString());
			ByteArrayInputStream baosImageQR = new ByteArrayInputStream(qrImage);
			
			Image imageQR = ImageIO.read(baosImageQR);			
			double precioNoGrabado=0.0;
			for(EntradaSalidaDetalleQuickView pvd:esdList){
				Map<String,Object> vals = new HashMap<String,Object> ();
                
                n = pvd.getCantidad();
                
                vals.put("clave",pvd.getProductoCodigoBarras());
                vals.put("cantidad",n);
				vals.put("ue",pvd.getProductoUnidadEmpaque());
				vals.put("ta",Constants.getDescripcionTipoAlmacen(pvd.getApTipoAlmacen()).substring(0,3));
                vals.put("codigoBarras",pvd.getProductoCodigoBarras());                
                vals.put("descripcion",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion());
				vals.put("descripcionCont",pvd.getProductoNombre()+"/"+pvd.getProductoPresentacion()+" ("+pvd.getProductoContenido()+pvd.getProductoUnidadMedida()+")");
				precioNoGrabado=pvd.getPrecioVenta() / (1.0+LogicaFinaciera.getImpuestoIVA());
				vals.put("precio",df.format(pvd.getPrecioVenta() / (1.0+LogicaFinaciera.getImpuestoIVA())));
			    vals.put("precioNoGrabado",df.format(precioNoGrabado));
				
				vals.put("precioIVA",df.format(pvd.getPrecioVenta() * LogicaFinaciera.getImpuestoIVA()));
                vals.put("ppc"  ,pvd.getProductoUnidadesPorCaja());
				vals.put("ubic"  ,pvd.getApUbicacion());
				
                vals.put("importe",df.format(n*precioNoGrabado));
                vals.put("cont",pvd.getProductoContenido()+" "+pvd.getProductoUnidadMedida());
                vals.put("isEmptyRow",false);
                col.add(vals);
			}
						
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.debug("Ok, JRDataSource created");
            
            Map parameters = new HashMap();
            SimpleDateFormat sdf_cdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            
            SimpleDateFormat sdf_f1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat sdf_h1 = new SimpleDateFormat("HH:mm");
            
            Date fechaReporte = new Date();
            
            parameters.put("printImages" ,fullPrint);            
			parameters.put("usuario",pedidoVenta.getUsuarioNombreCompleto());
			parameters.put("usuarioImpr",usuarioImr);
			parameters.put("pedidoVentaId",pedidoVenta.getId());
			parameters.put("pedidoVentaEstado",pedidoVenta.getEstadoDescripcion());
			parameters.put("imageQR"    ,imageQR);			
			parameters.put("pedidoVentaId" ,String.valueOf(pedidoVenta.getId()));            
            parameters.put("noFactura" ,""+pedidoVenta.getCdfNumCFD());
            parameters.put("printImages" ,fullPrint);
            parameters.put("folioFiscal" ,cfdMap.get("UUID"));
            parameters.put("fechaYHoraCert" ,cfdMap.get("FechaTimbrado").toString().replace("T", " "));
			String lugarExpedicion = "TECAMAC, ESTADO DE MÉXICO";
			parameters.put("lugarExp" , lugarExpedicion);
            parameters.put("fechaYHoraExp" ,cfdMap.get("fecha").toString().replace("T", " "));
            parameters.put("noSerCertSAT" ,cfdMap.get("noCertificadoSAT"));
            
            parameters.put("cliente",clienteVenta.getRazonSocial());
            parameters.put("rfc",clienteVenta.getRfc());
			
			parameters.put("fechaHoraImpr",sdf_cdf.format(new Date()));
						
			String direccionValue = null;
			direccionValue = clienteVenta.getDireccionFacturacion();
			if(direccionValue == null){
				direccionValue = clienteVenta.getCalle()+
						", Num. Ext. "+(clienteVenta.getNumExterior()!=null&&clienteVenta.getNumExterior().length()>0?clienteVenta.getNumExterior():"S/N")+
						", Num. Int. "+(clienteVenta.getNumInterior()!=null&&clienteVenta.getNumInterior().length()>0?clienteVenta.getNumInterior():"S/N")+					
						", "+clienteVenta.getColonia()+
					", "+clienteVenta.getMunicipio()+
						", "+clienteVenta.getEstado()+
						", C.P. "+clienteVenta.getCp();
			}
            parameters.put("direccion" , direccionValue.toUpperCase());
			
            if(pedidoVenta.getComentarios()!=null && pedidoVenta.getComentarios().trim().length()>1){
				parameters.put("comentarios" ,pedidoVenta.getComentarios());
			} else{
				parameters.put("comentarios" ,null);
			}
			if(pedidoVenta.getCondicionesDePago()!=null && pedidoVenta.getCondicionesDePago().trim().length()>1){
				parameters.put("condiciones" ,pedidoVenta.getCondicionesDePago());
			} else{
				parameters.put("condiciones" ,"--NO IDENTIFICADO--");
			}
			if(pedidoVenta.getNumDeCuenta()!=null && pedidoVenta.getNumDeCuenta().trim().length()>1){
				parameters.put("noCuenta" ,pedidoVenta.getNumDeCuenta());
			} else{
				parameters.put("noCuenta" ,"--NO IDENTIFICADO--");
			}
			
			parameters.put("formaDePago" ,pedidoVenta.getFormaDePagoDescripcion().toUpperCase());
            parameters.put("metodoDePago",pedidoVenta.getMetodoDePagoDescripcion().toUpperCase());
            parameters.put("cadenaOriginalSAT"  ,cfdMap.get("cadenaOriginal"));            
            parameters.put("selloDigitalEmisor" ,cfdMap.get("sello"));
            parameters.put("selloDigitalSAT"    ,cfdMap.get("selloSAT"));			
            parameters.put("subtotal" , df.format(esf.getSubTotalNoGrabado()));
			if(esf.getDescuentoAplicado()>0){
				parameters.put("descuento" ,df.format(esf.getImporteDescuentoAplicado()));
			}else{
				parameters.put("descuento" ,null);
			}
			parameters.put("iva" ,df.format(esf.getImporteIVA()));
            
            parameters.put("total" ,df.format(esf.getTotal()));  
            
            String intDecParts[] = dfEnt.format(esf.getTotal()).split("\\.");
            
            String letrasParteEntera  = NumeroCastellano.numeroACastellano(Long.parseLong(intDecParts[0])).trim();
            String letrasParteDecimal = NumeroCastellano.numeroACastellano(Long.parseLong(intDecParts[1])).trim();
            parameters.put("importeLetra" ,"--("+(letrasParteEntera+" Pesos "+intDecParts[1]+"/100 M.N.").toUpperCase()+")--");
			
            JasperReport jasperReport = null;
            //String intDecParts[] = dfEnt.format(esf.getTotal()).split("\\.");
			File compiledReportPathFile=new File(compiledReportPath);
			/*
            if(! compiledReportPathFile.exists()){
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
				logger.debug("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);				
			}
			
			jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
			*/
			InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);

			logger.debug("Ok, JasperReport loaded from:"+compiledReportPath);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.debug("Ok, JasperPrint created");
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "jasper_out_"+sdf.format(new Date())+".pdf");
            //logger.debug("Ok, JasperExportManager executed");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
			logger.debug("Ok, JasperExportManager executed");
			pdfBytes = baos.toByteArray();
            //JasperPrintManager.printReport(jasperPrint, false);
            //logger.debug("Ok, printed. executed");                        
            
            logger.debug("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		return pdfBytes;
    }
	
	public static JarpeReportsInfoDTO generaJarpeReportsInfoDTOTextTicket(EntradaSalidaQuickView es,List<EntradaSalidaDetalleQuickView> esdList,Sucursal suc,Usuario usuarioLogedin,boolean logoTexto){
		HashMap<String, Object> parameters = new HashMap<String, Object> (); 
		Collection<Map<String,?>> records  = new ArrayList<Map<String, ?>>();
		
		final String sucNom = suc.getNombre();
		final String sucDir = suc.getDireccion();
		parameters.put("sucursal.nombre"   , sucNom);
		
		final String nombre1 = sucNom.substring(0, sucNom.indexOf("S.A. DE C.V.")+13);
		final String nombre2 = sucNom.substring(sucNom.indexOf("S.A. DE C.V.")+13);
		
		parameters.put("sucursal.nombre1"  , nombre1);
		parameters.put("sucursal.nombre2"  , nombre2);
		
		List<String> direccionList = TextReporter.justifyText(sucDir, TextReporter.columns);		
		parameters.put("sucursal.direccion", sucDir);
		
		for(int i=1;i<=direccionList.size();i++){
			parameters.put("sucursal.direccion"+i, direccionList.get(i-1));
		}
		
		String[] txtLogo={  "          ##########          " ,
							"       ###          ###       " ,
							"    ###                ##     " ,
							"   ##                    ##   " ,
							"  ##  PPPPPPP           ...#  " ,
							" ##  P  PP  PPP        ·   .# " ,
							"##   P  PP   PP       ·    · #" ,
							"#     P PP  PPP  MMM   MMMM. #" ,
							"#       PPPPP     MMM  MMM   #" ,
							"#       PP        MMM MMMM   #" ,
							"#       PP        MM M  MM   #" ,
							"##   . PPPP       MM    MM   #" ,
							" #  ·.    PP      MM    MM  ##" ,
							" ## ···          MMMMM  MMM## " ,
							"  ## ····                 ##  " ,
							"   ##  ··················##   " ,
							"     ### ·DISTRUBUCIONES#     " ,
							"        ###         ###       " ,
							"           #########          "};
		
		String[] txtLogo2={ "           #########          " ,
							"      ###             ###     " ,
							"   ##                    ##   " ,
							"  ##   PPPPPP           ...#  " ,
							" ##  P  PP  PPP        ·   .# " ,
							"##   P  PP   PP       ·    · #" ,
							"#     P PP  PPP  MMM   MMMM. #" ,
							"#       PPPPP     MMM  MMM   #" ,
							"#       PP        MM M  MM   #" ,
							"#    . PPPP       MM    MM   #" ,
							" #  ·.    PP      MM    MM  ##" ,
							" ## ···          MMMMM  MMM## " ,
							"   ##  ··················##   " ,
							"     ### ·DISTRUBUCIONES#     " ,
							"           #########          "};
		if(logoTexto){
			for (int i = 1; i <= txtLogo.length; i++) {
				parameters.put("logo.line" + i, txtLogo[i - 1]);
			}
		} else {
			parameters.put("logo.line1" , "   ");
		}

		//parameters.put("sucursal.tels", "TELS.: ????-????");		
		
		parameters.put("L.venta.ticket"    , "NO. TICKET:");
		parameters.put("venta.ticket"      , es.getNumeroTicket());
		
		String titulo = "";
		if(es.getTipoMov() == Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){
			titulo = "VENTA DE MOSTRADOR";
		} else if(es.getTipoMov() == Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION){
			titulo = "DEVOLUCIÓN DE ART(S). VENTA";
		} else {
			titulo = "MOV."+es.getTipoMov();
		}

		parameters.put("fot.titulo"       , titulo);		
		parameters.put("venta.ticketFacil",ticketConGuiones(es.getNumeroTicket(),'-'));
		
		parameters.put("L.fecha.creado", "FECHA V.:");
		parameters.put("fecha.creado", Constants.sdfShortDateTime.format(es.getFechaCreo()));
		
		parameters.put("L.fecha.actual", "FECHA:");
		final Date today = new Date();
		parameters.put("fecha.actual", Constants.sdfShortDate.format(today));
		parameters.put("hora.actual", Constants.sdfShortTime.format(today));
		
		parameters.put("usuario.creo.email", es.getUsuarioEmailCreo());
		parameters.put("L.usuario.creo.nombre", "ATENDIO:");
		parameters.put("usuario.creo.nombre", es.getUsuarioNombreCompleto());
		parameters.put("usuario.creo.clave", String.valueOf(es.getUsuarioClave()));
		parameters.put("usuario.imprimio.email" , usuarioLogedin.getEmail());
		parameters.put("usuario.imprimio.nombre", usuarioLogedin.getNombreCompleto());
		parameters.put("usuario.imprimio.clave" , usuarioLogedin.getClave());

		parameters.put("sucursal.caja.creo", es.getCaja());
		
		parameters.put("cliente.rfc"  , es.getClienteRFC());
		parameters.put("cliente.estab", es.getClienteNombreEstablecimiento());
		parameters.put("cliente.racSoc", es.getClienteRazonSocial());
		List<String> racSocList = TextReporter.justifyText(es.getClienteRazonSocial(), TextReporter.columns - 11);
		for (int i = 1; i <= racSocList.size(); i++) {
			parameters.put("cliente.racSoc" + i, racSocList.get(i - 1));
		}
		parameters.put("cliente.cirFac", es.getClienteRazonSocial());
		
		parameters.put("formaDePago.desc" ,es.getFormaDePagoDescripcion());
		parameters.put("metodoDePago.desc",es.getMetodoDePagoDescripcion());
				
		for (EntradaSalidaDetalleQuickView esd: esdList) {
			HashMap<String, String> r=new HashMap<String, String>();
			double imp = esd.getCantidad() * esd.getPrecioVenta();
			r.put("pvd.producto.cb"    , esd.getProductoCodigoBarras());
			r.put("pvd.producto.nombre", esd.getProductoNombre());
			r.put("pvd.producto.present", esd.getProductoPresentacion());
			r.put("pvd.cant"			, String.valueOf(esd.getCantidad()));
			r.put("pvd.precio"			, String.valueOf(esd.getPrecioVenta()));
			r.put("pvd.imp"				, Constants.df2Decimal.format(imp));
			
			records.add(r);

		}
		final String totalXval = Constants.df2Decimal.format(es.getTotal());
		
		parameters.put("fot.tot"			, totalXval);
		
		double valorIVA = ( es.getTotal() / Constants.MAS_IVA ) * Constants.IVA;
		
						
		parameters.put("fot.neDesc"			, String.valueOf(0.0));
		parameters.put("fot.neSinDesc"		, String.valueOf(0.0));
		parameters.put("fot.neTotElem"		, String.valueOf(es.getTotProds()));
		parameters.put("fot.neTotElemL"		, " <-- # PRODS.");
		if(es.getTipoMov() == Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){
			parameters.put("fot.subTot"			, Constants.df2Decimal.format(es.getSubTotal1ra() + es.getSubTotalOpo() + es.getSubTotalReg()));
			parameters.put("fot.desc"			, Constants.df2Decimal.format(es.getImporteDescuento()));
			parameters.put("fot.metodoPago"		, es.getMetodoDePagoDescripcion());
			if(es.getMetodoDePagoId() == Constants.ID_MDP_EFECTIVO){
				parameters.put("fot.impRecib"		, Constants.df2Decimal.format(es.getImporteRecibido()));		
				parameters.put("fot.cambio"		    , Constants.df2Decimal.format(es.getImporteRecibido()-es.getTotal()));		
			} else if(es.getMetodoDePagoId() == Constants.ID_MDP_TARJETA){
				parameters.put("fot.numAuto"		, es.getAprobacionVisaMastercard());
			} else if(es.getMetodoDePagoId() == Constants.ID_MDP_EFECTIVO_Y_TARJETA){
				parameters.put("fot.impRecib"		, Constants.df2Decimal.format(es.getImporteRecibido()));		
				parameters.put("fot.numAuto"		, es.getAprobacionVisaMastercard());
				parameters.put("fot.cargo"		    , Constants.df2Decimal.format(es.getTotal()-es.getImporteRecibido()));		
			}
			parameters.put("fot.leyendaIVA" , "* "+Constants.df2Decimal.format(valorIVA)+"  I.V.A. al 16% Yá Incluido.");
			parameters.put("fot.leyenda1" , "¡ LE AGRADECEMOS SU COMPRA !");
			parameters.put("fot.leyenda2" , "VUELVA PRONTO");
		} else if(es.getTipoMov() == Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION){
			parameters.put("fot.leyenda2" , "VUELVA PRONTO");
		}
		
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
		parameters.put("fot.leyenda3" , "http://perfumeriamarlen.com.mx");		
		parameters.put("fot.leyenda4" , "http://facebook.com/PerfumeriaMarlen");

		return new JarpeReportsInfoDTO(parameters, records);
	}
	
	public static String ticketConGuiones(String ticket,char sep){
		StringBuilder sb = new StringBuilder();
		
		final char[] ticketCA = ticket.toCharArray();
		int i=0;
		for(char c: ticketCA){
			sb.append(c);
			i++;
			if(i%4==0){
				sb.append(sep);
			}
		}
		
		return sb.toString();
	}

	
}
