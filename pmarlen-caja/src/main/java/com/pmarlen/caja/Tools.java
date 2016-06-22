package com.pmarlen.caja;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ES_ESD;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * com.pmarlen.caja.Tools
 * @author alfredo
 */
public class Tools {
	private static Logger logger = Logger.getLogger(Tools.class.getName());
	public static void main(String[] args) {
		String fileName = null;
		String fechaFiltro = null;
		for(String arg: args){
			String[] argVal = arg.split("=");
			if(argVal[0].startsWith("-jsonFile")){
				fileName = argVal[1];
			} else if(argVal[0].startsWith("-fechaFiltro")){
				fechaFiltro = argVal[1];
			}
		}
		
		if(fileName == null){
			System.err.println("Usage: com.pmarlen.caja.Tools -jsonFile=PathToJsonFile");
			System.exit(1);
		}
		
		InputStream is = null;
		File fileToLoad = null;
		ArrayList<ES_ESD> esList = new ArrayList<ES_ESD>();
		fileToLoad = new File(fileName);
		if(fileToLoad.exists() ){
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
				System.exit(2);
			}
		}
		
		System.out.print("------------>>>EntrasaSalida:");
		System.out.print("fechaFiltro="+fechaFiltro);
		System.out.println();
		
		double tot=0.0;
		for(ES_ESD es_esd: esList){
			ES es = es_esd.getEs();
			final String fechaFormated = Constants.sdfLogFile.format(new Date(es.getFc()));
			
			if(fechaFiltro!=null && !fechaFormated.contains(fechaFiltro)){
				continue;
			}
			System.out.print(es.getNt());
			System.out.print("\t");
			System.out.print(es.getS());
			System.out.print("\t");
			
			int   fac = 1;
			if(es.getTm()==Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){
				fac = 1;
				System.out.print("VENTA");
			} else if(es.getTm()==Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION){
				fac = -1;
				System.out.print("DEVOL");
			}
			tot   += es.getTot()*fac;
			
			System.out.print("\t");			
			System.out.print(fechaFormated);
			System.out.print("\t");
			System.out.print(Constants.df2Decimal.format(es.getTot() * fac));
			System.out.println();
		}
		System.out.println("\tTot="+Constants.df2Decimal.format(tot));
		

	}
	
}
