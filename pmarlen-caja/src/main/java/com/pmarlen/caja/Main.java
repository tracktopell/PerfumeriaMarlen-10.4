package com.pmarlen.caja;

import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.caja.control.DialogLoginControl;
import com.pmarlen.caja.control.FirstRunParamsConfigDialogControl;
import com.pmarlen.caja.control.FramePrincipalControl;
import com.pmarlen.caja.control.UpadateApplicationJFrameControl;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.DialogLogin;
import com.pmarlen.caja.view.UpadateApplicationJFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
	private static boolean dinamicDebug=false;
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("main:Main args[]={");
		dinamicDebug=false;
		int na=0;
		for(String a: args){
			if(na>0){
				System.out.print(", ");
			}
			System.out.println("main:\t\""+a+"\"");
			if(a.equals("-debug=true")){
				dinamicDebug = true;
			}
			na++;
		}
		System.out.println("main:}");
		
		if(dinamicDebug){
			System.out.println("main:->Activating Log4J DEBUG Level.");
			LogManager.getRootLogger().setLevel(Level.DEBUG);
		}
		
		isSingleInstanceRunning();

		logger.debug("main:ok, Just 1 Thread");
		
		MemoryDAO.loadProperties();		
		
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

		if (!MemoryDAO.isExsistFile()) {
			logger.debug("main: 1st Time");
			FirstRunParamsConfigDialogControl firstRunParamsConfigDialogControl = new FirstRunParamsConfigDialogControl();
			firstRunParamsConfigDialogControl.estadoInicial();

			while (firstRunParamsConfigDialogControl.isConfiguring()) {
				try {
					logger.trace("main:configuring");
					Thread.sleep(500);
				} catch (InterruptedException ie) {
					logger.trace("main:InterruptedException");
				}
			}
			logger.debug("main:is configuring ?" + firstRunParamsConfigDialogControl.isConfiguring());
			if (!firstRunParamsConfigDialogControl.getParamatersConfigured()) {
				logger.info("[USER]->!firstRunParamsConfigDialogControl.getParamatersConfigured()" );
				System.exit(2);
			}
			firstRunParamsConfigDialogControl = null;
		}
		
		if (ApplicationLogic.getInstance().needsUpdateApplciation()) {
			int respuesta = JOptionPane.showConfirmDialog(null,
					"HAY UNA NUEVA VERIÓN PARA INSTALAR,\n ¿ DESEA ACTUALIZAR DE LA VERSIÓN ACTUAL "+
					ApplicationLogic.getInstance().getVersion()+" A "+ApplicationLogic.getInstance().getVersionRead()+
					"\n EN ESTE MOMENTO ?",
					"ACTUALIZACION DISPONIBLE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
					System.exit(3);
				}
				
				uaf  = null;
				uafc = null;
			} else {
				logger.info("[USER]->UpdateApp:NO");
			}	
		}
		
		logger.debug("main:Ready for Preload.");
		MemoryDAO.preLoad();
		ApplicationLogic.getInstance().iniciaAppCorteCajaDTO();		
		logger.debug("main:CorteCajaDTO: sucursalId="+ApplicationLogic.getInstance().getCorteCajaDTO().getSucursalId()+", #Caja:"+ApplicationLogic.getInstance().getCorteCajaDTO().getCaja());

		logger.debug("main:======================= S T A R T I N G =======================");

		MemoryDAO.startPaqueteSyncService();

		try {
			DialogLogin dialogLogin = DialogLogin.getInstance(FramePrincipalControl.getInstance().getFramePrincipal());
			DialogLoginControl dlc = new DialogLoginControl(dialogLogin);
			
			FramePrincipalControl.getInstance().setFontBigest();
			FramePrincipalControl.getInstance().estadoInicial();
			FramePrincipalControl.getInstance().iniciaReloj();

			logger.debug("main:Frame Principal, esperando por Login");
			dlc.setFontBigest();
			dlc.getDialogLogin().pack();
			dlc.getDialogLogin().setLocationRelativeTo(null);
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
