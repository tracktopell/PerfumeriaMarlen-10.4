/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.ticket.systemprinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class UnixSendToLP {

	private static Logger logger = Logger.getLogger(UnixSendToLP.class.getSimpleName());

	public static void printFile(String file) throws IOException {
		logger.debug("==========================================================");
		logger.debug("printFile:file=" + file);

		String[] command = new String[2];
		command[0] = "/usr/bin/lp";
		command[1] = file;
		try {
			logger.debug("printFile:exec: " + Arrays.asList(command));
			Process p = Runtime.getRuntime().exec(command);
			int r=p.waitFor();
			logger.debug("printFile:exit: " + r);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
}
