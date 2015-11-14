package com.pmarlen.businesslogic.reports;

import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.LogicaFinaciera;
import com.pmarlen.model.Constants;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class GeneradorJasperReport {
	private static Logger logger = Logger.getLogger(GeneradorJasperReport.class.getName());
	private static String reportDesignDir="/reports/";
	
	public static byte[] generaPdfTicketPedidoVenta(Collection<Map<String,?>> col,Map<String,Object> parameters) {
		byte[] pdfBytes = null;
		try {
			String reportFileName;
			String reportPath;
			String compiledReportPath      = "";
			String compiledReportClassPath = "";
            
			logger.info("Default Locale:"+Locale.getDefault());
            
			reportFileName = "pedidoVentaTicketDesign";
			
			reportPath = reportDesignDir + reportFileName + ".jrxml";
			compiledReportClassPath = reportDesignDir + reportFileName + ".jasper";
			compiledReportPath = "./"+ reportFileName + ".jasper";
			
            logger.info("Ok, jrxml loaded");
			
            JRDataSource beanColDataSource = new JRMapCollectionDataSource(col);
            logger.info("Ok, JRDataSource created");
            JasperReport jasperReport = null;
            //String intDecParts[] = dfEnt.format(esf.getTotal()).split("\\.");
			File compiledReportPathFile=new File(compiledReportPath);
			/*
            if(! compiledReportPathFile.exists()){
				InputStream inputStream = GeneradorImpresionPedidoVenta.class.getResourceAsStream(reportPath);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				JasperCompileManager.compileReportToStream(jasperDesign,new FileOutputStream(compiledReportPathFile));
				logger.info("Ok, JasperReport compiled and saved 1st time, to:"+compiledReportPath);				
			}
			jasperReport = (JasperReport)JRLoader.loadObject(compiledReportPathFile);
			*/
			InputStream inputStream = GeneradorJasperReport.class.getResourceAsStream(reportPath);
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);			
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			logger.info("Ok, JasperReport loaded from:"+compiledReportPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            logger.info("Ok, JasperPrint created.");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
    
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setMetadataAuthor("PerfumeriaMarlen_SAA_10.3");						
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
			logger.info("Ok, JasperExportManager executed");
			pdfBytes = baos.toByteArray();            
            logger.info("Ok, finished");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //System.exit(1);
        }
		return pdfBytes;
    }
}
