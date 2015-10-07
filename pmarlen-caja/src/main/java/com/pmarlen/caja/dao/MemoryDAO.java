package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.model.Sucursal;
import com.pmarlen.rest.dto.I;
import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.pmarlen.rest.dto.U;
import com.pmarlen.rest.dto.UserAgent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
		
	static {
		properties.put("sucursal","1");
		properties.put("caja","1");
		properties.put("host","localhost");
		properties.put("port","8070");
		properties.put("context","/l30");
		
		properties.put("dropboxdir",System.getProperty("user.home")+"/DropBox/");
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
		if(paqueteSinc ==null ){
			try {
				if(!exsistPackage()){
					download();
				}
				readLocally();
			}catch(IOException ioe){
				logger.error("downoload",ioe);
			}
		}
		return paqueteSinc;
	}
	private static boolean runnigPool = true;
	private static int     syncPollState = 0;
	private static long    xt = 0;
	
	public final static int SYNC_STATE_BEFORE_RUNNING        = 0;
	public final static int SYNC_STATE_BEFORE_CONNECTING     = 1;
	public final static int SYNC_STATE_BEFORE_DOWNLOADED     = 2;
	public final static int SYNC_STATE_READ                  = 3;
	public final static int SYNC_STATE_BEFORE_CONNECTINGLIVE = 4;
	public final static int SYNC_STATE_IMLIVE                = 5;
	public final static int SYNC_STATE_ERROR_URL             = 6;
	public final static int SYNC_STATE_ERROR                 = 10;
	
	private static long timeSleep = 15000L;
	
	public static void getPaqueteSyncPoll(){
		
		logger.debug("getPaqueteSyncPoll:");
		ESFileSystemJsonDAO.laod();
		logger.debug("----------------->>BEFORE while ");
		syncPollState = SYNC_STATE_BEFORE_RUNNING;
		while(runnigPool){
			logger.debug("\t----------------->> while running, download");
			if(xt%6==0){
				try {
					syncPollState = SYNC_STATE_BEFORE_CONNECTING;
					download();
					syncPollState = SYNC_STATE_BEFORE_DOWNLOADED;
					logger.debug("\t----------------->> OK, downloded, read locally");
					readLocally();
					syncPollState = SYNC_STATE_READ;
				}catch(MalformedURLException e){
					logger.error("URL: downoload",e);
					syncPollState = SYNC_STATE_ERROR_URL;
					break;
				}catch(Exception e){
					logger.error("X: downoload",e);
					syncPollState = SYNC_STATE_ERROR;
				}

			} else {
				try {					
					syncPollState = SYNC_STATE_BEFORE_CONNECTINGLIVE;
					iamalive();
					syncPollState = SYNC_STATE_IMLIVE;
				}catch(MalformedURLException e){
					logger.error("URL: imlive",e);
					syncPollState = SYNC_STATE_ERROR_URL;
					break;
				}catch(Exception e){
					logger.error("X: exception",e);
					syncPollState = SYNC_STATE_ERROR;
				}

			}
			
			try {
				logger.debug("\t----------------->> while running, sleep for "+timeSleep+" ms .....");
				Thread.sleep(timeSleep);
			}catch(Exception e){
				logger.error("downoload",e);
			}
			
			xt++;
		}
	}

	public static int getSyncPollState() {
		return syncPollState;
	}
	
	public static void startPaqueteSyncService(){
		logger.debug("----------------->> startPaqueteSyncService");
		new Thread(){
			@Override
			public void run() {
				getPaqueteSyncPoll();
			}
		}.start();
	}

	private static boolean exsistPackage() {
		File modelFile = null;
		modelFile = new File(fileName);
		if (modelFile.canRead() && modelFile.isFile() && modelFile.length() > 1024) {
			return true;
		}
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
			logger.debug("...creating WebResource for ZIP");
			WebResource webResource = client.resource(getServerContext()+uriServiceZipSync);
			
			
			String jsonInput = null;
			SyncDTORequest syncDTORequest = new SyncDTORequest();
			
			IAmAliveDTORequest iAmAliveDTORequest = buildIAmALivePackageDTO();
			
			syncDTORequest.setiAmAliveDTORequest(iAmAliveDTORequest);			
			syncDTORequest.setEscdList(ESFileSystemJsonDAO.getEsList());
			
			logger.debug("-->> building: SyncDTORequest="+syncDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(syncDTORequest);
			
			logger.debug("...invoking with:syncDTORequest="+jsonInput);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInput);
			logger.debug("...get response");
			long t2=System.currentTimeMillis();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed HTTP error code :"
						+ response.getStatus());
			}
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
			logger.debug("  fileZip = "+fileZip.length()+" bytes. == "+rl);						
			logger.debug("  T = "+(t4-t0));
			logger.debug("+T1 = "+(t1-t0));
			logger.debug("+T2 = "+(t2-t1));
			logger.debug("+T3 = "+(t3-t2));					
			logger.debug("+T4 = "+(t4-t3));			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			return;
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
		iAmAliveDTOPackage.setCajaId(1);
		U ul = ApplicationLogic.getInstance().getLogged();
		if(ul != null) {
			iAmAliveDTOPackage.setLoggedIn(ul.getE());
		} else {
			iAmAliveDTOPackage.setLoggedIn(null);
		}
		iAmAliveDTOPackage.setSessionId(getSessionID());
		iAmAliveDTOPackage.setSucursalId(getSucursalId());
		iAmAliveDTOPackage.setUserAgent(
				new UserAgent(
						ApplicationLogic.getInstance().getVersion(),
						operatingSystem,
						System.getProperty("java.version"),
						System.getProperty("user.name"),
						System.getProperty("user.dir")));
		return iAmAliveDTOPackage;
	}
	
	private static IAmAliveDTOPackage iAmAliveDTOPackage=null;
	
	private static void iamalive() throws IOException{		
		
		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			logger.debug("...creating WebResource for IAmAlive");
			WebResource webResource = client.resource(getServerContext()+uriServiceIAmAlive);			
			
			String jsonInput = null;
			IAmAliveDTORequest iAmAliveDTORequest = buildIAmALivePackageDTO();
			logger.debug("-->> building: IAmAliveDTORequest="+iAmAliveDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(iAmAliveDTORequest);
			
			logger.debug("...invoking with:IAmAliveDTORequest="+jsonInput);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInput);
			logger.debug("...get response");
			long t2=System.currentTimeMillis();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed HTTP error code :"
						+ response.getStatus());
			}
			logger.debug("OK, not error, trying get JSON response");
			byte byteArr[]=new byte[0];
			byte[] output = response.getEntity(byteArr.getClass());
			long t3=System.currentTimeMillis();
			logger.debug("Output from Server:output.length="+output.length);
			String content = new String(output);			
			String jsonContent=new String(content);					
			logger.debug("jsonContent:"+jsonContent);
			
			if(jsonContent != null) {
				iAmAliveDTOPackage = gson.fromJson(jsonContent, IAmAliveDTOPackage.class);			
				long t4=System.currentTimeMillis();

				logger.debug("  T = "+(t4-t0));
				logger.debug("+T1 = "+(t1-t0));
				logger.debug("+T2 = "+(t2-t1));
				logger.debug("+T3 = "+(t3-t2));					
				logger.debug("+T4 = "+(t4-t3));
			}
		} catch (Exception e) {
			logger.error("iamalive: Exception",e);
		} finally {
			return ;
		}
	}

	private static void readLocally() {
		ZipFile zf=null;
		Gson gson=new Gson();
		String jsonContent=null;
		File fileZip = null;
		boolean deleted=false;
		try {
			fileZip = new File(fileName);
			logger.debug("open ZIP:"+fileZip.getAbsolutePath());
			zf = new ZipFile(fileZip);
		} catch(ZipException ze){
			logger.error("ZIP corrupto:"+fileZip,ze);
			deleted=fileZip.delete();
			logger.debug("1)Deleteed  ZIP:"+fileZip.getAbsolutePath()+" ? "+deleted);
			return;
		} catch(IOException ioe) {
			logger.error("ZIP error al leer:"+fileZip,ioe);
			deleted=fileZip.delete();
			logger.debug("2)Deleteed  ZIP:"+fileZip.getAbsolutePath()+" ? "+deleted);
			return;
		}
		
		try {
			Enumeration<? extends ZipEntry> entries = zf.entries();

			while(entries.hasMoreElements()){
				ZipEntry ze = entries.nextElement();

				byte content[]=null;
				byte buffer[] =new byte[1024];
				ByteArrayOutputStream baos= new ByteArrayOutputStream();
				if(ze.getName().endsWith(".json")){
					logger.debug("Reading from:"+ze.getName()+", "+ze.getSize()+" bytes");
					InputStream is = zf.getInputStream(ze);
					int r=0;
					while((r=is.read(buffer))!=-1){
						baos.write(buffer, 0, r);
						baos.flush();
					}
					baos.close();
					is.close();
					logger.debug("OK read.");
					
					content = baos.toByteArray();
					logger.debug("content.length="+content.length);
					
					jsonContent=new String(content);					
					
					logger.debug("jsonContent.size="+jsonContent.length());
					//logger.debug("jsonContent="+jsonContent);
				}
			}
			zf.close();
			logger.debug("After read zip");
			if(jsonContent != null) {
				logger.debug("...OK, JSon parse:");
				paqueteSinc = gson.fromJson(jsonContent, SyncDTOPackage.class);			
				logger.debug("paqueteSinc:->"+paqueteSinc+"<-");
				logger.debug("paqueteSinc:->paqueteSinc.getSyncDBStatus():"+Integer.toBinaryString(paqueteSinc.getSyncDBStatus())+"<-");
				
				if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL) == SyncDTOPackage.SYNC_FAIL) {
					logger.debug("paqueteSinc:->SYNC_FAIL:");
					if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_INTEGRITY) == SyncDTOPackage.SYNC_FAIL_INTEGRITY) {
						logger.debug("paqueteSinc:->SYNC_FAIL_INTEGRITY:");
					} else if( (paqueteSinc.getSyncDBStatus() & SyncDTOPackage.SYNC_FAIL_JDBC) == SyncDTOPackage.SYNC_FAIL_JDBC) {
						logger.debug("paqueteSinc:->SYNC_FAIL_JDBC:");
					}
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_OK) {
					logger.debug("paqueteSinc:->SYNC_OK");
					ESFileSystemJsonDAO.reset();								
				} else if(paqueteSinc.getSyncDBStatus() == SyncDTOPackage.SYNC_EMPTY_TRANSACTION) {
					logger.debug("paqueteSinc:->SYNC_EMPTY_TRANSACTION");
				}
				
				List<I> lp = paqueteSinc.getInventarioSucursalList();
				productosParaBuscar = new HashMap<String,I>();
				logger.debug("productosParaBuscar, begin");
				long t0=System.currentTimeMillis();
				for(I p: lp){
					productosParaBuscar.put(p.getCb(),p);
				}
				long t=t0-System.currentTimeMillis();
				logger.debug("productosParaBuscar, ready T="+t);
				
				almacenList = paqueteSinc.getAlmacenList();
				usuarioList = paqueteSinc.getUsuarioList();
				clienteList = paqueteSinc.getClienteList();
				metodoDePagoList = paqueteSinc.getMetodoDePagoList();
				formaDePagoList = paqueteSinc.getFormaDePagoList();
				sucursal = new Sucursal();
				sucursal.setId(paqueteSinc.getSucursal().getId());
				sucursal.setNombre(paqueteSinc.getSucursal().getNombre().toUpperCase());				
				
				ApplicationLogic.getInstance().setTipoAlmacen(almacenList);
			}
			
		} catch (Exception ex) {
			logger.error("Reading ZIP:", ex);
		}

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
	
	public static String getCajaGlobalInfo() {
		return "pms"+properties.getProperty("sucursal")+"c"+properties.getProperty("caja");
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
}
