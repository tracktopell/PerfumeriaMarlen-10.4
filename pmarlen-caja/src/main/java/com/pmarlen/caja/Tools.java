package com.pmarlen.caja;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ES_ESD;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * com.pmarlen.caja.Tools
 * @author alfredo
 */
public class Tools {
	//private static Logger logger = Logger.getLogger(Tools.class.getName());
	public static void main(String[] args) {
		LogManager.getRootLogger().setLevel(Level.INFO);
		String pathToEntradaSalidaJsonFile = "EntradaSalida.json";
		String pathToCorteCajaJsonFile     = "CorteCajaDTO.json";
		String fechaFiltro                 = Constants.sdfDate.format(new Date());
        boolean inventario        = false;
		boolean corteCajaResumen  = false;
		for(String arg: args){
			String[] argVal = arg.split("=");
			if(argVal[0].equals("-I")){
                inventario = true;
            }else if(argVal[0].startsWith("-ES")){
				pathToEntradaSalidaJsonFile = argVal[1];
			} else if(argVal[0].startsWith("-CC")){
				pathToCorteCajaJsonFile     = argVal[1];
			} else if(argVal[0].startsWith("-FF")){
				fechaFiltro = argVal[1];
			} else if(argVal[0].startsWith("-RC")){
				corteCajaResumen  = true;
			}
		}
		
        System.err.println("Usage: java -cp pmarlen-caja.jar com.pmarlen.caja.Tools [-ES=EntradaSalida.json] [-CC=CorteCajaDTO.json] [-FF=AAAAMMDD]");
        //System.err.println("Usage: java -cp pmarlen-caja.jar com.pmarlen.caja.Tools -I");
		System.err.println("Usage: java -cp pmarlen-caja.jar com.pmarlen.caja.Tools -CC");
		
		InputStream is = null;
		File fileToLoad = null;
		ArrayList<ES_ESD> esList = new ArrayList<ES_ESD>();
		fileToLoad = new File(pathToEntradaSalidaJsonFile);
		if(fileToLoad.exists() ){
			System.out.println("load:File found:"+pathToEntradaSalidaJsonFile);
			Gson gson=new Gson();
			try {
				FileReader fr = new FileReader(fileToLoad);
				System.out.println("\tReading:");
				//final ArrayList fromJson = gson.fromJson(fr, esList.getClass());
				final ArrayList fromJson = gson.fromJson(fr, new TypeToken<ArrayList<ES_ESD>>(){}.getType());
				System.out.println("\t\tRead:fromJson="+fromJson);
				esList.addAll(fromJson);
				System.out.println("\tOK, esList.size="+esList.size());				
			}catch(IOException ioe){
                ioe.printStackTrace(System.err);
				System.exit(2);
			}
		} else if(!corteCajaResumen){
            System.err.println("Archivo:"+pathToEntradaSalidaJsonFile+", Error al abrir.");
			//System.exit(3);
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
			System.out.print("\t[");
			System.out.print(es_esd.getS()==ES_ESD.STATUS_SYNC_LOCAL?"LOC":es_esd.getS()==ES_ESD.STATUS_SYNC_SENT?"ENV":es_esd.getS()==ES_ESD.STATUS_SYNC_ERROR?"ERR":"???");
			System.out.print("]\t");
			
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
		if(! esList.isEmpty()) {
			System.out.println("\tTot="+Constants.df2Decimal.format(tot));
			System.out.println();
		}
		System.out.print("------------>>>corteCajaResumen:"+corteCajaResumen);
		if(corteCajaResumen){
			System.out.println("------------>>>CorteCaja: pathToCorteCajaJsonFile="+pathToCorteCajaJsonFile);        
			
			if(pathToCorteCajaJsonFile != null){
				is = null;
				fileToLoad = null;
				CorteCajaDTO cc=  null;
				fileToLoad = new File(pathToCorteCajaJsonFile);
				if(fileToLoad.exists() ){
					System.out.println("load:CorteCaja:File found:"+pathToEntradaSalidaJsonFile);
					Gson gson=new Gson();
					try {
						FileReader fr = new FileReader(fileToLoad);
						System.out.println("\tReading:");

						cc = gson.fromJson(fr, new TypeToken<CorteCajaDTO>(){}.getType());
						System.out.println("\t\tRead:CorteCaja="+cc);

						CorteCajaDTO ccA = MemoryDAO.readLastSavedCorteCajaDTOApertura();
						System.out.println("\t\tRead:CorteCajaDTOApertura="+ccA);
						
						System.out.println("\t\tRead:isInAperturaCorteCajaDTO="+MemoryDAO.isInAperturaCorteCajaDTO());
					}catch(IOException ioe){
						ioe.printStackTrace(System.err);
						System.exit(4);
					}
				} else {
					System.err.println("Archivo:"+pathToCorteCajaJsonFile+", Error al abrir.");
					//System.exit(5);
				}
			}
		}
	}	
}
