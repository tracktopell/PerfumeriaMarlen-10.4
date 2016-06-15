package com.pmarlen.caja;

import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.control.DialogLoginControl;
import com.pmarlen.caja.control.FramePrincipalControl;
import com.pmarlen.caja.control.ParamsConfigDialogControl;
import com.pmarlen.caja.control.UpadateApplicationJFrameControl;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.DialogLogin;
import com.pmarlen.caja.view.ParamsConfigDialog;
import com.pmarlen.caja.view.SplashFrame;
import com.pmarlen.caja.view.UpadateApplicationJFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class Main {

	private static boolean singleInstanceRunning = false;
	private static Logger logger = Logger.getLogger(Main.class.getName());
	//public static final String INTELBTH = "intelbth";
	public static boolean dinamicDebug=false;
	public static boolean dinamicTrace=false;
	public static final int EXIT_UPDATE_REBOOT = 5;
	/**
	 * @param args the command line arguments
	 */
	private static SplashFrame splashFrame;
	public static void main(String[] args) {
		CorteCajaDTO putaMadre = new CorteCajaDTO();
		System.out.println("main:Main args[]={");
		SplashFrame splashFrame = new SplashFrame();
		splashFrame.setVisible(true);
		splashFrame.addMessage("...INICIANDO");
		dinamicDebug=false;
		int na=0;
		for(String a: args){
			if(na>0){
				System.out.print(", ");
			}
			System.out.println("main:\t\""+a+"\"");
			if(a.equals("-debug=true")){
				dinamicDebug = true;
			} else if(a.equals("-trace=true")){
				dinamicTrace = true;
			}
			na++;
		}
		splashFrame.addMessage("...ARGUMENTOS");
		if(dinamicDebug || dinamicTrace){
			System.out.println("\tClassPath:{");
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			URL[] urls = ((URLClassLoader)cl).getURLs();
			for(URL url: urls){
				System.out.println("\t\t"+url.getFile());
			}
		}
		splashFrame.addMessage("...DEBUG");
		System.out.println("main:}");
		System.out.println("LogManager.getRootLogger().getLevel():"+LogManager.getRootLogger().getLevel());
		
		if(dinamicDebug){
			System.out.println("main:->Activating Log4J DEBUG Level.");
			LogManager.getRootLogger().setLevel(Level.DEBUG);
		}
		if(dinamicTrace){
			System.out.println("main:->Activating Log4J TRACE Level.");
			LogManager.getRootLogger().setLevel(Level.TRACE);
		}
		splashFrame.addMessage("...IS SINGLE INSTANCE RUNNING ?");
		
		
		
		isSingleInstanceRunning();

		logger.debug("main:ok, Just 1 Thread");
		
		splashFrame.addMessage("...LOAD PROPERTIES");
		
		MemoryDAO.loadProperties();
		splashFrame.addMessage("...OK PROPERTIES");
		
		ApplicationLogic.getInstance().getVersion();
		logger.debug("main: After Load Properties.");
		logger.info("main: ====================================================================");
		logger.info("main: ApplicationLogic.getInstance().getVersion():"+ApplicationLogic.getInstance().getVersion());
		
		try {
			logger.trace("main: L&Fs:" + Arrays.asList(UIManager.getInstalledLookAndFeels()));
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

		} catch (Exception e) {
			logger.error( "main:setLookAndFeel:", e);
		}
		splashFrame.addMessage("...OK, LOOKL & FEEL");
		if (!MemoryDAO.isExsistFile()) {
			splashFrame.addMessage("...1ST TIME");
			logger.debug("main: 1st Time");
			ParamsConfigDialog dlg1stTime =  new ParamsConfigDialog();
			logger.debug("main: dlg1stTime created");
			ParamsConfigDialogControl pcd1stTimeControl=new ParamsConfigDialogControl(dlg1stTime);
			logger.debug("main: pcd1stTimeControl created, -> pcd1stTimeControl.estadoInicial()");
			splashFrame.addMessage("...CONFIGURING");
			splashFrame.setVisible(false);
//			splashFrame.dispose();
			pcd1stTimeControl.estadoInicial();

			while (pcd1stTimeControl.isConfiguring()) {
				try {
					logger.trace("main:configuring, ...waith !!");
					Thread.sleep(500);
				} catch (InterruptedException ie) {
					logger.trace("main:InterruptedException");
				}
			}
			logger.debug("main:is configuring ?" + pcd1stTimeControl.isConfiguring());
			if (!pcd1stTimeControl.getParamatersConfigured()) {
				logger.info("[USER]->!pcd1stTimeControl.getParamatersConfigured()" );
				
				splashFrame.dispose();
				System.exit(2);
			}
			splashFrame.addMessage("...OK CONFIGURED 1ST TIME");
			splashFrame.setVisible(true);
			
			pcd1stTimeControl = null;
			logger.debug("main: after dlg1stTime");
		}
		splashFrame.addMessage("...UPDATE ?");
		if (ApplicationLogic.getInstance().needsUpdateApplciation()) {
			splashFrame.addMessage("...ASK !");
			int respuesta = JOptionPane.showConfirmDialog(null,
					"HAY UNA NUEVA VERISÓN PARA INSTALAR,\n ¿ DESEA ACTUALIZAR DE LA VERSIÓN ACTUAL "+
					ApplicationLogic.getInstance().getVersion()+" A "+ApplicationLogic.getInstance().getVersionRead()+
					"\n EN ESTE MOMENTO, PARA DESCARGARSE ?",
					"ACTUALIZACIÓN DISPONIBLE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (respuesta == JOptionPane.YES_OPTION) {
				logger.info("[USER]->UpdateApp:YES");
				UpadateApplicationJFrame uaf = new UpadateApplicationJFrame();
				UpadateApplicationJFrameControl uafc = new UpadateApplicationJFrameControl(uaf);
				uafc.estadoInicial();
				logger.debug("main:Updating, wait... for download");
				while(uafc.isActualizando()){
					try {						
						Thread.sleep(500);
					} catch (InterruptedException ie) {
						logger.trace("main:InterruptedException");
					}
				}
				
				if(uafc.isReboot()) {
					logger.info("[USER]->REBOT AFTER UPDATE");
					splashFrame.addMessage("...REBOOT");
					splashFrame.setVisible(false);
					splashFrame.dispose();

					System.exit(EXIT_UPDATE_REBOOT);
				}
				
				uaf  = null;
				uafc = null;
			} else {
				logger.info("[USER]->UpdateApp:NO");
			}	
		}
		
		splashFrame.addMessage("...MemoryDAO PRELOAD");					
		logger.debug("main:Ready for MemoryDAO.preLoad()");
		MemoryDAO.preLoad();
		logger.debug("main:Ready for ApplicationLogic.getInstance().iniciaAppCorteCajaDTO()");
		
		splashFrame.addMessage("...Corte Caja");
		
		ApplicationLogic.getInstance().iniciaAppCorteCajaDTO();		
		logger.debug("main:[*BUG*] CorteCajaDTO: sucursalId="+ApplicationLogic.getInstance().getCorteCajaDTO().getSucursalId()+", #Caja:"+ApplicationLogic.getInstance().getCorteCajaDTO().getCaja());

		logger.debug("main:======================= S T A R T I N G =======================");

		MemoryDAO.startPaqueteSyncService();
		splashFrame.addMessage("...start Paquete Sync Service");
		try {			
			DialogLogin dialogLogin = DialogLogin.getInstance(FramePrincipalControl.getInstance().getFramePrincipal());
			DialogLoginControl dlc = new DialogLoginControl(dialogLogin);
			
			MemoryDAO.setSyncUpdateListener(FramePrincipalControl.getInstance());
			
			FramePrincipalControl.getInstance().setFontBigest();
			FramePrincipalControl.getInstance().estadoInicial();
			FramePrincipalControl.getInstance().iniciaReloj();
			splashFrame.addMessage("...Login");
			logger.debug("main:Frame Principal, esperando por Login");
			dlc.setFontBigest();
			dlc.getDialogLogin().pack();
			dlc.getDialogLogin().setLocationRelativeTo(null);
			
			splashFrame.setVisible(false);
			splashFrame.dispose();
			splashFrame =  null;
			dlc.estadoInicial();
			if (!dlc.isLoggedIn()) {
				throw new IllegalAccessException();
			} else {
				
				dialogLogin = null;
				dlc         = null;
				
				logger.debug("main:======================= L O G G E D   I N  =======================");
				
				FramePrincipalControl.getInstance().enableAndDisableAdminControls();
				FramePrincipalControl.getInstance().updateStatusWest();
			}
		} catch (IllegalAccessException e) {
			logger.info("[USER]->CLOSE");
			System.exit(1);
		} catch (Exception e) {
			logger.error("Main:", e);
			System.exit(2);
		}
	}

	private static void isSingleInstanceRunning() {
		new Thread() {
			@Override
			public void run() {
				try {
					ServerSocket serverSocket = new ServerSocket(5690);
					logger.debug("OK, is single instance !");
					singleInstanceRunning = true;
					Socket s = serverSocket.accept();
				} catch (IOException ioe) {
					ioe.printStackTrace(System.err);
					JOptionPane.showMessageDialog(null, "¡LA APLICACIÓN YA SE HA INICIADO!, \nCIERRE PRIMERA ESTA OTRA.", "PMCaja >> Express", JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();

		int timeOut = 0;
		try {
			while (!singleInstanceRunning) {
				Thread.sleep(1000L);
				logger.debug("-->> close ?");
				timeOut++;
				if (timeOut >= 10) {
					break;
				}
			}
		} catch (InterruptedException ie) {

		} finally {
			if (!singleInstanceRunning) {
				logger.debug("-->> close !!");
				System.exit(1);
			}
		}
	}
}
