/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.dao;

import com.google.gson.Gson;
import com.pmarlen.backend.model.quickviews.SyncDTOPackage;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.control.FramePrincipalControl;
import com.pmarlen.rest.dto.P;
import com.pmarlen.rest.dto.U;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.log4j.Logger;
/**
 *
 * @author alfredo
 */
public class MemoryDAO {
	private static Properties properties = new Properties();
	
	static {
		//properties.put("host","pmarlencloudsrv1.dyndns.org");
		//properties.put("host","192.168.1.70");
		properties.put("sucursal","0");
		properties.put("caja","1");
		properties.put("host","localhost");
		properties.put("port","8070");
		properties.put("context","/pmarlen-web-ligth");
		properties.put("dropboxdir",System.getProperty("user.home")+"/DropBox/");
	}
	
	private static SyncDTOPackage paqueteSinc;
	private static Logger logger = Logger.getLogger(MemoryDAO.class.getName());
	private static String fileName = "./fileModel.zip";
	//static String hostDownload = "localhost:8070"; //"pmarlencloudsrv2.dyndns.org:8070";
	private static String urlDownload = null;//"http://"+hostDownload+"/pmarlen-web-ligth/sync/data?sucursalId=1&format=zip";
	
	private static HashMap<String,P> productosParaBuscar;
	private static String propertiesFileNAme="./system.properties";
	private static boolean exsistFile = false;	
	
	public static void loadProperties() {
		
		File fileProperties = new File(propertiesFileNAme);
		
		if(fileProperties.exists() && fileProperties.canRead()){
			try {
				logger.info("->Properties local File found, reading File Properties:"+fileProperties+"");
				properties.load(new FileInputStream(fileProperties));
				logger.info("->ok, Properties read:"+properties);
				exsistFile = true;
			}catch(IOException ioe){				
				logger.error( "Can`t read File for properties", ioe);
			}
		}
	}

	public static void persistPRoperties() {
		try {
			logger.info("->Properties Doesn't exsist, wrinting File Properties.");
			properties.store(new FileOutputStream(propertiesFileNAme), "Persisted");
			logger.info("->ok, writing.");
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
	
	
	public static void getPaqueteSyncPoll(){
		logger.info("----------------->>BEFORE while ");
		syncPollState = SYNC_STATE_BEFORE_RUNNING;
		while(runnigPool){
			logger.info("\t----------------->> while running, download");
			if(xt%6==0){
				try {
					syncPollState = SYNC_STATE_BEFORE_CONNECTING;
					download();
					syncPollState = SYNC_STATE_BEFORE_DOWNLOADED;
					logger.info("\t----------------->> OK, downloded, read locally");
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
				logger.info("\t----------------->> while running, sleep,.....");
				Thread.sleep(10000L);
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
		logger.info("----------------->> startPaqueteSyncService");
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
		URL url = null;

		StringBuilder sbURL = new StringBuilder("http://");
		sbURL.append(properties.get("host")).
				append(":").
				append(properties.get("port")).
				append(properties.get("context")).
				append("/sync/data?format=zip&sucursalId=").
				append(properties.get("sucursal")).
				append("&caja=").
				append(properties.get("caja")).
				append("&sessionId=").
				append(getSessionID()).
				append("&loggedIn=").
				append(getLoggedIn());
		
		url = new URL(sbURL.toString());
		logger.info(">> Downloading url:"+url);
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestProperty("User-Agent",getUserAgentExpression());
		connection.setConnectTimeout(2000);
		connection.setRequestMethod("GET");		
		connection.connect();
		
		InputStream is = connection.getInputStream();
		FileOutputStream fos = new FileOutputStream(fileName);

		byte buffer[] = new byte[1024];
		int r;
		logger.info(">> reading");
		while ((r = is.read(buffer)) != -1) {
			fos.write(buffer, 0, r);
			fos.flush();
		}
		fos.close();
		is.close();
		logger.info(">> saving");
	}
	
	private static void iamalive() throws IOException{
		URL url = null;

		StringBuilder sbURL = new StringBuilder("http://");
		sbURL.append(properties.get("host")).
				append(":").
				append(properties.get("port")).
				append(properties.get("context")).
				append("/sync/data?format=iamalive&sucursalId=").
				append(properties.get("sucursal")).
				append("&caja=").
				append(properties.get("caja")).
				append("&sessionId=").
				append(getSessionID()).
				append("&loggedIn=").
				append(getLoggedIn());
		
		url = new URL(sbURL.toString());
		logger.info(">> I'm Alive URL:"+url);
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		connection.setRequestProperty("User-Agent",getUserAgentExpression());
		connection.setConnectTimeout(2000);
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();

		logger.info(">> code:"+code);
	}

	private static void readLocally() {
		ZipFile zf=null;
		Gson gson=new Gson();
		String jsonContent=null;

		try {
			logger.info(">> open ZIP");
			zf = new ZipFile(fileName);

			Enumeration<? extends ZipEntry> entries = zf.entries();

			while(entries.hasMoreElements()){
				ZipEntry ze = entries.nextElement();

				byte content[]=null;
				byte buffer[] =new byte[1024];
				ByteArrayOutputStream baos= new ByteArrayOutputStream();
				if(ze.getName().endsWith(".json")){
					logger.info(">> Reading from:"+ze.getName()+", "+ze.getSize()+" bytes");
					InputStream is = zf.getInputStream(ze);
					int r=0;
					while((r=is.read(buffer))!=-1){
						baos.write(buffer, 0, r);
						baos.flush();
					}
					baos.close();
					is.close();
					logger.info(">> OK read.");
					
					content = baos.toByteArray();
					logger.info(">> content.length="+content.length);
					
					jsonContent=new String(content);					
					
					logger.info(">> jsonContent.size="+jsonContent.length());
					//logger.info(">> jsonContent="+jsonContent);
				}
			}
			zf.close();
			logger.info(">> After read zip");
			if(jsonContent != null) {
				logger.info(">> parse:");
				paqueteSinc = gson.fromJson(jsonContent, SyncDTOPackage.class);			
				logger.info(">> paqueteSinc:"+paqueteSinc);
				List<P> lp=paqueteSinc.getInventarioSucursalQVList();
				productosParaBuscar = new HashMap<String,P>();
				for(P p: lp){
					productosParaBuscar.put(p.getCb(), p);
				}
				logger.info(">> productosParaBuscar, ok ready !");
			}
			
		} catch (Exception ex) {
			logger.error(null, ex);
		}

	}
	
	public static String getProperty(String p){
		return properties.getProperty(p);
	}
	
	public static String getCajaGlobalInfo() {
		return "pms"+properties.getProperty("sucursal")+"c"+properties.getProperty("caja");
	}

	public static P fastSearchProducto(String codigoBuscar) {
		return productosParaBuscar.get(codigoBuscar);
	}

	private static Object getLoggedIn() {
		U logged = ApplicationLogic.getInstance().getLogged();
		if(logged == null){
			return "null";
		} else {
			return logged.getE();
		}
	}
	
	private static String userAgentExpression;
	
	private static String getUserAgentExpression() {
		if(userAgentExpression == null){			
			String os = System.getProperty("os.name")+"_"+System.getProperty("os.version")+"("+System.getProperty("os.arch")+")";
/*
{java.runtime.name=Java(TM) SE Runtime Environment, sun.boot.library.path=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib, java.vm.version=24.51-b03, user.country.format=MX, gopherProxySet=false, java.vm.vendor=Oracle Corporation, java.vendor.url=http://java.oracle.com/, path.separator=:, java.vm.name=Java HotSpot(TM) 64-Bit Server VM, file.encoding.pkg=sun.io, user.country=ES, sun.java.launcher=SUN_STANDARD, sun.os.patch.level=unknown, java.vm.specification.name=Java Virtual Machine Specification, user.dir=/Users/alfredo/tmp/pmarlen-caja, java.runtime.version=1.7.0_51-b13, java.awt.graphicsenv=sun.awt.CGraphicsEnvironment, java.endorsed.dirs=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/endorsed, os.arch=x86_64, java.io.tmpdir=/var/folders/r0/x48m0drs5jjb972jf25km_pw0000gp/T/, line.separator=
, java.vm.specification.vendor=Oracle Corporation, os.name=Mac OS X, sun.jnu.encoding=UTF-8, java.library.path=/Users/alfredo/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:., sun.awt.enableExtraMouseButtons=true, java.specification.name=Java Platform API Specification, java.class.version=51.0, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, os.version=10.9.5, http.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, user.home=/Users/alfredo, user.timezone=America/Mexico_City, java.awt.printerjob=sun.lwawt.macosx.CPrinterJob, file.encoding=UTF-8, java.specification.version=1.7, java.class.path=pmarlen-caja.jar, user.name=alfredo, java.vm.specification.version=1.7, sun.java.command=pmarlen-caja.jar, java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre, sun.arch.data.model=64, user.language=es, java.specification.vendor=Oracle Corporation, awt.toolkit=sun.lwawt.macosx.LWCToolkit, java.vm.info=mixed mode, java.version=1.7.0_51, java.ext.dirs=/Users/alfredo/Library/Java/Extensions:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/ext:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java, sun.boot.class.path=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/sunrsasign.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/classes, java.vendor=Oracle Corporation, file.separator=/, java.vendor.url.bug=http://bugreport.sun.com/bugreport/, sun.font.fontmanager=sun.font.CFontManager, sun.io.unicode.encoding=UnicodeBig, sun.cpu.endian=little, socksNonProxyHosts=local|*.local|169.254/16|*.169.254/16, ftp.nonProxyHosts=local|*.local|169.254/16|*.169.254/16, sun.cpu.isalist=}			
			
			
			*/
			userAgentExpression =	"pmarlen-caja_"+ApplicationLogic.getInstance().getVersion()+
									"|os="+os+
									"|java-version="+System.getProperty("java.version")+
									"|user-working="+System.getProperty("user.name")+"@"+System.getProperty("user.dir");
		}
		return userAgentExpression;
		
	}

}
