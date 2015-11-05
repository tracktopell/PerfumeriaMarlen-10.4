/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.ticket.systemprinter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class SendFileToSystemPrinter {

	private static Logger logger = Logger.getLogger(SendFileToSystemPrinter.class.getSimpleName());

	public static void printFile(String file) throws IOException {
		// Input the file
		FileInputStream textstream = null;
		try {
			textstream = new FileInputStream(file);
		} catch (FileNotFoundException ffne) {
			throw new IOException(ffne.getMessage());
		}
		
		// Set the document type
		DocFlavor myFormat = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
		// Create a Doc
		Doc myDoc = new SimpleDoc(textstream, myFormat, null);
		
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		
		DocPrintJob job = defaultPrintService.createPrintJob();
		try {
			job.print(myDoc, null);
			logger.error("main: ok printed");
		} catch (PrintException pe) {
			logger.error("in main:", pe);
		}
		
	}
}
