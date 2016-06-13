/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
			logger.debug("commit:<<<=== SALIDA");
			add = -1;
		} else if(	tm >=Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA &&
					tm <=Constants.TIPO_MOV_ENTRADA_ALMACEN_TRASPASO){
			logger.debug("commit:===>>> ENTRADA");
			add = 1;
		}
		logger.debug("commit:TICKET:"+escd.getEs().getNt()+", SUC/CAJA="+escd.getEs().getS()+"/"+escd.getEs().getJ()+" TM:"+tm);
		for(ESD d:escd.getEsdList()){
			I p = MemoryDAO.fastSearchProducto(d.getCb());
			
			if(d.getTa()== Constants.ALMACEN_PRINCIPAL){
				p.setA1c(p.getA1c() * d.getC() * add);
			} else if(d.getTa() == Constants.ALMACEN_REGALIAS){
				p.setaRc(p.getaRc()* d.getC() * add);
			} else if(d.getTa() == Constants.ALMACEN_OPORTUNIDAD){
				p.setaOc(p.getaOc()* d.getC() * add);
			}
			logger.debug("\tcommit:"+d.getC()+" x "+d.getCb()+"["+d.getTa()+"]");
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
		logger.debug("reset: esList.getClass():"+esList.getClass()+", esList="+esList.getClass());
		Iterator ix = esList.iterator();
		int ixc=0;
		while(ix.hasNext()){
			logger.debug("reset:-> esList["+ixc+"]: class:"+ix.next().getClass());
			ixc++;
		}
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
				logger.debug("\tReading:");
				//final ArrayList fromJson = gson.fromJson(fr, esList.getClass());
				final ArrayList fromJson = gson.fromJson(fr, new TypeToken<ArrayList<ES_ESD>>(){}.getType());
				logger.debug("\t\tRead:fromJson="+fromJson);
				esList.addAll(fromJson);
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

	public static ArrayList<ES_ESD> getEsListNotSent() {
		ArrayList<ES_ESD> nsList=new ArrayList<ES_ESD>();
		
		for(ES_ESD e: esList){
			if(e.getS()==ES_ESD.STATUS_SYNC_LOCAL){
				nsList.add(e);
			}
		}
		
		return nsList;
	}

}
