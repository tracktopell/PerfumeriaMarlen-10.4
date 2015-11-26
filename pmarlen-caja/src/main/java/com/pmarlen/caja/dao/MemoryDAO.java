package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.control.FramePrincipalControl;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
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

	private static final String uriServiceIAmAlive =               "/rest/iamaliveservice/hello";
	
	private static final String uriServiceZipSync =                "/rest/syncservice/sync";
	
	private static final String uriSaldoEstimado =                "/rest/syncservice/saldoEstimado";
	
	private static boolean enviandoCierreCaja = false;
	private static boolean enviandoCierreCorrectmente = false;
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
	private final static int  DOWNLOADPERIOD_SECS = 30;
	private final static int  IMALIVEPERIOD_SECS  = 10;
	
	public static void getPaqueteSyncPoll() {
		
		logger.debug("getPaqueteSyncPoll:");
		ESFileSystemJsonDAO.laod();
		logger.debug("getPaqueteSyncPoll:----------------->>BEFORE while ");
		syncPollState = SYNC_STATE_BEFORE_RUNNING;
		while(runnigPool){
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

			}
			
			try {
				logger.trace("getPaqueteSyncPoll:\t----------------->> while running, sleep for "+TIMESLEEP_MS+" ms .., syncPollState="+syncPollState);
				Thread.sleep(TIMESLEEP_MS);
			}catch(Exception e){
				logger.error("getPaqueteSyncPoll:->sleep ? ",e);
			}
			
			xt++;
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
				getPaqueteSyncPoll();
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
			syncDTORequest.setEscdList(ESFileSystemJsonDAO.getEsList());
			
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
/*
{
			java.runtime.name=Java(TM) SE Runtime Environment, 
			sun.boot.library.path=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib, java.vm.version=24.51-b03, 
			user.country.format=MX, 
			gopherProxySet=false, 
			java.vm.vendor=Oracle Corporation, 
			java.vendor.url=http://java.oracle.com/, path.separator=:, 
			java.vm.name=Java HotSpot(TM) 64-Bit Server VM, file.encoding.pkg=sun.io, user.country=ES, 
			sun.java.launcher=SUN_STANDARD, sun.os.patch.level=unknown, java.vm.specification.name=Java Virtual Machine Specification, 
			user.dir=/Users/alfredo/tmp/pmarlen-caja, java.runtime.version=1.7.0_51-b13, 
			java.awt.graphicsenv=sun.awt.CGraphicsEnvironment, java.endorsed.dirs=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/endorsed, 
			os.arch=x86_64, java.io.tmpdir=/var/folders/r0/x48m0drs5jjb972jf25km_pw0000gp/T/, 
			line.separator=, 
			java.vm.specification.vendor=Oracle Corporation, os.name=Mac OS X, 
			sun.jnu.encoding=UTF-8, 
			java.library.path=/Users/alfredo/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:., 
			sun.awt.enableExtraMouseButtons=true, java.specification.name=Java Platform API Specification, 
			java.class.version=51.0, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, 
			os.version=10.9.5, http.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, 
			user.home=/Users/alfredo, user.timezone=America/Mexico_City, 
			java.awt.printerjob=sun.lwawt.macosx.CPrinterJob, file.encoding=UTF-8, java.specification.version=1.7, 
			java.class.path=pmarlen-caja.jar, user.name=alfredo, java.vm.specification.version=1.7, 
			sun.java.command=pmarlen-caja.jar, java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre, 
			sun.arch.data.model=64, user.language=es, 
			java.specification.vendor=Oracle Corporation, 
			awt.toolkit=sun.lwawt.macosx.LWCToolkit, 
			java.vm.info=mixed mode, java.version=1.7.0_51, 
			java.ext.dirs=/Users/alfredo/Library/Java/Extensions:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/ext:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java, 
			sun.boot.class.path=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/sunrsasign.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/classes, 
			java.vendor=Oracle Corporation, 
			file.separator=/, 
			java.vendor.url.bug=http://bugreport.sun.com/bugreport/, 
			sun.font.fontmanager=sun.font.CFontManager, 
			sun.io.unicode.encoding=UnicodeBig, 
			sun.cpu.endian=little, 
			socksNonProxyHosts=local|*.local|169.254/16|*.169.254/16, 
			ftp.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, 
			sun.cpu.isalist=}			
			
			
			*/
		
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
		
		CorteCajaDTO lastSavedCC = ApplicationLogic.getInstance().getLastSavedCC();
		CorteCajaDTO corteCajaDTOEnviar = ApplicationLogic.getInstance().getCorteCajaDTO();
		if(lastSavedCC != null && lastSavedCC.getTipoEvento()==Constants.TIPO_EVENTO_CIERRE && iAmALiveOKEnviadas==0) {
			logger.debug("buildIAmALivePackageDTO:Forzando Evnio de CIERRE DE CAJA");
			iAmAliveDTOPackage.setCorteCajaDTO(lastSavedCC);
		} else {
			iAmAliveDTOPackage.setCorteCajaDTO(corteCajaDTOEnviar);
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
			if(iAmAliveDTORequest.getCorteCajaDTO().getTipoEvento() == Constants.TIPO_EVENTO_CIERRE) {
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
				logger.trace("readLocally:...OK, JSon parse:");
				paqueteSinc = gson.fromJson(jsonContent, SyncDTOPackage.class);			
				logger.trace("readLocally:paqueteSinc=->"+paqueteSinc+"<-");
				logger.trace("readLocally:paqueteSinc=->paqueteSinc.getSyncDBStatus():"+Integer.toBinaryString(paqueteSinc.getSyncDBStatus())+"<-");
				
				if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL) == SyncDTOPackage.SYNC_FAIL) {
					logger.debug("readLocally:paqueteSinc:->SYNC_FAIL:");
					if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_INTEGRITY) == SyncDTOPackage.SYNC_FAIL_INTEGRITY) {
						logger.debug("readLocally:paqueteSinc=->SYNC_FAIL_INTEGRITY:");
					} else if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_JDBC) == SyncDTOPackage.SYNC_FAIL_JDBC) {
						logger.debug("readLocally:paqueteSinc=->SYNC_FAIL_JDBC:");
					}
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_OK) {
					logger.trace("readLocally:paqueteSinc:->SYNC_OK");
					ESFileSystemJsonDAO.reset();								
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_EMPTY_TRANSACTION) {
					logger.trace("readLocally:paqueteSinc:->SYNC_EMPTY_TRANSACTION");
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
			return sucursal.getNombre()+" C#"+properties.getProperty("caja").toUpperCase();
		} else {
			return "["+getSucursalId()+"] C#"+properties.getProperty("caja").toUpperCase();
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
	
	private static String userAgentExpression;
	private static final String corteCajaDTOjsonFile = "CorteCajaDTO.json";
	
	public static CorteCajaDTO _getCorteCaja(){
		CorteCajaDTO cc=null;
		File fileToLoad = new File(corteCajaDTOjsonFile);
		if(fileToLoad.exists() ){
			logger.debug("getCorteCaja:File found:"+corteCajaDTOjsonFile);
			Gson gson=new Gson();
			try {
				FileReader fr = new FileReader(fileToLoad);
				logger.debug("\tReading");
				cc= gson.fromJson(fr, CorteCajaDTO.class);					
			}catch(IOException ioe){
				logger.error("getCorteCaja: fail:",ioe);
			}
		} else {
			logger.debug("getCorteCaja:File NOT found:"+corteCajaDTOjsonFile);
			cc= new CorteCajaDTO();
		}
		return cc;
	}
	
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
			
			File x=new File(corteCajaDTOjsonFile);
			
			logger.debug("backupCorteCajaDTO: created file="+x.getAbsolutePath()+" ? "+x.exists()+", size="+x.length());
			
		}catch(IOException ioe){
			logger.error("backupCorteCajaDTO:Error to write",ioe);
		}	
	}
	
	public static CorteCajaDTO readLastSavedCorteCajaDTO(){
		logger.debug("readLastSavedCorteCajaDTO: ");
		final String backupFaileName = "CorteCajaDTO.json";
		Gson gson=new Gson();
		CorteCajaDTO cc = null;
		
		try {			
			cc = gson.fromJson(new FileReader(backupFaileName), CorteCajaDTO.class);		
			logger.debug("readLastSavedCorteCajaDTO: cc="+cc);
		}catch(IOException ioe){
			logger.debug("readLastSavedCorteCajaDTO: No existe el archivo:"+ioe.getMessage());
		}
		return cc;
	}

	public static double getSaldoEstimado() throws IOException{
		double saldoEstimado = 0.0;
		
		URL url = null;
		InputStream is=null;
		
		try {
			url = new URL(getServerContext()+uriSaldoEstimado+"/"+getSucursalId()+"/"+getNumCaja());
			logger.debug("getSaldoEstimado: url::"+url);
			is = url.openConnection().getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			saldoEstimado = Double.parseDouble(br.readLine());
			logger.debug("getSaldoEstimado: saldoEstimado="+saldoEstimado);
			is.close();
		}catch(Exception e){
			logger.error("getSaldoEstimado: error", e);
			throw new IOException("No se puede Obtener el saldo:"+e.getMessage());
		}
		return saldoEstimado;
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
	
}
