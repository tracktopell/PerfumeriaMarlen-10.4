/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.Notificacion;
import com.pmarlen.caja.model.VentaSesion;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.U;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author tracktopell
 */
public class ApplicationLogic {
	private static Logger logger = Logger.getLogger(ApplicationLogic.class.getName());
	private static final  long  statTime=System.currentTimeMillis();
	private static final String URI_VERSION_FILE = "/pmcajaupdate/version.properties";
	private static final String URI_APP_PACKAGE  = "/pmcajaupdate/update.zip";
	private static final String FILE_APP_PACKAGE = "update.zip";
	private static final String BACKUP_DIR       = "backup";
	private static String _version = null;
	private static final boolean printingEnabled = true; 
		
	private static final String VERSION_PROPERTY = "pmarlencaja.version";
	private boolean adminLogedIn = false;
	private String versionRead;
	private static ApplicationLogic instance;
	private U logged;
	private Almacen almacen;	
	private HashMap<Integer,Almacen> tipoAlmacen;
	private CorteCajaDTO corteCajaDTO;
	private	VentaSesion ventaSesion;
	private LinkedHashMap<String,Notificacion> notificaciones;

	private ApplicationLogic(){
		corteCajaDTO = new CorteCajaDTO();
		notificaciones = new LinkedHashMap<String,Notificacion>();
		nuevaNotificacion=false;
	}

	public CorteCajaDTO getCorteCajaDTO() {
		return corteCajaDTO;
	}
	
	public void setLogged(U logged) {
		this.logged = logged;
	}

	public U getLogged() {
		return logged;
	}
	
	public String getBTImpresora() {
		return null;
	}
	
	public void setBTImpresora(String btaImpresora) {
		
	}

	/**
	 * @return the instance
	 */
	public static ApplicationLogic getInstance() {
		if(instance == null){
			instance =  new ApplicationLogic();
		}
		return instance;
	}

	public String getVersionRead() {
		return versionRead;
	}
	
	public boolean needsUpdateApplciation(){
		boolean updateApp =  false;
		
		URL url=null;
		InputStream is = null;
		BufferedReader br = null;
		try{
			url = new URL(MemoryDAO.getServerContext()+URI_VERSION_FILE);
			logger.debug("url="+url);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			is = conn.getInputStream();
		} catch(FileNotFoundException fnfe){
			logger.error("needsUpdateApplciation: 404:" + fnfe);
			return false;
		} catch(IOException ioe){
			logger.error("needsUpdateApplciation: No se puede coenctar:"+ ioe);
			return false;
		} catch(Exception e){
			logger.error("needsUpdateApplciation: Error grave:", e);
			return false;
		}
		
		br = new BufferedReader(new InputStreamReader(is));
		String lineRead = null;
		try{
			while((lineRead = br.readLine()) != null) {
				if(lineRead.contains(VERSION_PROPERTY)){
					
					String[] propValue = lineRead.split("=");
					versionRead = propValue[1]; 
					
					logger.debug("lineRead="+lineRead+", versionReadOfLine="+versionRead);
					logger.debug("current version ="+getVersion());					
					
					String currentVersionParts[] = getVersion().split("\\.");
					String versionReadParts[]    = versionRead.split("\\.");
					
					logger.debug("comparing: "+Arrays.asList(currentVersionParts)+" < "+Arrays.asList(versionReadParts)+" : "+campareVersions(currentVersionParts,versionReadParts));
					
					if(campareVersions(currentVersionParts,versionReadParts)<0){
						logger.debug("\t->needsUpdateApplciation: Ok, update!");
						return true;
					}
				}
			}			
		} catch(IOException ioe){
			logger.error("needsUpdateApplciation: ERROR EN LA LECTURA:",ioe);
			return false;
		}
		
		return updateApp;
	}
	
	public int compareRemoteVersion(String remoteCurrentVersion){
		String currentVersionParts[] = getVersion().split("\\.");
		String versionReadParts[]    = remoteCurrentVersion.split("\\.");
		return campareVersions(currentVersionParts, versionReadParts);			
	}
	
	private int campareVersions(String currentVersionParts[],String versionReadParts[]){
		int  cv = Integer.parseInt(currentVersionParts[0])*1000000+
				  Integer.parseInt(currentVersionParts[1])*10000+
				  Integer.parseInt(currentVersionParts[2])*100+
				  Integer.parseInt(currentVersionParts[3]);
		int  nv = Integer.parseInt(versionReadParts[0])*1000000+
				  Integer.parseInt(versionReadParts[1])*10000+
				  Integer.parseInt(versionReadParts[2])*100+
				  Integer.parseInt(versionReadParts[3]);
		return cv - nv;
	}
	
	void updateApplication(final UpdateApplicationListener ual) {
		new Thread(){

			@Override
			public void run() {
				downloadApplication(ual);
			}
		}.start();
	}
	
	void cacellUpdateApplication() {
		keepDownlaod = false;
	}
	
	private boolean keepDownlaod;
	
	private void downloadApplication(final UpdateApplicationListener ual) {
		URL url=null;
		BufferedReader br = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		
		try{
			url = new URL(MemoryDAO.getServerContext()+URI_APP_PACKAGE);
			conn = (HttpURLConnection)url.openConnection();
			long length = conn.getContentLengthLong();
			is = conn.getInputStream();
			File lastUpdate = new File(FILE_APP_PACKAGE);
			if(lastUpdate.exists() && lastUpdate.isFile() && lastUpdate.canRead() && lastUpdate.length()>1024){
				logger.debug("downloadApplication: Backup last update File.");
				
				File backupDir = new File(BACKUP_DIR);
				if(!backupDir.exists()) {
					boolean r = backupDir.mkdirs();
					logger.debug("downloadApplication: Creating BACKUP DIR:"+backupDir+" ? resutl created:"+r);
				}
				File backupFile=new File(BACKUP_DIR+"/update_"+getVersion()+".zip");
				logger.debug("downloadApplication: Backup last update File to:"+backupFile);				
				Path copied = Files.copy(lastUpdate.toPath(),backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				logger.debug("downloadApplication: OK copied for backup:"+copied);
			}
			
			FileOutputStream fos = new FileOutputStream(FILE_APP_PACKAGE);
			byte[] buffer = new byte[1024 * 1024]; // 1MB
			long r = -1;
			long t= 0;
			keepDownlaod = true;
			while ((r = is.read(buffer, 0, buffer.length)) != -1) {
				if(!keepDownlaod){
					int resp = JOptionPane.showConfirmDialog(null, "¿ DESEA CANCELAR LA DESCARGA ?", "CANCELAR", 
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resp == JOptionPane.YES_OPTION){
						break;
					} else {
						keepDownlaod = true;
					}
				}
				t += r;
				fos.write(buffer, 0, (int)r);
				fos.flush();
				long advance = (100L * t) / length;
				logger.debug("------->> Downloaded:\t [+ "+r+"]( "+t+"/"+length+") : "+advance+" %");
				ual.updateProgress((int)advance);
			}
			ual.updateProgress(100);
			logger.debug("");
			logger.debug("finished: saved to"+FILE_APP_PACKAGE);
			is.close();
			fos.close();
			if(!keepDownlaod){
				throw new IllegalStateException("Update Canceled");
			} else {
				extractFolder(FILE_APP_PACKAGE);
				ual.finisUpdate();
			}
		} catch (IOException ex) {
			ual.errorUpdate("ERROR AL ACTUALIZAR:"+ex.getMessage());
		}		
	}

	private void extractFolder(String zipFile) throws ZipException, IOException {
		System.out.println(zipFile);
		int BUFFER = 2048;
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file);
		String destPathToInflate = ".";

		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		logger.debug("-> extracting :");
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(destPathToInflate, currentEntry);
			logger.debug("-> inflating :"+destFile.getPath());
			//destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory()) {
				BufferedInputStream is = new BufferedInputStream(zip
						.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest = new BufferedOutputStream(fos,
						BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		}
		logger.debug("-> OK, finish extracting, versionRead="+versionRead);
		/*
		logger.debug("-> OK, finish extracting, update sym link for: versionRead="+versionRead);
		try {
			String cmdToRelink = "ln -sf pmarlen-caja-"+versionRead+".jar pmarlen-caja.jar";
			
			int exitStatus = Runtime.getRuntime().exec(cmdToRelink).waitFor();
			logger.debug("-> exec: "+cmdToRelink+" ? "+exitStatus);
		}catch(InterruptedException ie){
			logger.error("Relink:", ie);
		}
		*/
	}

	boolean canDownlaodUpdateApplication() {
		try {
			URL  url = new URL(MemoryDAO.getServerContext()+URI_APP_PACKAGE);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			int length = conn.getContentLength();
			if(length > 1024){
				return true;
			} else{
				return false;
			}
		} catch(Exception ex){
			ex.printStackTrace(System.err);
			return false;
		}
	}
	
	U checkForUser(String usuarioEmail,String plainPassword) {
		U logged=null;
		List<U> usuarioList = MemoryDAO.getPaqueteSinc().getUsuarioList();
		for(U u: usuarioList){
			if(u.getE().equals(usuarioEmail)){
				if(u.getP().equals(getMD5Encrypted(plainPassword))){
					logged=u;
					break;
				}
			}
		}
		return logged;
	}

	private static String getMD5Encrypted(String e) {

        MessageDigest mdEnc = null; // Encryption algorithm
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        mdEnc.update(e.getBytes(), 0, e.length());
        return (new BigInteger(1, mdEnc.digest())).toString(16);
    }

	public String getVersion() {
		if(_version == null){
			Properties porpVersion = new Properties();
			try {
				porpVersion.load(getClass().getResourceAsStream("/version.properties"));
				_version = porpVersion.getProperty("pmarlencaja.version");
			} catch (IOException ex) {
				ex.printStackTrace(System.err);
			}
		}
		return _version;
	}
	
	public static String calculateSessionId() {
		String sessionId= MemoryDAO.getProperty("sucursal")+"."+MemoryDAO.getProperty("caja")+"|"+statTime;
		return getMD5Encrypted(sessionId).toUpperCase();
	}

	boolean isPrintingEnabled() {
		return printingEnabled;
	}
	
	public void setAlmacen(List<Almacen> almacenList) {
		if(this.tipoAlmacen == null) {			
			this.tipoAlmacen = new HashMap<>();
		}
		for(Almacen a:almacenList){
			this.tipoAlmacen.put(a.getTipoAlmacen(), a);
			if(a.getTipoAlmacen() == Constants.ALMACEN_PRINCIPAL){
				this.almacen = a;
			}
		}
	}
	

	public HashMap<Integer, Almacen> getTipoAlmacen() {
		return tipoAlmacen;
	}
	
	public Almacen getAlmacen() {
		return almacen;
	}
	
	private CorteCajaDTO lastSavedCC;

	public CorteCajaDTO getLastSavedCC() {
		return lastSavedCC;
	}
	
	public void iniciaAppCorteCajaDTO(){
		
		lastSavedCC = MemoryDAO.readLastSavedCorteCajaDTO();
		
		getCorteCajaDTO().setCaja(MemoryDAO.getNumCaja());
		getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
		getCorteCajaDTO().setFecha(System.currentTimeMillis());
		getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_AP_INICIADA);
		if(logged!=null) {
			getCorteCajaDTO().setUsuarioEmail(logged.getE());
		} else {
			getCorteCajaDTO().setUsuarioEmail(null);
		}
		getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
		MemoryDAO.saveCorteCajaDTO(corteCajaDTO);
		MemoryDAO.backupCorteCajaDTO(corteCajaDTO);
	}	

	double getRemoteSaldoFinalEstimado() throws IOException{
		double  saldoFinalEstimado = 0.0;
		
		saldoFinalEstimado = MemoryDAO.getRemoteSaldoEstimado();
		
		return saldoFinalEstimado;
	}

	public VentaSesion getVentaSesion() {
		if(ventaSesion == null){
			ventaSesion = new VentaSesion();
		}
		return ventaSesion;
	}
	
	boolean nuevaNotificacion;
	
	public void add(Notificacion nuevaNot){
		if( ! notificaciones.containsKey(nuevaNot.getId()) ){
			notificaciones.put(nuevaNot.getId(), nuevaNot);
			nuevaNotificacion = true;
		}
	}

	public Iterator<Notificacion> getNotificaciones() {
		nuevaNotificacion = false;
		return notificaciones.values().iterator();
	}

	public boolean isNuevaNotificacion() {
		return nuevaNotificacion;
	}
	
	public synchronized int getNotificacionesSinLeer(){
		int  nsl=0;
		
		for(Notificacion n:notificaciones.values()){
			if(!n.isVista()){
				nsl++;
			}
		}
		
		return nsl;
	}
	
}
