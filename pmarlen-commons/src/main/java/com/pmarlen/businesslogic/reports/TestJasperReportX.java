package com.pmarlen.businesslogic.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestJasperReportX {
	private static Logger logger = Logger.getLogger(TestJasperReportX.class.getName());
	private static String reportDesignDir="/reports/";
	
	public static void generaPdfTicketPedidoVenta(Collection<Map<String,?>> col,Map<String,Object> parameters) {
		byte[] pdfBytes = null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath      = "";
			String compiledReportClassPath = "";
            
			logger.info("Default Locale:"+Locale.getDefault());
            
			reportFileName = "ReportX";
			
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = "./"+ reportFileName + ".jasper";
			
            logger.info("Ok, jrxml loaded");
			
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.info("Ok, JRDataSource created");
            JasperReport jasperReport = null;
            
			File compiledReportPathFile=new File(compiledReportPath);
			
			logger.info("compiledReportPathFile:"+compiledReportPathFile.getAbsolutePath()+", exist?"+compiledReportPathFile.exists());
			
			int ph = 132+35+23+111+19*col.size();			
            if(! compiledReportPathFile.exists()){
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
				logger.info("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);
				jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
				logger.info("Ok, JasperReport 1st time loaded from:"+compiledReportPath);
			} else {
				jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
				logger.info("Ok, JasperReport, loaded from:"+compiledReportPath);
			}
			
			logger.info("Ok, JasperReport Ready, filling.");
			
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.info("Ok, JasperPrint created.");
            OutputStream os = null;
			//os = new ByteArrayOutputStream();
			os = new FileOutputStream("ReportX.pdf");
			JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
    
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setMetadataAuthor("PerfumeriaMarlen_SAA_10.3");						
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
			logger.info("Ok, JasperExportManager executed");
			//pdfBytes = baos.toByteArray();            
            logger.info("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		//return pdfBytes;
    }
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
		try {
			props.load(TestJasperReportX.class.getResourceAsStream("/log4j_local/log4j.properties"));
			PropertyConfigurator.configure(props);
		}catch(IOException ioe){
			ioe.printStackTrace(System.err);
		}
		Collection<Map<String,?>> col = new ArrayList<Map<String,?>>();
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		parameters.put("codigoPedido" ,"878789789");
		parameters.put("fecha" ,"2015/11/21");
		parameters.put("hora" ,"16:23:45");
		parameters.put("direccion" ,"XXXXXXXXXXXXXXX XXXXX XXXXXXXXX XXXXXX");
		parameters.put("cliente" ,"AAAAAAA AAAAAA AAAAAAA");
		parameters.put("rfc" ,"XXXX000000CCC");
		parameters.put("usuario" ,"XXXXXXX XXXXXXXXXX");
		parameters.put("condiciones" ,"XXXXXXXXXXXXXXXX");
		parameters.put("subtotal" ,"100.00");
		parameters.put("iva" ,"16.00");
		parameters.put("descuento" ,"0.00");
		parameters.put("total" ,"116.00");
		parameters.put("importeLetra" ,"CIENTO DIEZ Y SEIS");
		parameters.put("LeyendaFotter" ,"XXXXXXX XXXXXXXXXX XXXXXXXXX");
		parameters.put("recibimos" ,"500.00");
		parameters.put("cambio" ,"384.00");
		parameters.put("esClienteRegistrado","");
		parameters.put("ticket" ,"8788897897897897897");
		parameters.put("caja" ,"1");
		parameters.put("aprobacion" ,"1212121211212");
		
		for(int i=0;i<50;i++){
			Map<String,Object> r = new HashMap<String,Object>();
			
			r.put("cantidad" ,1);
			r.put("clave"    ,2);
			r.put("codigoBarras" ,"1234567890123");
			r.put("descripcion" ,"XXXX XXXXXXXXX XXXXXXXX");
			r.put("precio" ,"12.34");
			r.put("importe" ,"123.45");
			
			col.add(r);
		}
		
		generaPdfTicketPedidoVenta(col, parameters);
	}
	
}
