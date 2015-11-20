/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.ticket.systemprinter;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class SendFileToSystemPrinter {

	private static Logger logger = Logger.getLogger(SendFileToSystemPrinter.class.getSimpleName());

	public static void printFile(String file) throws IOException {
		logger.debug("==========================================================");
		logger.debug("printFile:file="+file);
		FileInputStream textstream = null;
		try {
			textstream = new FileInputStream(file);
		} catch (FileNotFoundException ffne) {
			throw new IOException(ffne.getMessage());
		}
		DocFlavor theFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		DocFlavor binFlavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
		DocFlavor otherFlavor = null;
		
		logger.debug("printFile:myFlavor:MimeType="+theFlavor.getMimeType()+", MediaType="+theFlavor.getMediaType()+", MediaSubtype="+theFlavor.getMediaSubtype());
		
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		logger.debug("printFile:defaultPrintService: " + defaultPrintService);
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        logger.debug("printFile:printServices: " + printServices);
		int i=0;
		if(printServices != null){
			for (PrintService ps : printServices) {
				boolean servieSelected= ps.equals(defaultPrintService);
				logger.debug("\tprintFile: Printer["+i+"]: " +(servieSelected?" -> ":"    ")+ps.getName()); 
				i++;
				DocFlavor[] supportedDocFlavors = ps.getSupportedDocFlavors();
				int j=0;
				logger.debug("\t\tprintFile:supportedDocFlavors:"+supportedDocFlavors);
				if(supportedDocFlavors!=null){
					for(DocFlavor df: supportedDocFlavors){
						boolean likeMyFlavor=false;
						if(df.getMimeType().equalsIgnoreCase(theFlavor.getMimeType())){
							likeMyFlavor = true;
						}
						logger.debug("\t\tprintFile:supportedDocFlavors["+j+"]: "+(likeMyFlavor?"-> ":"   ")+"MimeType="+df.getMimeType()+", MediaType="+df.getMediaType()+", MediaSubtype="+df.getMediaSubtype());
						if(likeMyFlavor && servieSelected){
							otherFlavor = df;
						}
						j++;
					}
				}
			}
		}		
		logger.debug("printFile:flavors.... ? ");
		if(defaultPrintService!=null && !defaultPrintService.isDocFlavorSupported(theFlavor) && otherFlavor!=null){
			logger.debug("printFile:not defaultPrintService.isDocFlavorSupported("+theFlavor+"): Dam it !! , changing the flavor to:"+otherFlavor);
			if(!defaultPrintService.isDocFlavorSupported(otherFlavor)){
				logger.debug("printFile: no defaultPrintService.isDocFlavorSupported("+otherFlavor+"): Dam it !! , changing the flavor to:"+binFlavor);
				if(!defaultPrintService.isDocFlavorSupported(binFlavor)){
					logger.debug("printFile: no defaultPrintService.isDocFlavorSupported("+binFlavor+"): Dam it !!   :(   WE CAN'T PRINT ");
				} else {
					theFlavor = binFlavor;
				}
			} else {
				theFlavor = otherFlavor;
			}
		
		
			DocPrintJob job = defaultPrintService.createPrintJob();
			logger.debug("printFile:job="+job);
			// Set the document type
			// Create a Doc
			Doc myDoc = new SimpleDoc(textstream, theFlavor, null);
			logger.debug("printFile:myDoc="+myDoc);

			try {
				PrintRequestAttributeSet printAtt = new HashPrintRequestAttributeSet();
				Date today=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
				printAtt.add(new JobName("JAVA_PRINTJOB_"+sdf.format(today),Locale.getDefault())); 
				MyPrintListener pl = new MyPrintListener();
				job.addPrintJobListener(pl);
				logger.debug("printFile: ["+defaultPrintService.getName()+"] before print, with flavor:"+theFlavor);
				job.print(myDoc, printAtt);
				logger.debug("printFile:after print");
				try {
					while(pl.jobRunning){
						Thread.sleep(1000L);
						logger.debug("printFile:...waiting 1 sec.");
					}
				}catch(InterruptedException ie){
					logger.debug("printFile: ie:"+ie.getMessage());
				}
			} catch (PrintException pe) {
				logger.error("printFile:", pe);
			}
		}
		
	}
	
	private static class MyPrintListener implements PrintJobListener {
		private boolean jobRunning;

		public MyPrintListener() {
			jobRunning = true;
		}
		
		@Override
		public void printDataTransferCompleted(PrintJobEvent pje) {
			logger.debug("printDataTransferCompleted:pje"+pje);
			jobRunning = false;
		}

		@Override
		public void printJobCanceled(PrintJobEvent pje) {
			logger.debug("printJobCanceled:pje"+pje);
			jobRunning = false;
		}

		@Override
		public void printJobCompleted(PrintJobEvent pje) {
			logger.debug("printJobCompleted:pje"+pje);
			jobRunning = false;
		}

		@Override
		public void printJobFailed(PrintJobEvent pje) {
			logger.debug("printJobFailed:pje"+pje);
			jobRunning = false;
		}

		@Override
		public void printJobNoMoreEvents(PrintJobEvent pje) {
			logger.debug("printJobNoMoreEvents:pje"+pje);
		}

		@Override
		public void printJobRequiresAttention(PrintJobEvent pje) {
			logger.debug("printJobRequiresAttention:pje"+pje);
		}
	}

}
