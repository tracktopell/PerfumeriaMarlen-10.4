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
    private static boolean firstLoad=false;
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
			int ci = 0;
			int cf = 0;
			if(d.getTa()== Constants.ALMACEN_PRINCIPAL){
				ci = p.getA1c();
				cf = p.getA1c() + d.getC() * add;
				p.setA1c(cf);
			} else if(d.getTa() == Constants.ALMACEN_REGALIAS){
				ci = p.getaRc();
				cf = p.getaRc() + d.getC() * add;
				p.setaRc(cf);
			} else if(d.getTa() == Constants.ALMACEN_OPORTUNIDAD){
				ci = p.getaOc();
				cf = p.getaOc() + d.getC() * add;
				p.setaOc(cf);
			}
			logger.trace("\tcommit:"+d.getC()+" x "+d.getCb()+"["+d.getTa()+"]:("+ci+")<-("+cf+")");
		}
		
		esList.add(escd);
		
		persist();
	}
    
	@Deprecated
	static void _old_reset(){
		logger.debug("old_reset:");
		Date now=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdddHHmmss");
		
		File origFile   = new File(fileName);
		File backupFile = new File(fileName.replace(".json", "_"+sdf.format(now)+".json"));
		
		boolean r = origFile.renameTo(backupFile);
		
		logger.debug("old_reset: backup file ?"+r);
				
		esList.clear();
	}
	
	static void setSentAllES(){
		logger.debug("setSentAllES: esList="+esList.size());
        if(!firstLoad){
            laod();
        }
		int ixc=0;
		for(ES_ESD es_esd: esList) {
            logger.trace("setSentAllES:\t->esList["+ixc+"]:set SENT TO: es="+es_esd.getEs().toShrotString());
			if(es_esd.getS()==ES_ESD.STATUS_SYNC_LOCAL){
				es_esd.setS(ES_ESD.STATUS_SYNC_SENT);
			}
		}
	}
	
    static void operactionSentFailed(){
		logger.debug("operactionSentFailed(): esList="+esList.size());
        if(!firstLoad){
            laod();
        }
		int ixc=0;
		for(ES_ESD es_esd: esList) {
            logger.debug("operactionSentFailed():\t-> esList["+ixc+"]: es="+es_esd.getEs().toShrotString());
			if(es_esd.getS()==ES_ESD.STATUS_SYNC_LOCAL){
				es_esd.setS(ES_ESD.STATUS_SYNC_ERROR);
			}
		}

	}
    
	static void laod(){        
		File fileToLoad = new File(fileName);
		if(fileToLoad.exists() && esList.isEmpty()){
			logger.debug("load:Json File found:"+fileName);
			Gson gson=new Gson();
			try {
				FileReader fr = new FileReader(fileToLoad);
				logger.debug("load:Reading:");
				//final ArrayList fromJson = gson.fromJson(fr, esList.getClass());
				final ArrayList fromJson = gson.fromJson(fr, new TypeToken<ArrayList<ES_ESD>>(){}.getType());				
				esList.addAll(fromJson);
				logger.debug("load: After read, added, esList.size="+esList.size());
                for(ES_ESD esd_es:esList){
                    logger.trace("load:\tes_esd="+esd_es);
                }
                firstLoad=true;
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
			
			logger.debug("persist: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length()+" bytes.");
			
			
		}catch(IOException ioe){
			logger.error("Error to write",ioe);
		}
	}
	
	public void sync(){
	
	}

	public static ArrayList<ES_ESD> getEsList() {
		return esList;
	}
	
	public static ArrayList<ES_ESD> getEsVentasList() {
		ArrayList<ES_ESD> nsList=new ArrayList<ES_ESD>();
		
		for(ES_ESD e: esList){
			if(e.getEs().getTm()==Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){
				nsList.add(e);
			}
		}
		
		return nsList;
	}
	
	public static ArrayList<ES_ESD> getEsDevolucionesList() {
		ArrayList<ES_ESD> nsList=new ArrayList<ES_ESD>();
		
		for(ES_ESD e: esList){
			if(e.getEs().getTm()==Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION){
				nsList.add(e);
			}
		}
		
		return nsList;
	}

	public static ArrayList<ES_ESD> getEsListNotSent() {
		ArrayList<ES_ESD> nsList=new ArrayList<ES_ESD>();
		
		for(ES_ESD e: esList){
			if(e.getS()==ES_ESD.STATUS_SYNC_LOCAL ||e.getS()==ES_ESD.STATUS_SYNC_RESENT){
				nsList.add(e);
			}
		}
		
		return nsList;
	}

}
