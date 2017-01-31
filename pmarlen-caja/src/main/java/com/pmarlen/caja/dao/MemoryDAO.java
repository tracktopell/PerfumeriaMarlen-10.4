package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.model.Notificacion;
import com.pmarlen.caja.model.SyncUpdateListener;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.pmarlen.rest.dto.U;
import com.pmarlen.rest.dto.UserAgent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
/**
 *
 * @author alfredo
 */
public class MemoryDAO {
	private static Properties properties = new Properties();
	private static final String uriServiceIAmAlive    = "/rest/iamaliveservice/hello";
	private static final String uriServiceZipSync     = "/rest/syncservice/sync";
	private static final String uriSaldoEstimado      = "/rest/syncservice/saldoEstimado";
	private static final String uriBuscarTicket       = "/rest/syncservice/ticket";	
	private static boolean enviandoCierreCaja         = false;
	private static boolean enviandoCierreCorrectmente = false;	
	private static final String corteCajaDTOjsonFile    = "CorteCajaDTO.json";
	private static final String corteCajaDTOjsonHistoryFile    = "CorteCajaDTO([0-9])+_([0-9])+\\.json";
	//private static final String aperturaCajaDTOjsonFile = "AperturaCajaDTO.json";
	public static final int URL_CONNECTION_TIMEOUT = 5000;	
	private static SyncUpdateListener  syncUpdateListener;
	static {
		properties.put("sucursal","1");
		properties.put("caja","1");
		properties.put("host","localhost");
		properties.put("port","8070");
		properties.put("context","/l30");
		properties.put("textSystemPrinterColumns","38");
		
		properties.put("dropboxdir",System.getProperty("user.home")+"/Dropbox/");
	}
	
	private static SyncDTOPackage paqueteSinc;
	
	private static Logger logger = Logger.getLogger(MemoryDAO.class.getName());
	private static final String fileName = "./fileModel.zip";
	
	private static HashMap<String,I> productosParaBuscar;
	private static String propertiesFileNAme="./system.properties";
	private static boolean exsistFile = false;	
	private static List<U> usuarioList;
	private static List<Cliente> clienteList;
	private static List<MetodoDePago> metodoDePagoList;
	private static List<FormaDePago> formaDePagoList;
	private static Sucursal sucursal;
	private static List<Almacen> almacenList;

	public static void setSyncUpdateListener(SyncUpdateListener syncUpdateListener) {
		MemoryDAO.syncUpdateListener = syncUpdateListener;
	}
	
	public static void loadProperties() {
		
		File fileProperties = new File(propertiesFileNAme);
		
		if(fileProperties.exists() && fileProperties.canRead()){
			try {
				logger.debug("->Properties local File found, reading File Properties:"+fileProperties+"");
				properties.load(new FileInputStream(fileProperties));
				logger.debug("->ok, Properties read:"+properties);
				exsistFile = true;
			}catch(IOException ioe){				
				logger.error( "Can`t read File for properties", ioe);
			}
		}
	}

	public static void persistPRoperties() {
		try {
			logger.debug("->Properties Doesn't exsist, wrinting File Properties.");
			properties.store(new FileOutputStream(propertiesFileNAme), "Persisted");
			logger.debug("->ok, writing.");
		}catch(IOException ioe){
			logger.error( "Can`t create File for properties", ioe);
		}
	}

	public static boolean isExsistFile() {
		return exsistFile;
	}

	public static Properties getProperties() {
		return properties;
	}
	
	public static String getServerContext(){
		StringBuilder sbURL = new StringBuilder("http://");
		sbURL.append(properties.get("host")).
				append(":").
				append(properties.get("port")).
				append(properties.get("context"));
		return sbURL.toString();
	}
    
    public static String getServerContextManual(){
		StringBuilder sbURL = new StringBuilder("http://");
		sbURL.append(properties.get("host")).append("/PML30-Caja.pdf");
		return sbURL.toString();
	}
	
	public static void setPaqueteSinc(SyncDTOPackage paqueteSinc) {
		MemoryDAO.paqueteSinc = paqueteSinc;
	}

	public static SyncDTOPackage getPaqueteSinc() {
		logger.debug("getPaqueteSinc:paqueteSinc="+paqueteSinc);
		if(paqueteSinc == null ){
//			try {
//				if(!exsistPackage()){
//					download();
//				}
//				readLocally();
//			}catch(IOException ioe){
//				logger.error("getPaqueteSinc: getting paqueteSinc:",ioe);
//			}
			
			if(exsistPackage()){
				readLocally();
			}
		}
		return paqueteSinc;
	}
	
	public static void preLoad() {
		logger.debug("preLoad:paqueteSinc is null ?");
		if(paqueteSinc == null ){
			try {
				if(exsistPackage()){
					readLocally();
				}
			}catch(Exception ioe){
				logger.error("preLoad:: getting paqueteSinc:",ioe);
			}
		}		
	}
	private static int iAmALiveBuild        =0;
	private static int iAmALiveOKEnviadas   =0;
	
	private static boolean runnigPool = true;
	private static int     syncPollState = 0;
	private static long    xt = 0;
	
	public final static int SYNC_STATE_BEFORE_RUNNING        = 0;
	public final static int SYNC_STATE_BEFORE_CONNECTING     = 1;
	public final static int SYNC_STATE_BEFORE_DOWNLOADED     = 2;
	public final static int SYNC_STATE_READ                  = 3;
	public final static int SYNC_STATE_BEFORE_CONNECTINGLIVE = 4;
	public final static int SYNC_STATE_IMLIVE                = 5;
	public final static int SYNC_STATE_IO_ERROR              = 20;	
	public final static int SYNC_STATE_SERVER_4XX            = 21;
	public final static int SYNC_STATE_SERVER_5XX            = 22;
	public final static int SYNC_STATE_CONNECTION            = 23;
	public final static int SYNC_STATE_ERROR                 = 99;
	
	private final static long TIMESLEEP_MS        = 1000L;
	private final static int  DOWNLOADPERIOD_SECS = 120;
	private final static int  IMALIVEPERIOD_SECS  = 30;
    private final static int  CIERREPERIOD_SECS   = 2;
	
	public static void getPaqueteSyncPoll() {
		
		logger.debug("getPaqueteSyncPoll:");
		ESFileSystemJsonDAO.laod();
		logger.debug("getPaqueteSyncPoll:========================>>BEFORE while<<========================("+runnigPool+")");
		syncPollState = SYNC_STATE_BEFORE_RUNNING;
		for(xt=0;runnigPool;xt++){
			logger.trace("getPaqueteSyncPoll:\t----------------->> while running, download");
			if(xt % DOWNLOADPERIOD_SECS == 0){
				try {
					syncPollState = SYNC_STATE_BEFORE_CONNECTING;
					logger.trace("getPaqueteSyncPoll: -->  [  DOWNLOAD  ] syncPollState="+syncPollState);
					download();
					syncPollState = SYNC_STATE_BEFORE_DOWNLOADED;
					logger.trace("getPaqueteSyncPoll:\t----------------->> OK, downloded, read locally, syncPollState="+syncPollState);
					readLocally();
					syncPollState = SYNC_STATE_READ;
				}catch(ConnectException ce){
					logger.debug("getPaqueteSyncPoll: after call iamalive:"+ce);
					syncPollState = SYNC_STATE_CONNECTION;					
				}catch(FileNotFoundException fnfe){
					logger.debug("getPaqueteSyncPoll: after call downoload, readLocally:"+fnfe);
					syncPollState = SYNC_STATE_SERVER_4XX;					
				}catch(IOException e){
					logger.debug("getPaqueteSyncPoll: after call downoload, readLocally:"+e);
					syncPollState = SYNC_STATE_IO_ERROR;					
				}catch(Exception e){
					logger.error("getPaqueteSyncPoll: after call downoload, readLocally:",e);
					syncPollState = SYNC_STATE_ERROR;
				}
			} else if(xt % IMALIVEPERIOD_SECS == 0){
				try {					
					syncPollState = SYNC_STATE_BEFORE_CONNECTINGLIVE;
					logger.trace("getPaqueteSyncPoll: --> [  I'AM ALIVE  ]syncPollState="+syncPollState);
					iamalive();
					logger.trace("getPaqueteSyncPoll: --> after iamalive : syncPollState="+syncPollState);
					syncPollState = SYNC_STATE_IMLIVE;
				}catch(ConnectException ce){
					logger.debug("getPaqueteSyncPoll: after call iamalive:"+ce);
					syncPollState = SYNC_STATE_CONNECTION;					
				}catch(FileNotFoundException fnfe){
					logger.debug("getPaqueteSyncPoll: after call iamalive:"+fnfe);
					syncPollState = SYNC_STATE_SERVER_4XX;					
				}catch(IOException e){
					logger.debug("getPaqueteSyncPoll: after call iamalive:"+e);
					syncPollState = SYNC_STATE_IO_ERROR;					
				}catch(Exception e){
					logger.error("getPaqueteSyncPoll: after call iamalive:",e);
					syncPollState = SYNC_STATE_ERROR;
				}
			} else if(xt % CIERREPERIOD_SECS == 0){
                CorteCajaDTO corteCajaDTOEnviar = ApplicationLogic.getInstance().getCorteCajaDTO();
                if(enviandoCierreCaja && corteCajaDTOEnviar != null && corteCajaDTOEnviar.getTipoEvento()==Constants.TIPO_EVENTO_CIERRE) {
                    logger.debug("getPaqueteSyncPoll: CIERRE ==>> CIERRE MAS RAPIDO !!");
                    try {					
                        syncPollState = SYNC_STATE_BEFORE_CONNECTINGLIVE;
                        logger.trace("getPaqueteSyncPoll: CIERRE --> [  DOWNLOAD ]syncPollState="+syncPollState);
                        download();
                        logger.trace("getPaqueteSyncPoll: CIERRE --> after download : syncPollState="+syncPollState);
                        readLocally();
                        syncPollState = SYNC_STATE_READ;
                    }catch(ConnectException ce){
                        logger.debug("getPaqueteSyncPoll: CIERRE after call download:"+ce);
                        syncPollState = SYNC_STATE_CONNECTION;					
                    }catch(FileNotFoundException fnfe){
                        logger.debug("getPaqueteSyncPoll: CIERRE after call download:"+fnfe);
                        syncPollState = SYNC_STATE_SERVER_4XX;					
                    }catch(IOException e){
                        logger.debug("getPaqueteSyncPoll: CIERRE after call download:"+e);
                        syncPollState = SYNC_STATE_IO_ERROR;					
                    }catch(Exception e){
                        logger.error("getPaqueteSyncPoll: CIERRE after call iamalive:",e);
                        syncPollState = SYNC_STATE_ERROR;
                    }
                } else{
                    logger.trace("getPaqueteSyncPoll: NO ES ESTADO DE CIERRE:enviandoCierreCaja="+enviandoCierreCaja+", corteCajaDTOEnviar="+corteCajaDTOEnviar);
                    //continue;
                }
			}
			
			try {
				logger.trace("getPaqueteSyncPoll:\t----------------->> while running, sleep for TIMESLEEP_MS="+TIMESLEEP_MS+" ms , DOWNLOADPERIOD_SECS="+DOWNLOADPERIOD_SECS+" ms.., syncPollState="+syncPollState);
				Thread.sleep(TIMESLEEP_MS);
			}catch(Exception e){
				logger.error("getPaqueteSyncPoll:->sleep ? ",e);
			}
						
		}
	}

	public static int getSyncPollState() {
		return syncPollState;
	}
	
	public static void startPaqueteSyncService(){
		logger.debug("startPaqueteSyncService:startPaqueteSyncService");
		new Thread(){
			@Override
			public void run() {
                logger.debug("startPaqueteSyncService:before getPaqueteSyncPoll()-->>");
				getPaqueteSyncPoll();
                logger.debug("startPaqueteSyncService:after getPaqueteSyncPoll()<<--");
			}
		}.start();
	}

	private static boolean exsistPackage() {
		logger.debug("exsistPackage:");
		File modelFile = null;
		modelFile = new File(fileName);
		if (modelFile.canRead() && modelFile.isFile() && modelFile.length() > 1024) {
			logger.debug("exsistPackage: ok, File ("+modelFile.getAbsolutePath()+")Exist !");
			return true;
		}
		logger.debug("exsistPackage: NO, File doesn't Exist :( ");
		return false;
	}
	
	private static String sessionID=null;

	public static String getSessionID() {
		if(sessionID == null) {
			sessionID = ApplicationLogic.calculateSessionId();
		}
		return sessionID;
	}

	private static void download() throws IOException{

		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			logger.debug("download:...creating WebResource for ZIP");
			WebResource webResource = client.resource(getServerContext()+uriServiceZipSync);
			String jsonInput = null;
			SyncDTORequest syncDTORequest = new SyncDTORequest();
			
			IAmAliveDTORequest iAmAliveDTORequest = buildIAmALivePackageDTO();
			
			syncDTORequest.setiAmAliveDTORequest(iAmAliveDTORequest);			
            final ArrayList<ES_ESD> esListNotSent = ESFileSystemJsonDAO.getEsListNotSent();
            for(ES_ESD es_esd:esListNotSent){
                logger.debug("download:-->> building:\tesListNotSent:es_esd:"+es_esd.getEs().toShrotString());
            }
			syncDTORequest.setEscdList(esListNotSent);
			
			logger.debug("download:-->> building: SyncDTORequest="+syncDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(syncDTORequest);
			
			logger.debug("download:...invoking with:syncDTORequest="+jsonInput);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInput);
			logger.debug("download:...get response");
			long t2=System.currentTimeMillis();
			if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
				enviandoCierreCaja = false;
			}
			if (response.getStatus() != 200) {
				if(response.getStatus() >= 500 && response.getStatus()< 600){
					logger.debug("download:ERROR =======================================>> ENVIAR_POR_EMAIL: ERROR DE DATOS");
				} else if(response.getStatus() >= 400 && response.getStatus()<500){
					logger.debug("download:ERROR =======================================>> ENVIAR_POR_EMAIL: NO HAY SERVIDOR");
				} 
				
				if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
					enviandoCierreCorrectmente = false;
				}
				throw new IOException("Failed HTTP error code :"
						+ response.getStatus());
			}
			if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
				enviandoCierreCorrectmente = true;
			}
			iAmALiveOKEnviadas++;
			int rl=response.getLength();
			long t3=System.currentTimeMillis();
			InputStream is = response.getEntityInputStream();
			OutputStream os = new FileOutputStream(fileName);
			byte buffer[]=new byte[1024];
			int r;
			while((r = is.read(buffer, 0, buffer.length))!= -1){
				os.write(buffer, 0, r);
				os.flush();
			}
			is.close();
			os.close();
			long t4=System.currentTimeMillis();
			File fileZip =  new File(fileName);
			logger.trace("download:  fileZip = "+fileZip.length()+" bytes. == "+rl);						
			logger.trace("download:  T = "+(t4-t0));
			logger.trace("download:+T1 = "+(t1-t0));
			logger.trace("download:+T2 = "+(t2-t1));
			logger.trace("download:+T3 = "+(t3-t2));					
			logger.trace("download:+T4 = "+(t4-t3));			
		} catch (ClientHandlerException e) {
			logger.debug("download:ClientHandlerException Cause:="+e.getCause().getClass());
			if(e.getCause() instanceof ConnectException){
				throw (ConnectException)e.getCause();
			} else {
				throw new IOException("No se puede leer el RestService:"+e.getMessage());
			}
		}
	}

	private static IAmAliveDTORequest buildIAmALivePackageDTO() {
		IAmAliveDTORequest iAmAliveDTOPackage;
		
		iAmAliveDTOPackage = new IAmAliveDTORequest();

		String operatingSystem = System.getProperty("os.name")+"_"+System.getProperty("os.version")+"("+System.getProperty("os.arch")+")";
		
		U ul = ApplicationLogic.getInstance().getLogged();
		if(ul != null) {
			iAmAliveDTOPackage.setLoggedIn(ul.getE());
			//ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(ul.getE());
		} else {
			iAmAliveDTOPackage.setLoggedIn(null);
			//ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(null);
		}
		iAmAliveDTOPackage.setSucursalId(getSucursalId());
		iAmAliveDTOPackage.setCajaId(getNumCaja());
		iAmAliveDTOPackage.setSessionId(getSessionID());
		
		iAmAliveDTOPackage.setUserAgent(
				new UserAgent(
						ApplicationLogic.getInstance().getVersion(),
						operatingSystem,
						System.getProperty("java.version"),
						System.getProperty("user.name"),
						System.getProperty("user.dir")));
		
		CorteCajaDTO lastSavedCC        = ApplicationLogic.getInstance().getLastSavedCC();
		CorteCajaDTO corteCajaDTOEnviar = ApplicationLogic.getInstance().getCorteCajaDTO();
		if(corteCajaDTOEnviar != null) {
			logger.debug("buildIAmALivePackageDTO: A) corteCajaDTOEnviar="+corteCajaDTOEnviar);
			iAmAliveDTOPackage.setCorteCajaDTO(corteCajaDTOEnviar);
		} else {
            logger.debug("buildIAmALivePackageDTO: B) lastSavedCC="+lastSavedCC);
			iAmAliveDTOPackage.setCorteCajaDTO(lastSavedCC);
		}
		
		iAmALiveBuild++;
		return iAmAliveDTOPackage;
	}
	
	private static IAmAliveDTOPackage iAmAliveDTOPackage=null;
	
	private static void iamalive() throws IOException{
		
		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			logger.debug("iamalive:...creating WebResource for IAmAlive");
			WebResource webResource = client.resource(getServerContext()+uriServiceIAmAlive);			
			
			String jsonInput = null;
			IAmAliveDTORequest iAmAliveDTORequest = buildIAmALivePackageDTO();
			logger.trace("iamalive:-->> building: IAmAliveDTORequest="+iAmAliveDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(iAmAliveDTORequest);
			
			logger.trace("iamalive:...invoking with:IAmAliveDTORequest="+jsonInput);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInput);
			logger.trace("iamalive:...get response");
			long t2=System.currentTimeMillis();
			if(enviandoCierreCaja && iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
				enviandoCierreCaja = false;
			}
			if (response.getStatus() != 200) {
				if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
					enviandoCierreCorrectmente = false;
				}
				throw new IOException("Failed HTTP error code :"
						+ response.getStatus());
			} 
			if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
				enviandoCierreCorrectmente = true;
			}
			iAmALiveOKEnviadas++;
			
			logger.trace("iamalive:OK, not error, trying get JSON response");
			byte byteArr[]=new byte[0];
			byte[] output = response.getEntity(byteArr.getClass());
			long t3=System.currentTimeMillis();
			logger.trace("iamalive:Output from Server:output.length="+output.length);
			String content = new String(output);			
			String jsonContent=new String(content);					
			logger.trace("iamalive:jsonContent:"+jsonContent);
			
			if(jsonContent != null) {
				iAmAliveDTOPackage = gson.fromJson(jsonContent, IAmAliveDTOPackage.class);			
				long t4=System.currentTimeMillis();

				logger.trace("iamalive:  T = "+(t4-t0));
				logger.trace("iamalive:+T1 = "+(t1-t0));
				logger.trace("iamalive:+T2 = "+(t2-t1));
				logger.trace("iamalive:+T3 = "+(t3-t2));					
				logger.trace("iamalive:+T4 = "+(t4-t3));
			}
		} catch (ClientHandlerException e) {
			//logger.debug("iamalive:ClientHandlerException Cause:="+e.getCause().getClass());
			if(e.getCause() instanceof ConnectException){
				throw (ConnectException)e.getCause();
			} else {
				throw new IOException("No se puede leer el RestService:"+e.getMessage());
			}
		}
	}
	
	private static int numReadSync=0;
	
	public static void resetAll(){
		File[] filesToDelete = new File(".").listFiles();
		boolean deleteFile = false;
		for(File fd: filesToDelete){
			deleteFile = false;
			logger.info("->resetAll:filesToDelete:"+fd);
			if(fd.getName().contains("CorteCajaDTO")){
				deleteFile = true;
			} else if(fd.getName().contains("fileModel.zip")){
				deleteFile = true;
			} else if(fd.getName().contains("EntradaSalida")){
				deleteFile = true;
			}
			
			if(deleteFile){
				logger.info("->resetAll:\tDELETE:"+fd);
				boolean resultDelte = fd.delete();
				logger.info("->resetAll:\t\tDELETED ?:"+resultDelte);
			}
		}	
	}

	private static void readLocally() {
		logger.debug("readLocally:");
		ZipFile zf=null;
		Gson gson=new Gson();
		String jsonContent=null;
		File fileZip = null;
		boolean deleted=false;
		try {
			fileZip = new File(fileName);
			logger.debug("readLocally:open ZIP:"+fileZip.getAbsolutePath());
			zf = new ZipFile(fileZip);
		} catch(ZipException ze){
			logger.error("readLocally:ZIP corrupto:"+fileZip,ze);
			deleted=fileZip.delete();
			logger.debug("readLocally: 1)Deleteed  ZIP:"+fileZip.getAbsolutePath()+" ? "+deleted);
			return;
		} catch(IOException ioe) {
			logger.error("readLocally: ZIP error al leer:"+fileZip,ioe);
			deleted=fileZip.delete();
			logger.debug("readLocally: 2)Deleteed  ZIP:"+fileZip.getAbsolutePath()+" ? "+deleted);
			return;
		}
		
		try {
			Enumeration<? extends ZipEntry> entries = zf.entries();
			logger.debug("readLocally: OK Reading ZIP");
			while(entries.hasMoreElements()){
				ZipEntry ze = entries.nextElement();

				byte content[]=null;
				byte buffer[] =new byte[1024];
				ByteArrayOutputStream baos= new ByteArrayOutputStream();
				if(ze.getName().endsWith(".json")){
					logger.trace("readLocally:\tReading from:"+ze.getName()+", "+ze.getSize()+" bytes");
					InputStream is = zf.getInputStream(ze);
					int r=0;
					while((r=is.read(buffer))!=-1){
						baos.write(buffer, 0, r);
						baos.flush();
					}
					baos.close();
					is.close();
					logger.trace("readLocally:\tOK read.");
					
					content = baos.toByteArray();
					logger.trace("readLocally:\tcontent.length="+content.length);
					
					jsonContent=new String(content);					
					
					logger.trace("readLocally:\tjsonContent.size="+jsonContent.length());
					//logger.debug("jsonContent="+jsonContent);
				}
			}
			zf.close();
			logger.debug("readLocally:After read zip");
			if(jsonContent != null) {
				logger.debug("readLocally:...OK, JSon parse:");
				paqueteSinc = gson.fromJson(jsonContent, SyncDTOPackage.class);
				numReadSync++;
				logger.debug("readLocally:paqueteSinc["+numReadSync+"]=->"+paqueteSinc+"<-");
				logger.debug("readLocally:paqueteSinc:paqueteSinc.getSyncDBStatus():"+Integer.toBinaryString(paqueteSinc.getSyncDBStatus())+"<-");
				
				if(paqueteSinc.getNextSyncSpecialAction()!=null){
					logger.info("readLocally:paqueteSinc:nextSyncSpecialAction="+paqueteSinc.getNextSyncSpecialAction());
					if(paqueteSinc.getNextSyncSpecialAction() == SyncDTOPackage.SPECIAL_ACTION_RESET_ALL) {
						logger.info("readLocally:paqueteSinc:SPECIAL_ACTION_RESET_ALL");
						if(syncUpdateListener != null){
							syncUpdateListener.resetAll();
						}
					}
				}
				
				String remoteCurrentPMCajaVersion = paqueteSinc.getCurrentPMCajaVersion();
				logger.debug("readLocally:paqueteSinc:paqueteSinc.getCurrentPMCajaVersion="+remoteCurrentPMCajaVersion);
				if(remoteCurrentPMCajaVersion != null){
					int compare = ApplicationLogic.getInstance().compareRemoteVersion(remoteCurrentPMCajaVersion);
					logger.debug("readLocally:paqueteSinc:compare="+compare);
					if(compare < 0){
						Notificacion nuevaNotificacion=new Notificacion("UP_"+remoteCurrentPMCajaVersion,"HAY UNA NUEVA ACTUALIZACION: Ver."+remoteCurrentPMCajaVersion+"\nDEBE REINICIAR POR FAVOR LA APLICACION DE CAJA.");
						ApplicationLogic.getInstance().add(nuevaNotificacion);
						ApplicationLogic.getInstance().needsUpdateAppNow();
					}
				}
				
				if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL) == SyncDTOPackage.SYNC_FAIL) {
					logger.debug("readLocally:paqueteSinc:->SYNC_FAIL:");
					if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_INTEGRITY) == SyncDTOPackage.SYNC_FAIL_INTEGRITY) {
						logger.debug("readLocally:paqueteSinc=->SYNC_FAIL_INTEGRITY:");
					} else if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_JDBC) == SyncDTOPackage.SYNC_FAIL_JDBC) {
						logger.debug("readLocally:paqueteSinc=->SYNC_FAIL_JDBC:");
					}
                    ESFileSystemJsonDAO.operactionSentFailed();
                    ESFileSystemJsonDAO.persist();
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_OK) {
					logger.debug("readLocally:paqueteSinc:->SYNC_OK, all List<es> -> SENT");
					ESFileSystemJsonDAO.setSentAllES();
                    ESFileSystemJsonDAO.persist();
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_EMPTY_TRANSACTION) {
					logger.debug("readLocally:paqueteSinc:->SYNC_EMPTY_TRANSACTION");
				}
				
				List<I> lp = paqueteSinc.getInventarioSucursalList();
				productosParaBuscar = new HashMap<String,I>();
				logger.trace("readLocally:productosParaBuscar, begin");
				long t0=System.currentTimeMillis();
				for(I p: lp){
					productosParaBuscar.put(p.getCb(),p);
				}
				long t=t0-System.currentTimeMillis();
				logger.trace("readLocally:productosParaBuscar, ready T="+t);
				logger.trace("readLocally:paqueteSinc.getSucursal="+paqueteSinc.getSucursal());				
				if(paqueteSinc.getSucursal()!=null){
					logger.trace("readLocally:paqueteSinc.getSucursal.clave="+paqueteSinc.getSucursal().getClave());
				}
				logger.debug("readLocally: --------------- LOADING All Objects in Memory ------------");
				almacenList = paqueteSinc.getAlmacenList();
				usuarioList = paqueteSinc.getUsuarioList();
				logger.trace("readLocally: usuarioList:");
				for(U u:usuarioList){
					logger.trace("\treadLocally: usuarioList: U:"+u.getE()+", perfiles:"+u.getPerfiles()+" a:"+u.getA());
				}
				clienteList = paqueteSinc.getClienteList();
				metodoDePagoList = paqueteSinc.getMetodoDePagoList();
				formaDePagoList = paqueteSinc.getFormaDePagoList();
				if(paqueteSinc.getSucursal() != null){
					sucursal = paqueteSinc.getSucursal();					
				} else {
					sucursal = new Sucursal();
					sucursal.setClave("????");
				}
				logger.trace("readLocally:sucursal="+sucursal);
				logger.trace("readLocally:almacenList="+almacenList);
				ApplicationLogic.getInstance().setAlmacen(almacenList);

				if(syncUpdateListener != null){
					logger.debug("readLocally:->syncUpdateListener.updateSyncInfo()");
					syncUpdateListener.updateSyncInfo();
					
					if(sucursal.getProhibidoVentOpo()!=null && sucursal.getProhibidoVentOpo()==1){
						syncUpdateListener.updateProhibidaVentOpo(false);
					}
					if(sucursal.getProhibidoVentReg()!=null && sucursal.getProhibidoVentReg()==1){
						syncUpdateListener.updateProhibidaVentReg(false);
					}
				}

				logger.debug("readLocally:======================= E N D ======================");
			}
			
		} catch (Exception ex) {
			logger.error("readLocally:Reading ZIP:", ex);
		}
	}

	public static Sucursal getSucursal() {
		return sucursal;
	}
	
	public static String getProperty(String p){
		return properties.getProperty(p);
	}
	
	public static int getSucursalId(){
		return Integer.parseInt(properties.getProperty("sucursal"));
	}

	public static int getNumCaja(){
		return Integer.parseInt(properties.getProperty("caja"));
	}
	
	public static int getTextSystemPrinterColumns(){
		return Integer.parseInt(properties.getProperty("textSystemPrinterColumns"));
	}
	
	public static String getCajaGlobalInfo() {
		if(sucursal!=null){
			return sucursal.getNombre()+" / CAJA #"+properties.getProperty("caja").toUpperCase();
		} else {
			return "["+getSucursalId()+"] / CAJA #"+properties.getProperty("caja").toUpperCase();
		}
	}

	public static I fastSearchProducto(String codigoBuscar) {
		return productosParaBuscar.get(codigoBuscar);
	}

	private static Object getLoggedIn() {
		U logged = ApplicationLogic.getInstance().getLogged();
		if(logged == null){
			return null;
		} else {
			return logged.getE();
		}
	}
	/*	
	public static CorteCajaDTO getAperturaCaja(){
		CorteCajaDTO cc=null;
		File fileToLoad = new File(aperturaCajaDTOjsonFile);
		if(fileToLoad.exists() ){
			logger.debug("getCorteCaja:File found:"+aperturaCajaDTOjsonFile);
			Gson gson=new Gson();
			try {
				FileReader fr = new FileReader(fileToLoad);
				logger.debug("\tReading");
				cc= gson.fromJson(fr, CorteCajaDTO.class);					
			}catch(IOException ioe){
				logger.error("getCorteCaja: fail:",ioe);
			}
		} else {
			logger.debug("getCorteCaja:File NOT found:"+aperturaCajaDTOjsonFile);
			cc= null;
		}
		return cc;
	}
	
	public static void saveAperturaCajaDTO(CorteCajaDTO cc){
		logger.debug("saveAperturaCajaDTO: CorteCajaDTO="+cc);
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(cc);
		FileWriter fw = null;
		try {
			fw = new FileWriter(aperturaCajaDTOjsonFile);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
			
			File x=new File(aperturaCajaDTOjsonFile);
			
			logger.debug("saveAperturaCajaDTO: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
			
		}catch(IOException ioe){
			logger.error("saveAperturaCajaDTO:Error to write",ioe);
		}	
	}
	*/
	public static void saveCorteCajaDTO(CorteCajaDTO cc){
		logger.debug("saveCorteCajaDTO: CorteCajaDTO="+cc);
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(cc);
		FileWriter fw = null;
		try {
			fw = new FileWriter(corteCajaDTOjsonFile);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
			
			File x=new File(corteCajaDTOjsonFile);
			
			logger.debug("saveCorteCajaDTO: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
			
		}catch(IOException ioe){
			logger.error("saveCorteCajaDTO:Error to write",ioe);
		}	
	}
    
	public static void updateCorteCajaDTO(CorteCajaDTO cc){
		logger.debug("updateCorteCajaDTO: CorteCajaDTO="+cc);
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(cc);
		FileWriter fw = null;
		final String backupFaileName = "CorteCajaDTO"+Constants.sdfLogFile.format(new Date(cc.getFecha()))+".json";
		try {
			
			fw = new FileWriter(backupFaileName);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
			
			File x=new File(backupFaileName);
			
			logger.debug("updateCorteCajaDTO: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
		}catch(IOException ioe){
			logger.error("updateCorteCajaDTO:Error to write",ioe);
		}
	}    
    	
	public static void backupCorteCajaDTO(CorteCajaDTO cc){
		logger.debug("backupCorteCajaDTO: CorteCajaDTO="+cc);
		Gson gson=new Gson();
		String jsonAllES = gson.toJson(cc);
		FileWriter fw = null;
		final String backupFaileName = "CorteCajaDTO"+Constants.sdfLogFile.format(new Date(cc.getFecha()))+".json";
		try {
			
			fw = new FileWriter(backupFaileName);
			fw.write(jsonAllES);
			fw.flush();
			fw.close();
			
			File x=new File(backupFaileName);
			
			logger.debug("backupCorteCajaDTO: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
			
		}catch(IOException ioe){
			logger.error("backupCorteCajaDTO:Error to write",ioe);
		}	
	}
	
	public static CorteCajaDTO readLastSavedCorteCajaDTO(){
		logger.debug("readLastSavedCorteCajaDTO: read from :"+corteCajaDTOjsonFile);
		
		Gson gson=new Gson();
		CorteCajaDTO cc = null;
		FileReader fileReader = null;
		try {			
			fileReader =new FileReader(corteCajaDTOjsonFile);		
			cc = gson.fromJson(fileReader, CorteCajaDTO.class);		
			logger.debug("readLastSavedCorteCajaDTO: cc="+cc);
			fileReader.close();
		}catch(IOException ioe){
			logger.debug("readLastSavedCorteCajaDTO: No existe el archivo:"+ioe.getMessage());
		}
		return cc;
	}

	public static void existRequestResetAll() {
		File fileRequestReset = new File("RESET.do");
		if(fileRequestReset.exists()) {
			if(fileRequestReset.delete()) {
				resuqestResetAll();
			}
		}
	}
	
	public static void resuqestResetAll() {
		logger.info("==========================>> RESET <<======================");
		File currDir = new File(".");
		File[] allFiles = currDir.listFiles();
		boolean deleteFile=false;
		for(File f: allFiles){
			deleteFile=false;
			if(f.getName().startsWith("CorteCaja")){
				deleteFile=true;
			} else if(f.getName().startsWith("EntradaSalida")){
				deleteFile=true;
			} else if(f.getName().startsWith("fileModel.zip")){
				deleteFile=true;
			}

			if(deleteFile){					
				f.setWritable(true);
				boolean deleted = f.delete();
				logger.info("resetAction: delete:"+f+" ? "+deleted);
			}
		}
	}
    
    public static class CorteCajaDTOComparatorDescOrder implements Comparator<CorteCajaDTO>{

        @Override
        public int compare(CorteCajaDTO o1, CorteCajaDTO o2) {
            // Orden descendente
            return (int)(o2.getFecha() - o1.getFecha()); 
        }
    
    }
    
    public static class CorteCajaDTOComparatorAscOrder implements Comparator<CorteCajaDTO>{

        @Override
        public int compare(CorteCajaDTO o1, CorteCajaDTO o2) {
            // Orden Ascendente
            return (int)(o1.getFecha() - o2.getFecha()); 
        }
    
    }
    
	public static CorteCajaDTO readLastSavedCorteCajaDTOApertura(){
		logger.debug("readLastSavedCorteCajaDTOApertura: read from :"+corteCajaDTOjsonHistoryFile);
		
		File ff = new File(".");
		
		File[] fcc = ff.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return(name.matches(corteCajaDTOjsonHistoryFile));
			}
		});
		logger.debug("readLastSavedCorteCajaDTOApertura:list:{");
		List<File> fxList = new ArrayList<File>();
        TreeSet<CorteCajaDTO> cortesTS=new TreeSet<CorteCajaDTO>(new CorteCajaDTOComparatorDescOrder());
        
        Gson gson=new Gson();		
		
		for(File fx: fcc){
			//logger.debug("readLastSavedCorteCajaDTOApertura:\t->"+fx.getName());			
            FileReader fileReader = null;
			try {
                CorteCajaDTO cc = null;
				fileReader = new FileReader(fx);
				cc = gson.fromJson(fileReader, CorteCajaDTO.class);
                logger.trace("readLastSavedCorteCajaDTOApertura:\t\t->"+cc);			
                cortesTS.add(cc);
				fileReader.close();
			}catch(Exception ioe){
				logger.debug("readLastSavedCorteCajaDTOApertura: Error al parsear el Archivo:"+ioe.getMessage());
			}
		}
		logger.debug("}");
		
		CorteCajaDTO cCC = null;
		logger.debug("readLastSavedCorteCajaDTOApertura:TreeSet<CorteCajaDTO>:{");
        boolean apeturaCC=false;
        boolean cierreCC =false;
        for(CorteCajaDTO ccX: cortesTS){
            apeturaCC = ccX.getTipoEvento() == Constants.TIPO_EVENTO_APERTURA;
            cierreCC  = ccX.getTipoEvento() == Constants.TIPO_EVENTO_CIERRE;
            if(apeturaCC){
                logger.debug("readLastSavedCorteCajaDTOApertura:\t->ccX: [_] ccX="+ccX);
            } else if(cierreCC){
                logger.debug("readLastSavedCorteCajaDTOApertura:\t->ccX: [X] ccX="+ccX);
            }           
            
            if(apeturaCC && ! cierreCC){
                cCC = ccX;
                break;
            }
        }
        logger.debug("readLastSavedCorteCajaDTOApertura:}");
        logger.debug("readLastSavedCorteCajaDTOApertura:cCC="+cCC);
		return cCC;
	}

	public static boolean isInAperturaCorteCajaDTO(){
		logger.debug("isInAperturaCorteCajaDTO: read from :"+corteCajaDTOjsonHistoryFile);
		
		File ff = new File(".");
		
		File[] fcc = ff.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return(name.matches(corteCajaDTOjsonHistoryFile));
			}
		});
		logger.debug("isInAperturaCorteCajaDTO:list:{");
		List<File> fxList = new ArrayList<File>();
        TreeSet<CorteCajaDTO> cortesTS=new TreeSet<CorteCajaDTO>(new CorteCajaDTOComparatorDescOrder());
        
        Gson gson=new Gson();		
		
		for(File fx: fcc){
			//logger.debug("readLastSavedCorteCajaDTOApertura:\t->"+fx.getName());			
            FileReader fileReader = null;
			try {
                CorteCajaDTO cc = null;
				fileReader = new FileReader(fx);
				cc = gson.fromJson(fileReader, CorteCajaDTO.class);
                logger.trace("isInAperturaCorteCajaDTO:\t\t->"+cc);			
                cortesTS.add(cc);
				fileReader.close();
			}catch(Exception ioe){
				logger.debug("isInAperturaCorteCajaDTO: Error al parsear el Archivo:"+ioe.getMessage());
			}
		}
		logger.debug("}");
		
		CorteCajaDTO cCC = null;
		
        boolean apeturaCC=false;
        for(CorteCajaDTO ccX: cortesTS){
            apeturaCC = ccX.getTipoEvento() == Constants.TIPO_EVENTO_APERTURA;
            if(ccX.getTipoEvento() < Constants.TIPO_EVENTO_APERTURA){
                
            }else if(ccX.getTipoEvento() == Constants.TIPO_EVENTO_APERTURA){
				apeturaCC = true;
                break;
            }else if(ccX.getTipoEvento() > Constants.TIPO_EVENTO_APERTURA){
                break;
            }
            
        }
 		return apeturaCC;
	}
	
   	public static CorteCajaDTO readLastSavedCorteCajaDTOCierre(){
		logger.debug("readLastSavedCorteCajaDTOCierre: read from :"+corteCajaDTOjsonHistoryFile);
		
		File ff = new File(".");
		
		File[] fcc = ff.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return(name.matches(corteCajaDTOjsonHistoryFile));
			}
		});
		logger.debug("readLastSavedCorteCajaDTOCierre:list:{");
		List<File> fxList = new ArrayList<File>();
        TreeSet<CorteCajaDTO> cortesTS=new TreeSet<CorteCajaDTO>(new CorteCajaDTOComparatorDescOrder());
        
        Gson gson=new Gson();		
		
		for(File fx: fcc){
			//logger.debug("readLastSavedCorteCajaDTOApertura:\t->"+fx.getName());			
			FileReader fileReader = null;
            try {
                CorteCajaDTO cc = null;
				fileReader = new FileReader(fx);
				cc = gson.fromJson(fileReader, CorteCajaDTO.class);
                logger.trace("readLastSavedCorteCajaDTOCierre:\t\t->"+cc);
                cortesTS.add(cc);
				fileReader.close();
			}catch(Exception ioe){
				logger.debug("readLastSavedCorteCajaDTOCierre: Error al parsear el Archivo:"+ioe.getMessage());
			}
		}
		logger.debug("}");
		
		CorteCajaDTO cCC = null;
		logger.debug("readLastSavedCorteCajaDTOCierre:TreeSet<CorteCajaDTO>:{");
        boolean cierreCC =false;
        for(CorteCajaDTO ccX: cortesTS){
            cierreCC  = ccX.getTipoEvento() == Constants.TIPO_EVENTO_CIERRE;
            if(cierreCC){
                logger.debug("readLastSavedCorteCajaDTOCierre:\t->ccX: [X] ccX="+ccX);
                cCC = ccX;
                break;
            }
        }
        logger.debug("readLastSavedCorteCajaDTOCierre:}");
        logger.debug("readLastSavedCorteCajaDTOCierre:cCC="+cCC);
		return cCC;
	}

   	public static CorteCajaDTO readFirstSavedCorteCajaDTOIniciada(){
		logger.debug("readFirstSavedCorteCajaDTOIniciada: read from :"+corteCajaDTOjsonHistoryFile);
		
		File ff = new File(".");
		
		File[] fcc = ff.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return(name.matches(corteCajaDTOjsonHistoryFile));
			}
		});
		//logger.debug("readFirstSavedCorteCajaDTOIniciada:list:{");
		List<File> fxList = new ArrayList<File>();
        TreeSet<CorteCajaDTO> cortesTS=new TreeSet<CorteCajaDTO>(new CorteCajaDTOComparatorAscOrder());
        
        Gson gson=new Gson();		
		
		for(File fx: fcc){
			//logger.debug("readFirstSavedCorteCajaDTOIniciada:\t->"+fx.getName());			
			FileReader fileReader = null;
            try {
                CorteCajaDTO cc = null;
				fileReader = new FileReader(fx);
				cc = gson.fromJson(fileReader, CorteCajaDTO.class);
                //logger.debug("readFirstSavedCorteCajaDTOIniciada:\t\t->"+cc);			
                cortesTS.add(cc);
				fileReader.close();
			}catch(Exception ioe){
				logger.debug("readLastSavedCorteCajaDTOApertura: Error al parsear el Archivo:"+ioe.getMessage());
			}
		}
		//logger.debug("}");
		
		CorteCajaDTO cCC = null;
		logger.debug("readFirstSavedCorteCajaDTOIniciada:TreeSet<CorteCajaDTO>:{");
        Date    hoyInicioDeDia = null;
        
        try{
            hoyInicioDeDia = Constants.sdfDate.parse(Constants.sdfDate.format(new Date()));            
        }catch(ParseException pe){
            hoyInicioDeDia = new Date();
        }
        
        for(CorteCajaDTO ccX: cortesTS){
            if(ccX.getFecha()>=hoyInicioDeDia.getTime() && ccX.getTipoEvento() == Constants.TIPO_EVENTO_AP_INICIADA){            
                logger.debug("readFirstSavedCorteCajaDTOIniciada:\t->ccX: [ ] ccX="+ccX);
                cCC = ccX;
                break;
            }
        }
        logger.debug("readFirstSavedCorteCajaDTOIniciada:}");
        logger.debug("readFirstSavedCorteCajaDTOIniciada:cCC="+cCC);
		return cCC;
	}
    
   	public static TreeSet<CorteCajaDTO> readHistoricoCorteCaja(){
		logger.debug("readHistoricoCorteCaja");
		
		File ff = new File(".");
		
		File[] fcc = ff.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return(name.matches(corteCajaDTOjsonHistoryFile));
			}
		});
		//logger.debug("readFirstSavedCorteCajaDTOIniciada:list:{");
		List<File> fxList = new ArrayList<File>();
        TreeSet<CorteCajaDTO> cortesTS=new TreeSet<CorteCajaDTO>(new CorteCajaDTOComparatorDescOrder());
        
        Gson gson=new Gson();		
		
		for(File fx: fcc){
			//logger.debug("readFirstSavedCorteCajaDTOIniciada:\t->"+fx.getName());			
            try {
                CorteCajaDTO cc = null;
				cc = gson.fromJson(new FileReader(fx), CorteCajaDTO.class);
                //logger.debug("readFirstSavedCorteCajaDTOIniciada:\t\t->"+cc);			
                cortesTS.add(cc);
			}catch(Exception ioe){
				logger.debug("readHistoricoCorteCaja: Error al parsear el Archivo:"+ioe.getMessage());
			}
		}
		return cortesTS;
	}


	public static double getRemoteSaldoEstimado() throws IOException{
		double saldoEstimado = 0.0;
		
		URL url = null;
		InputStream is=null;
		
		try {
			url = new URL(getServerContext()+uriSaldoEstimado+"/"+getSucursalId()+"/"+getNumCaja());
			logger.debug("getRemoteSaldoEstimado: url="+url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();			
			conn.setConnectTimeout(URL_CONNECTION_TIMEOUT);			
			conn.setReadTimeout(URL_CONNECTION_TIMEOUT);			
			is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			saldoEstimado = Double.parseDouble(br.readLine());
			logger.debug("getRemoteSaldoEstimado: saldoEstimado="+saldoEstimado);
			is.close();
		}catch(Exception e){
			logger.error("getRemoteSaldoEstimado: error", e);
			throw new IOException("No se puede Obtener Remotamente el saldo:"+e.getMessage());
		}
		return saldoEstimado;
	}

	public static ES_ESD getTicket(String ticket) throws IOException{
		ES_ESD es_esd =  null;
		
		URL url = null;
		InputStream is=null;
		Gson gson=new Gson();
		try {
			url = new URL(getServerContext()+uriBuscarTicket+"/"+ticket);
			logger.debug("getTicket: url="+url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();			
			conn.setConnectTimeout(URL_CONNECTION_TIMEOUT);			
			conn.setReadTimeout(URL_CONNECTION_TIMEOUT);			
			is = conn.getInputStream();
			
			ByteArrayOutputStream baos= new ByteArrayOutputStream();
			int r=0;
			byte[] buffer=new byte[1024];
			while((r=is.read(buffer))!=-1){
				baos.write(buffer, 0, r);
				baos.flush();
			}
			baos.close();
			is.close();
			logger.debug("getTicket:\tOK read.");

			byte[]content = baos.toByteArray();
			logger.debug("getTicket:\tcontent.length="+content.length);

			String jsonContent=new String(content);					

			logger.debug("getTicket:\tjsonContent.size="+jsonContent.length()+": jsonContent="+jsonContent);
			
			es_esd = gson.fromJson(jsonContent, ES_ESD.class);
			
			is.close();
		}catch(Exception e){
			logger.error("getTicket: error", e);
			throw new IOException("No se puede Obtener Remotamente el Ticket:"+e.getMessage());
		}
		return es_esd;
	}
	
	
	public static void iniciaEnvioCierreCaja() {
		enviandoCierreCaja = true;
		enviandoCierreCorrectmente = false;
	}

	public static boolean isEnviandoCierreCaja() {
		return enviandoCierreCaja;
	}

	public static boolean isEnviandoCierreCorrectmente() {
		return enviandoCierreCorrectmente;
	}

	public static U getUsuario(String e) {
		final List<U> ul = getPaqueteSinc().getUsuarioList();
		for(U u:ul){
			if(u.getE().equalsIgnoreCase(e)){
				return u;
			}
		}
		return null;
	}
	
	public static Cliente getCliente(int clienteId) {
		final List<Cliente> cl = getPaqueteSinc().getClienteList();
		for(Cliente c:cl){
			if(c.getId() == clienteId){
				return c;
			}
		}
		return null;
	}

	public static FormaDePago getFormaDePago(int formaDePagoId) {
		final List<FormaDePago> fpL = getPaqueteSinc().getFormaDePagoList();
		for(FormaDePago fp:fpL){
			if(fp.getId() == formaDePagoId){
				return fp;
			}
		}
		return null;
	}
	
	public static MetodoDePago getMetodoDePago(int metodoDePagoId) {
		final List<MetodoDePago> mpL = getPaqueteSinc().getMetodoDePagoList();
		for(MetodoDePago mp:mpL){
			if(mp.getId() == metodoDePagoId){
				return mp;
			}
		}
		return null;
	}
	
	public static void stopPooling(){
		runnigPool = false;
	}
}
