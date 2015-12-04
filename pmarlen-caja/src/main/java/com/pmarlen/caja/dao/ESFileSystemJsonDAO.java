/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class ESFileSystemJsonDAO {
	private static Logger logger = Logger.getLogger(ESFileSystemJsonDAO.class.getName());
	private static final String fileName = "EntradaSalida.json";
	private static final ArrayList<ES_ESD> esList = new ArrayList<ES_ESD>();
	
	public static void commit(ES_ESD escd){
		
		int tm = escd.getEs().getTm();
		int add = 0;
		
		if(			tm>=Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA &&
					tm <=Constants.TIPO_MOV_SALIDA_MERMA){
			add = -1;
		} else if(	tm >=Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA &&
					tm <=Constants.TIPO_MOV_ENTRADA_ALMACEN_TRASPASO){
			add = 1;
		}
		
		for(ESD d:escd.getEsdList()){
			I p = MemoryDAO.fastSearchProducto(d.getCb());
			
			if(d.getA()== Constants.ALMACEN_PRINCIPAL){
				p.setA1c(p.getA1c() * d.getC() * add);
			} else if(d.getA() == Constants.ALMACEN_REGALIAS){
				p.setaRc(p.getaRc()* d.getC() * add);
			} else if(d.getA() == Constants.ALMACEN_OPORTUNIDAD){
				p.setaOc(p.getaOc()* d.getC() * add);
			}
			
		}
		
		esList.add(escd);
		
		persist();
	}
	
	static void old_reset(){
		logger.debug("old_reset:");
		Date now=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdddHHmmss");
		
		File origFile   = new File(fileName);
		File backupFile = new File(fileName.replace(".json", "_"+sdf.format(now)+".json"));
		
		boolean r = origFile.renameTo(backupFile);
		
		logger.debug("old_reset: backup file ?"+r);
				
		esList.clear();
	}
	
	static void reset(){
		logger.debug("reset:"+esList.getClass());
		for(ES_ESD e: esList) {
			if(e.getS()==ES_ESD.STATUS_SYNC_LOCAL){
				e.setS(ES_ESD.STATUS_SYNC_SENT);
			}
		}
	}
	
	static void laod(){
		File fileToLoad = new File(fileName);
		if(fileToLoad.exists() && esList.isEmpty()){
			logger.debug("load:File found:"+fileName);
			Gson gson=new Gson();
			try {
				FileReader fr = new FileReader(fileToLoad);
				logger.debug("\tReading");
				esList.addAll(gson.fromJson(fr, esList.getClass()));
				logger.debug("\tOK, esList.size="+esList.size());				
			}catch(IOException ioe){
				logger.error("load, fail:",ioe);
			}
		}
	}
	
	static void persist(){
		logger.debug("persist: esList.size="+esList.size());
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(esList);
		FileWriter fw = null;
		try {
			
			fw = new FileWriter(fileName);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
			
			File x=new File(fileName);
			
			logger.debug("persist: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
			
			
		}catch(IOException ioe){
			logger.error("Error to write",ioe);
		}
	}
	
	public void sync(){
	
	}

	public static ArrayList<ES_ESD> getEsList() {
		return esList;
	}
	
}
