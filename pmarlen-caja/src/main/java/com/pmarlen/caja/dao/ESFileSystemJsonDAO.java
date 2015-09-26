/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.DES;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.P;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/**
 *
 * @author alfredo
 */
public class ESFileSystemJsonDAO {
	private static Logger logger = Logger.getLogger(ESFileSystemJsonDAO.class.getName());
	private static final String fileName = "ES.json";
	private static final ArrayList<ES> esList = new ArrayList<ES>();
	
	public static void commit(ES es){
		
		int tm = es.getTm();
		int add = 0;
		
		if(			tm>=Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA &&
					tm <=Constants.TIPO_MOV_SALIDA_MERMA){
			add = -1;
		} else if(	tm >=Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA &&
					tm <=Constants.TIPO_MOV_ENTRADA_ALMACEN_TRASPASO){
			add = 1;
		}
		
		for(DES d:es.getD()){
			P p = MemoryDAO.fastSearchProducto(d.getCb());
			if(d.getaId() == Constants.ALMACEN_PRINCIPAL){
				p.setA1c(p.getA1c() * d.getC() * add);
			} else if(d.getaId() == Constants.ALMACEN_REGALIAS){
				p.setaRc(p.getA1c() * d.getC() * add);
			} else if(d.getaId() == Constants.ALMACEN_OPORTUNIDAD){
				p.setaOc(p.getA1c() * d.getC() * add);
			}
		}
		
		esList.add(es);
		
		persist();
	}
	
	public void laod(){
		
	}
	
	public static void persist(){
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(esList);
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
		}catch(IOException ioe){
			logger.error("Error to write",ioe);
		}
	}
	
	public void sync(){
	
	}

	public static ArrayList<ES> getEsList() {
		return esList;
	}
	
}
